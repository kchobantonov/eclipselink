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
package org.eclipse.persistence.testing.tests.simplepojoclient;

import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.testing.framework.OracleDBPlatformHelper;
import org.eclipse.persistence.descriptors.*;
import org.eclipse.persistence.mappings.*;
import org.eclipse.persistence.mappings.converters.*;

/**
 * This class was generated by the TopLink project class generator.
 * It stores the meta-data (descriptors) that define the TopLink mappings.
 * ## Oracle TopLink - 10g release 2 (10.1.3.0.0) (Build 050411) ##
 * @see org.eclipse.persistence.sessions.factories.ProjectClassGenerator
 */
public class

PojoEmployeeProject extends org.eclipse.persistence.sessions.Project {

    public PojoEmployeeProject() {
        setName("SimplePojoTest");
        applyLogin();

        addDescriptor(buildPojoEmployeeDescriptor());
    }

    public void applyLogin() {
        DatabaseLogin login = new DatabaseLogin();
        login.usePlatform(OracleDBPlatformHelper.getInstance().getOracle9Platform());
        login.setDriverClassName("oracle.jdbc.OracleDriver");
        login.setConnectionString("jdbc:oracle:thin:@tlsvrdb5.ca.oracle.com:TOPLINK");
        login.setUserName("qa9");
        login.setEncryptedPassword("BB742416276274A46959A54867978929");

        // Configuration Properties.

        setDatasourceLogin(login);
    }

    public ClassDescriptor buildPojoEmployeeDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.tests.simplepojoclient.PojoEmployee.class);
        descriptor.addTableName("POJO_EMPLOYEE");
        descriptor.addPrimaryKeyFieldName("POJO_EMPLOYEE.EMP_ID");

        // Descriptor Properties.
        descriptor.useSoftCacheWeakIdentityMap();
        descriptor.setIdentityMapSize(100);
        descriptor.useRemoteSoftCacheWeakIdentityMap();
        descriptor.setRemoteIdentityMapSize(100);
        descriptor.setAlias("PojoEmployee");


        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();


        // Event Manager.

        // Mappings.
        DirectToFieldMapping empIdMapping = new DirectToFieldMapping();
        empIdMapping.setAttributeName("empId");
        empIdMapping.setGetMethodName("getEmpId");
        empIdMapping.setSetMethodName("setEmpId");
        empIdMapping.setFieldName("POJO_EMPLOYEE.EMP_ID");
        descriptor.addMapping(empIdMapping);

        DirectToFieldMapping fNameMapping = new DirectToFieldMapping();
        fNameMapping.setAttributeName("fName");
        fNameMapping.setGetMethodName("getFName");
        fNameMapping.setSetMethodName("setFName");
        fNameMapping.setFieldName("POJO_EMPLOYEE.F_NAME");
        descriptor.addMapping(fNameMapping);

        DirectToFieldMapping genderMapping = new DirectToFieldMapping();
        genderMapping.setAttributeName("gender");
        genderMapping.setGetMethodName("getGender");
        genderMapping.setSetMethodName("setGender");
        genderMapping.setFieldName("POJO_EMPLOYEE.GENDER");
        ObjectTypeConverter genderMappingConverter = new ObjectTypeConverter();
        genderMappingConverter.addConversionValue(new Character('F'), "Female");
        genderMappingConverter.addConversionValue(new Character('M'), "Male");
        genderMapping.setConverter(genderMappingConverter);
        descriptor.addMapping(genderMapping);

        DirectToFieldMapping lNameMapping = new DirectToFieldMapping();
        lNameMapping.setAttributeName("lName");
        lNameMapping.setGetMethodName("getLName");
        lNameMapping.setSetMethodName("setLName");
        lNameMapping.setFieldName("POJO_EMPLOYEE.L_NAME");
        descriptor.addMapping(lNameMapping);

        DirectToFieldMapping managerIdMapping = new DirectToFieldMapping();
        managerIdMapping.setAttributeName("managerId");
        managerIdMapping.setGetMethodName("getManagerId");
        managerIdMapping.setSetMethodName("setManagerId");
        managerIdMapping.setFieldName("POJO_EMPLOYEE.MANAGER_ID");
        descriptor.addMapping(managerIdMapping);

        return descriptor;
    }

}
