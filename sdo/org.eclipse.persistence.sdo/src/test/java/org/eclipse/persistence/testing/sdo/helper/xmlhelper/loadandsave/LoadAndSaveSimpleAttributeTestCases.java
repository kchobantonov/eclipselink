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
package org.eclipse.persistence.testing.sdo.helper.xmlhelper.loadandsave;

import java.util.ArrayList;
import java.util.List;

import commonj.sdo.DataObject;
import commonj.sdo.Type;
import junit.textui.TestRunner;
import org.eclipse.persistence.sdo.SDOConstants;
import org.eclipse.persistence.sdo.SDOType;

public class LoadAndSaveSimpleAttributeTestCases extends LoadAndSaveTestCases {
    public LoadAndSaveSimpleAttributeTestCases(String name) {
        super(name);
    }

    public static void main(String[] args) {
        String[] arguments = { "-c", "org.eclipse.persistence.testing.sdo.helper.xmlhelper.loadandsave.LoadAndSaveSimpleAttributeTestCases" };
        TestRunner.main(arguments);
    }

    protected String getSchemaName() {
        return "./org/eclipse/persistence/testing/sdo/helper/xmlhelper/Customer.xsd";
    }

    protected String getControlFileName() {
        return ("./org/eclipse/persistence/testing/sdo/helper/xmlhelper/simpleAttribute.xml");
    }

    protected String getControlRootURI() {
        return NON_DEFAULT_URI;
    }

    protected String getControlRootName() {
        return "customer";
    }

    protected String getRootInterfaceName() {
        return "CustomerType";
    }

    // Override package generation based on the JAXB 2.0 algorithm in SDOUtil.java
    protected List<String> getPackages() {
        List<String> packages = new ArrayList<String>();
        packages.add(NON_DEFAULT_JAVA_PACKAGE_DIR);
        return packages;
    }

    public void registerTypes() {
        Type intType = typeHelper.getType("commonj.sdo", "Int");
        Type stringType = typeHelper.getType("commonj.sdo", "String");
        SDOType propertyType = (SDOType) typeHelper.getType(SDOConstants.SDO_URL, SDOConstants.PROPERTY);

        // create a new Type for Customers
        DataObject customerType = dataFactory.create("commonj.sdo", "Type");
        customerType.set("uri", getControlRootURI());
        customerType.set("name", "customer");

        // create a first name property
        DataObject custIDProperty = customerType.createDataObject("property");
        custIDProperty.set("name", "customerID");
        custIDProperty.set("type", intType);

        // create a last name property
        DataObject sinProperty = customerType.createDataObject("property");
        sinProperty.set("name", "sin");
        sinProperty.set("type", stringType);

        // now define the Customer type so that customers can be made
        Type customerSDOType = typeHelper.define(customerType);

        DataObject propDO = dataFactory.create(propertyType);
        propDO.set("name", getControlRootName());
        propDO.set("type", customerSDOType);
        typeHelper.defineOpenContentProperty(getControlRootURI(), propDO);
    }
}
