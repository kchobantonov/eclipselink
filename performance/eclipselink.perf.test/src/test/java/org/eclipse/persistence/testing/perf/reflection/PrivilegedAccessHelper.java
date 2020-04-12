/*
 * Copyright (c) 1998, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Oracle - initial API and implementation from Oracle TopLink
//     08/23/2010-2.2 Michael O'Brien
//        - 323043: application.xml module ordering may cause weaving not to occur causing an NPE.
//                       warn if expected "_persistence_//_vh" method not found
//                       instead of throwing NPE during deploy validation.
//     30/05/2012-2.4 Guy Pelletier
//       - 354678: Temp classloader is still being used during metadata processing
//
package org.eclipse.persistence.testing.perf.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.internal.helper.Helper;

/**
 * INTERNAL:
 * Privileged Access Helper provides a utility so all calls that require privileged access can use the same code.
 *
 * Do privileged blocks can be used with a security manager to grant a code base (eclipselink.jar) access to certain
 * Java operations such as reflection.  Generally a security manager is not enabled in a JVM, so this is not an issue.
 * If a security manager is enabled, then either the application can be configured to have access to operations such as
 * reflection, or only EclipseLink can be given access.  If only EclipseLink is desired to be given access then
 * do privileged must be enabled through the System property "eclipselink.security.usedoprivileged"=true.
 *
 * Note the usage of do privileged has major impacts on performance, so should normally be avoided.
 */
public class PrivilegedAccessHelper {
    private static boolean defaultUseDoPrivilegedValue = false;
    private static boolean shouldCheckPrivilegedAccess = true;
    private static boolean shouldUsePrivilegedAccess = false;

    private static Map<String, Class> primitiveClasses;

    static {
        primitiveClasses = new HashMap<String, Class>();
        primitiveClasses.put("boolean", boolean.class);
        primitiveClasses.put("int", int.class);
        primitiveClasses.put("long", long.class);
        primitiveClasses.put("float", float.class);
        primitiveClasses.put("double", double.class);
        primitiveClasses.put("char", char.class);
        primitiveClasses.put("byte", byte.class);
        primitiveClasses.put("void", void.class);
        primitiveClasses.put("short", short.class);
    }

    /**
     * INTERNAL
     * It will be used to set default value of property "eclipselink.security.usedoprivileged"
     * if not passed as system property. This is used by GlassfishPlatform.
     */
    public static void setDefaultUseDoPrivilegedValue(boolean def) {
        defaultUseDoPrivilegedValue = def;
        shouldCheckPrivilegedAccess = true;
    }

    /**
     * Finding a field within a class potentially has to navigate through it's superclasses to eventually
     * find the field.  This method is called by the public getDeclaredField() method and does a recursive
     * search for the named field in the given classes or it's superclasses.
     */
    private static Field findDeclaredField(Class javaClass, String fieldName) throws NoSuchFieldException {
        try {
            return javaClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException ex) {
            Class superclass = javaClass.getSuperclass();
            if (superclass == null) {
                throw ex;
            } else {
                return findDeclaredField(superclass, fieldName);
            }
        }
    }

    /**
     * Finding a method within a class potentially has to navigate through it's superclasses to eventually
     * find the method.  This method is called by the public getDeclaredMethod() method and does a recursive
     * search for the named method in the given classes or it's superclasses.
     */
    private static Method findMethod(Class javaClass, String methodName, Class[] methodParameterTypes) throws NoSuchMethodException {
        try {
            // use a combination of getDeclaredMethod() and recursion to ensure we get the non-public methods
            // getMethod will not help because it returns only public methods
            return javaClass.getDeclaredMethod(methodName, methodParameterTypes);
        } catch (NoSuchMethodException ex) {
            Class superclass = javaClass.getSuperclass();
            if (superclass == null) {
                throw ex;
            } else {
                try{
                    return findMethod(superclass, methodName, methodParameterTypes);
                }catch (NoSuchMethodException lastEx){
                    throw ex;
                }
            }
        }
    }

    /**
     * Execute a java Class.forName().  Wrap the call in a doPrivileged block if necessary.
     * @param className
     */
    public static Class getClassForName(final String className) throws ClassNotFoundException {
        // Check for primitive types.
        Class primitive = primitiveClasses.get(className);
        if (primitive != null) {
            return primitive;
        }
        return Class.forName(className);
    }

    /**
     * Execute a java Class.forName() wrap the call in a doPrivileged block if necessary.
     */
    public static Class getClassForName(final String className, final boolean initialize, final ClassLoader loader) throws ClassNotFoundException {
        // Check for primitive types.
        Class primitive = primitiveClasses.get(className);
        if (primitive != null) {
            return primitive;
        }
        return Class.forName(className, initialize, loader);
    }

    /**
     * Gets the class loader for a given class.  Wraps the call in a privileged block if necessary
     */
    public static ClassLoader getClassLoaderForClass(final Class clazz) {
        return clazz.getClassLoader();
    }

    /**
     * Get the public constructor for the given class and given arguments and wrap it in doPrivileged if
     * necessary.  The shouldSetAccessible parameter allows the the setAccessible API to be called as well.
     * This option was added to avoid making multiple doPrivileged calls within InstantiationPolicy.
     * @param javaClass The class to get the Constructor for
     * @param args An array of classes representing the argument types of the constructor
     * @param shouldSetAccessible whether or not to call the setAccessible API
     * @throws java.lang.NoSuchMethodException
     */
    public static Constructor getConstructorFor(final Class javaClass, final Class[] args, final boolean shouldSetAccessible) throws NoSuchMethodException {
        Constructor result = null;
        try {
            result = javaClass.getConstructor(args);
        } catch (NoSuchMethodException missing) {
            // Search for any constructor with the same number of arguments and assignable types.
            for (Constructor constructor : javaClass.getConstructors()) {
                if (constructor.getParameterTypes().length == args.length) {
                    boolean found = true;
                    for (int index = 0; index < args.length; index++) {
                        Class parameterType = Helper.getObjectClass(constructor.getParameterTypes()[index]);
                        Class argType = Helper.getObjectClass(args[index]);
                        if ((!parameterType.isAssignableFrom(argType))
                                && (!argType.isAssignableFrom(parameterType))) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        result = constructor;
                        break;
                    }
                }
            }
            if (result == null) {
                throw missing;
            }
        }
        if (shouldSetAccessible) {
            result.setAccessible(true);
        }
        return result;
    }

    /**
     *  Get the context ClassLoader for a thread.  Wrap the call in a doPrivileged block if necessary.
     */
    public static ClassLoader getContextClassLoader(final Thread thread) {
        return thread.getContextClassLoader();
    }

    /**
     * Get the constructor for the given class and given arguments (regardless of whether it is public
     * or private))and wrap it in doPrivileged if necessary.  The shouldSetAccessible parameter allows
     * the the setAccessible API to be called as well. This option was added to avoid making multiple
     * doPrivileged calls within InstantiationPolicy.
     * @param javaClass The class to get the Constructor for
     * @param args An array of classes representing the argument types of the constructor
     * @param shouldSetAccessible whether or not to call the setAccessible API
     * @throws java.lang.NoSuchMethodException
     */
    public static Constructor getDeclaredConstructorFor(final Class javaClass, final Class[] args, final boolean shouldSetAccessible) throws NoSuchMethodException {
        Constructor result = javaClass.getDeclaredConstructor(args);
        if (shouldSetAccessible) {
            result.setAccessible(true);
        }
        return result;
    }

    /**
     * Get a field in a class or its superclasses and wrap the call in doPrivileged if necessary.
     * The shouldSetAccessible parameter allows the the setAccessible API to be called as well.
     * This option was added to avoid making multiple doPrivileged calls within InstanceVariableAttributeAccessor.
     * @param javaClass The class to get the field from
     * @param fieldName The name of the field
     * @param shouldSetAccessible whether or not to call the setAccessible API
     * @throws java.lang.NoSuchFieldException
     */
    public static Field getField(final Class javaClass, final String fieldName, final boolean shouldSetAccessible) throws NoSuchFieldException {
        Field field = findDeclaredField(javaClass, fieldName);
        if (shouldSetAccessible) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * Get a field actually declared in a class and wrap the call in doPrivileged if necessary.
     * The shouldSetAccessible parameter allows the the setAccessible API to be called as well.
     * This option was added to avoid making multiple doPrivileged calls within InstanceVariableAttributeAccessor.
     * @param javaClass The class to get the field from
     * @param fieldName The name of the field
     * @param shouldSetAccessible whether or not to call the setAccessible API
     * @throws java.lang.NoSuchFieldException
     */
    public static Field getDeclaredField(final Class javaClass, final String fieldName, final boolean shouldSetAccessible) throws NoSuchFieldException {
        Field field = javaClass.getDeclaredField(fieldName);
        if (shouldSetAccessible) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * Get the list of fields in a class.  Wrap the call in doPrivileged if necessary
     * Excludes inherited fields.
     * @param clazz the class to get the fields from.
     */
    public static Field[] getDeclaredFields(final Class clazz) {
        return clazz.getDeclaredFields();
    }

    /**
     * Get the list of public fields in a class.  Wrap the call in doPrivileged if necessary
     * @param clazz the class to get the fields from.
     */
    public static Field[] getFields(final Class clazz) {
        return clazz.getFields();
    }

   /**
     * Return a method on a given class with the given method name and parameter
     * types. This call will NOT traverse the superclasses. Wrap the call in
     * doPrivileged if necessary.
     * @param methodName the name of the method to get
     *  parameters of the method.
     */
    public static Method getDeclaredMethod(final Class clazz, final String methodName, final Class[] methodParameterTypes) throws NoSuchMethodException {
         return clazz.getDeclaredMethod(methodName, methodParameterTypes);
    }

    /**
     * Get a method declared in the given class. Wrap the call in doPrivileged
     * if necessary. This call will traverse the superclasses. The
     * shouldSetAccessible parameter allows the the setAccessible API to be
     * called as well. This option was added to avoid making multiple
     * doPrivileged calls within MethodBasedAttributeAccessor.
     * @param javaClass The class to get the method from
     * @param methodName The name of the method to get
     * @param methodParameterTypes A list of classes representing the classes of the parameters of the mthod
     * @param shouldSetAccessible whether or not to call the setAccessible API
     * @throws java.lang.NoSuchMethodException
     */
    public static Method getMethod(final Class javaClass, final String methodName, final Class[] methodParameterTypes, final boolean shouldSetAccessible) throws NoSuchMethodException {
        Method method = findMethod(javaClass, methodName, methodParameterTypes);
        if (shouldSetAccessible) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * Get a public method declared in the given class. Wrap the call in doPrivileged if necessary.
     * This call will traverse the superclasses. The shouldSetAccessible parameter allows the the
     * setAccessible API to be called as well. This option was added to avoid making multiple
     * doPrivileged calls within MethodBasedAttributeAccessor.
     *
     * @param javaClass The class to get the method from
     * @param methodName The name of the method to get
     * @param methodParameterTypes A list of classes representing the classes of the parameters of the method
     * @param shouldSetAccessible whether or not to call the setAccessible API
     * @throws java.lang.NoSuchMethodException
     */
    public static Method getPublicMethod(final Class javaClass, final String methodName, final Class[] methodParameterTypes, final boolean shouldSetAccessible) throws NoSuchMethodException {
        // Return the (public) method - will traverse superclass(es) if necessary
        Method method = javaClass.getMethod(methodName, methodParameterTypes);
        if (shouldSetAccessible) {
            method.setAccessible(true);
        }
        return method;
    }

    /**
     * Get the list of methods in a class. Wrap the call in doPrivileged if
     * necessary. Excludes inherited methods.
     * @param clazz the class to get the methods from.
     */
    public static Method[] getDeclaredMethods(final Class clazz) {
        return clazz.getDeclaredMethods();
    }

    /**
     * Get the return type for a given method. Wrap the call in doPrivileged if necessary.
     * @param field
     */
    public static Class getFieldType(final Field field) {
        return field.getType();
    }

    /**
     * Get the list of parameter types for a given method.  Wrap the call in doPrivileged if necessary.
     * @param method The method to get the parameter types of
     */
    public static Class[] getMethodParameterTypes(final Method method) {
        return method.getParameterTypes();
    }

    /**
     * Get the return type for a given method. Wrap the call in doPrivileged if necessary.
     * @param method
     */
    public static Class getMethodReturnType(final Method method) {
        // 323148: a null method as a possible problem with module ordering breaking weaving - has been trapped by implementors of this method.
        return method.getReturnType();
    }

    /**
     * Get the list of methods in a class. Wrap the call in doPrivileged if
     * necessary. This call will traverse the superclasses.
     * @param clazz the class to get the methods from.
     */
    public static Method[] getMethods(final Class clazz) {
        return clazz.getMethods();
    }

    /**
     * Get the value of the given field in the given object.
     */
    public static Object getValueFromField(final Field field, final Object object) throws IllegalAccessException {
        return field.get(object);
    }

    /**
     * Construct an object with the given Constructor and the given array of arguments.  Wrap the call in a
     * doPrivileged block if necessary.
     */
    public static Object invokeConstructor(final Constructor constructor, final Object[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return constructor.newInstance(args);
    }

    /**
     * Invoke the givenMethod on a givenObject. Assumes method does not take
     * parameters. Wrap in a doPrivileged block if necessary.
     */
    public static Object invokeMethod(final Method method, final Object object) throws IllegalAccessException, InvocationTargetException {
        return invokeMethod(method, object, (Object[]) null);
    }

    /**
     * Invoke the givenMethod on a givenObject using the array of parameters given.  Wrap in a doPrivileged block
     * if necessary.
     */
    public static Object invokeMethod(final Method method, final Object object, final Object[] parameters) throws IllegalAccessException, InvocationTargetException {
        // Ensure the method is accessible.
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return method.invoke(object, parameters);
    }

    /**
     * Get a new instance of a class using the default constructor.  Wrap the call in a privileged block
     * if necessary.
     */
    public static Object newInstanceFromClass(final Class clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    /**
     * Set the value of a given field in the given object with the value given.  Wrap the call in a privileged block
     * if necessary.
     */
    public static void setValueInField(final Field field, final Object object, final Object value) throws IllegalAccessException {
        field.set(object, value);
    }

    /**
     * This method checks to see if calls should be made to doPrivileged.
     * It will only return true if a security manager is enabled,
     * and the "eclipselink.security.usedoprivileged" property is set.
     * <p>
     * Note: it is not possible to run EclipseLink using doPrivileged blocks when there is no SecurityManager
     * enabled.
     */
    public static boolean shouldUsePrivilegedAccess() {
        return true;
    }
}
