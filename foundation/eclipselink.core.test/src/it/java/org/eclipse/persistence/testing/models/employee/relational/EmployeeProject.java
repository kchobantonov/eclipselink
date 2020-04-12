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
package org.eclipse.persistence.testing.models.employee.relational;

import org.eclipse.persistence.sessions.*;
import org.eclipse.persistence.descriptors.*;
import org.eclipse.persistence.mappings.*;
import org.eclipse.persistence.mappings.converters.*;
import org.eclipse.persistence.queries.*;
import org.eclipse.persistence.expressions.ExpressionBuilder;

/**
 * This class was generated by the TopLink project class generator.
 * It stores the meta-data (descriptors) that define the TopLink mappings.
 * ## OracleAS TopLink - 10g (10.1.3 ) (Build 040713) ##
 * @see org.eclipse.persistence.sessions.factories.ProjectClassGenerator
 */
public class EmployeeProject extends org.eclipse.persistence.sessions.Project {
    public EmployeeProject() {
        setName("Employee");
        applyLogin();

        addDescriptor(buildAddressDescriptor());
        addDescriptor(buildChildDescriptor());
        addDescriptor(buildEmployeeDescriptor());
        addDescriptor(buildEmploymentPeriodDescriptor());
        addDescriptor(buildLargeProjectDescriptor());
        addDescriptor(buildPhoneNumberDescriptor());
        addDescriptor(buildProjectDescriptor());
        addDescriptor(buildSmallProjectDescriptor());
    }

    public void applyLogin() {
        DatabaseLogin login = new DatabaseLogin();
        login.usePlatform(new org.eclipse.persistence.platform.database.OraclePlatform());
        login.setDriverClassName("oracle.jdbc.OracleDriver");
        login.setConnectionString("jdbc:oracle:thin:@localhost:1521:orcl");
        login.setUserName("scott");
        login.setEncryptedPassword("3E20F8982C53F4ABA825E30206EC8ADE");
        login.setUsesExternalConnectionPooling(false);
        login.setUsesExternalTransactionController(false);

        setDatasourceLogin(login);
    }

    public ClassDescriptor buildAddressDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.Address.class);
        descriptor.addTableName("ADDRESS");
        descriptor.addPrimaryKeyFieldName("ADDRESS.ADDRESS_ID");

        // Descriptor Properties.
        descriptor.useSoftCacheWeakIdentityMap();
        descriptor.setIdentityMapSize(100);
        descriptor.setSequenceNumberFieldName("ADDRESS.ADDRESS_ID");
        descriptor.setSequenceNumberName("ADDRESS_SEQ");
        descriptor.setAlias("Address");

        // Cache Invalidation Policy
        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Named Queries.
        // Event Manager.
        // Mappings.
        DirectToFieldMapping cityMapping = new DirectToFieldMapping();
        cityMapping.setAttributeName("city");
        cityMapping.setFieldName("ADDRESS.CITY");
        descriptor.addMapping(cityMapping);

        DirectToFieldMapping countryMapping = new DirectToFieldMapping();
        countryMapping.setAttributeName("country");
        countryMapping.setFieldName("ADDRESS.COUNTRY");
        descriptor.addMapping(countryMapping);

        DirectToFieldMapping idMapping = new DirectToFieldMapping();
        idMapping.setAttributeName("id");
        idMapping.setFieldName("ADDRESS.ADDRESS_ID");
        descriptor.addMapping(idMapping);

        DirectToFieldMapping postalCodeMapping = new DirectToFieldMapping();
        postalCodeMapping.setAttributeName("postalCode");
        postalCodeMapping.setFieldName("ADDRESS.P_CODE");
        descriptor.addMapping(postalCodeMapping);

        DirectToFieldMapping provinceMapping = new DirectToFieldMapping();
        provinceMapping.setAttributeName("province");
        provinceMapping.setFieldName("ADDRESS.PROVINCE");
        descriptor.addMapping(provinceMapping);

        DirectToFieldMapping streetMapping = new DirectToFieldMapping();
        streetMapping.setAttributeName("street");
        streetMapping.setFieldName("ADDRESS.STREET");
        descriptor.addMapping(streetMapping);

        return descriptor;
    }

    public ClassDescriptor buildChildDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.Child.class);
        descriptor.addTableName("CHILD");
        descriptor.addPrimaryKeyFieldName("CHILD.CHILD_ID");

        descriptor.useSoftCacheWeakIdentityMap();
        descriptor.setIdentityMapSize(50);
        descriptor.setSequenceNumberFieldName("CHILD.CHILD_ID");
        descriptor.setSequenceNumberName("CHILD_SEQ");
        descriptor.setAlias("Child");

        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Mappings.
        DirectToFieldMapping firstNameMapping = new DirectToFieldMapping();
        firstNameMapping.setAttributeName("firstName");
        firstNameMapping.setFieldName("F_NAME");
        firstNameMapping.setNullValue("");
        descriptor.addMapping(firstNameMapping);

        DirectToFieldMapping idMapping = new DirectToFieldMapping();
        idMapping.setAttributeName("id");
        idMapping.setFieldName("CHILD_ID");
        descriptor.addMapping(idMapping);

        DirectToFieldMapping lastNameMapping = new DirectToFieldMapping();
        lastNameMapping.setAttributeName("lastName");
        lastNameMapping.setFieldName("L_NAME");
        lastNameMapping.setNullValue("");
        descriptor.addMapping(lastNameMapping);

        DirectToFieldMapping genderMapping = new DirectToFieldMapping();
        genderMapping.setAttributeName("gender");
        genderMapping.setFieldName("GENDER");
        ObjectTypeConverter genderMappingConverter = new ObjectTypeConverter();
        genderMappingConverter.addConversionValue("F", "Female");
        genderMappingConverter.addConversionValue("M", "Male");
        genderMapping.setConverter(genderMappingConverter);
        descriptor.addMapping(genderMapping);

        DirectToFieldMapping birthdayMapping = new DirectToFieldMapping();
        birthdayMapping.setAttributeName("birthday");
        birthdayMapping.setFieldName("BIRTHDAY");
        descriptor.addMapping(birthdayMapping);

        OneToOneMapping managerMapping = new OneToOneMapping();
        managerMapping.setAttributeName("parent");
        managerMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        managerMapping.dontUseIndirection();
        managerMapping.addForeignKeyFieldName("CHILD.PARENT_EMP_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(managerMapping);


        return descriptor;
    }

    public ClassDescriptor buildEmployeeDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        descriptor.addTableName("EMPLOYEE");
        descriptor.addTableName("SALARY");
        descriptor.addPrimaryKeyFieldName("EMPLOYEE.EMP_ID");

    // Interface Properties.
    descriptor.getInterfacePolicy().addParentInterface(org.eclipse.persistence.testing.models.employee.interfaces.Employee.class);
        // Descriptor Properties.
        descriptor.useSoftCacheWeakIdentityMap();
        descriptor.setIdentityMapSize(100);
        descriptor.getCachePolicy().addCacheIndex("F_NAME", "L_NAME");
        descriptor.setSequenceNumberFieldName("EMPLOYEE.EMP_ID");
        descriptor.setSequenceNumberName("EMP_SEQ");
        VersionLockingPolicy lockingPolicy = new VersionLockingPolicy();
        lockingPolicy.setWriteLockFieldName("EMPLOYEE.VERSION");
        descriptor.setOptimisticLockingPolicy(lockingPolicy);
        descriptor.setAlias("Employee");

        // Cache Invalidation Policy
        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Named Queries.
        // Event Manager.
        // Mappings.
        DirectToFieldMapping firstNameMapping = new DirectToFieldMapping();
        firstNameMapping.setAttributeName("firstName");
        firstNameMapping.setFieldName("EMPLOYEE.F_NAME");
        firstNameMapping.setNullValue("");
        descriptor.addMapping(firstNameMapping);

        DirectToFieldMapping idMapping = new DirectToFieldMapping();
        idMapping.setAttributeName("id");
        idMapping.setFieldName("EMPLOYEE.EMP_ID");
        descriptor.addMapping(idMapping);

        DirectToFieldMapping lastNameMapping = new DirectToFieldMapping();
        lastNameMapping.setAttributeName("lastName");
        lastNameMapping.setFieldName("EMPLOYEE.L_NAME");
        lastNameMapping.setNullValue("");
        descriptor.addMapping(lastNameMapping);

        DirectToFieldMapping salaryMapping = new DirectToFieldMapping();
        salaryMapping.setAttributeName("salary");
        salaryMapping.setFieldName("SALARY.SALARY");
        descriptor.addMapping(salaryMapping);

        DirectToFieldMapping genderMapping = new DirectToFieldMapping();
        genderMapping.setAttributeName("gender");
        genderMapping.setFieldName("EMPLOYEE.GENDER");
        ObjectTypeConverter genderMappingConverter = new ObjectTypeConverter();
        genderMappingConverter.addConversionValue("F", "Female");
        genderMappingConverter.addConversionValue("M", "Male");
        genderMapping.setConverter(genderMappingConverter);
        descriptor.addMapping(genderMapping);

    TransformationMapping normalHoursMapping = new TransformationMapping();
    normalHoursMapping.setAttributeName("normalHours");
    normalHoursMapping.setAttributeTransformation("buildNormalHours");
    normalHoursMapping.addFieldTransformation("EMPLOYEE.START_TIME", "getStartTime");
    normalHoursMapping.addFieldTransformation("EMPLOYEE.END_TIME", "getEndTime");
    normalHoursMapping.setIsMutable(true);
    descriptor.addMapping(normalHoursMapping);

        AggregateObjectMapping periodMapping = new AggregateObjectMapping();
        periodMapping.setAttributeName("period");
        periodMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.EmploymentPeriod.class);
        periodMapping.setIsNullAllowed(true);
        periodMapping.addFieldNameTranslation("EMPLOYEE.END_DATE", "endDate->DIRECT");
        periodMapping.addFieldNameTranslation("EMPLOYEE.START_DATE", "startDate->DIRECT");
        descriptor.addMapping(periodMapping);

        DirectCollectionMapping responsibilitiesListMapping = new DirectCollectionMapping();
        responsibilitiesListMapping.setAttributeName("responsibilitiesList");
        responsibilitiesListMapping.useBasicIndirection();
        responsibilitiesListMapping.setReferenceTableName("RESPONS");
        responsibilitiesListMapping.setDirectFieldName("RESPONS.DESCRIP");
        responsibilitiesListMapping.addReferenceKeyFieldName("RESPONS.EMP_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(responsibilitiesListMapping);

        OneToOneMapping addressMapping = new OneToOneMapping();
        addressMapping.setAttributeName("address");
        addressMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Address.class);
        addressMapping.useBasicIndirection();
        addressMapping.privateOwnedRelationship();
        addressMapping.addForeignKeyFieldName("EMPLOYEE.ADDR_ID", "ADDRESS.ADDRESS_ID");
        descriptor.addMapping(addressMapping);

        //Joel:EJBQLTesting
        OneToOneMapping managerMapping = new OneToOneMapping();
        managerMapping.setAttributeName("manager");
        managerMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        managerMapping.useBasicIndirection();
        managerMapping.addForeignKeyFieldName("EMPLOYEE.MANAGER_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(managerMapping);

        OneToManyMapping managedEmployeesMapping = new OneToManyMapping();
        managedEmployeesMapping.setAttributeName("managedEmployees");
        managedEmployeesMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        managedEmployeesMapping.useBasicIndirection();
        managedEmployeesMapping.addTargetForeignKeyFieldName("EMPLOYEE.MANAGER_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(managedEmployeesMapping);

        OneToManyMapping childrenMapping = new OneToManyMapping();
        childrenMapping.setAttributeName("children");
        childrenMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Child.class);
        childrenMapping.addAscendingOrdering("birthday");
        childrenMapping.useTransparentList();
        childrenMapping.privateOwnedRelationship();
        childrenMapping.addTargetForeignKeyFieldName("CHILD.PARENT_EMP_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(childrenMapping);

        OneToManyMapping phoneNumbersMapping = new OneToManyMapping();
        phoneNumbersMapping.setAttributeName("phoneNumbers");
        phoneNumbersMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.PhoneNumber.class);
        phoneNumbersMapping.useBasicIndirection();
        phoneNumbersMapping.privateOwnedRelationship();
        phoneNumbersMapping.addTargetForeignKeyFieldName("PHONE.EMP_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(phoneNumbersMapping);

        ManyToManyMapping projectsMapping = new ManyToManyMapping();
        projectsMapping.setAttributeName("projects");
        projectsMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);
        projectsMapping.useBasicIndirection();
        projectsMapping.setRelationTableName("PROJ_EMP");
        projectsMapping.addSourceRelationKeyFieldName("PROJ_EMP.EMP_ID", "EMPLOYEE.EMP_ID");
        projectsMapping.addTargetRelationKeyFieldName("PROJ_EMP.PROJ_ID", "PROJECT.PROJ_ID");
        descriptor.addMapping(projectsMapping);

        return descriptor;
    }

    public ClassDescriptor buildEmploymentPeriodDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.descriptorIsAggregate();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.EmploymentPeriod.class);

        // Descriptor Properties.
        descriptor.setAlias("EmploymentPeriod");

        // Cache Invalidation Policy
        // Query Manager.
        // Named Queries.
        // Event Manager.
        // Mappings.
        DirectToFieldMapping endDateMapping = new DirectToFieldMapping();
        endDateMapping.setAttributeName("endDate");
        endDateMapping.setFieldName("endDate->DIRECT");
        descriptor.addMapping(endDateMapping);

        DirectToFieldMapping startDateMapping = new DirectToFieldMapping();
        startDateMapping.setAttributeName("startDate");
        startDateMapping.setFieldName("startDate->DIRECT");
        descriptor.addMapping(startDateMapping);

        return descriptor;
    }

    public ClassDescriptor buildLargeProjectDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.LargeProject.class);
        descriptor.addTableName("LPROJECT");

        // Inheritance Properties.
        descriptor.getInheritancePolicy().setParentClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);
        descriptor.getInheritancePolicy().dontReadSubclassesOnQueries();

    // Interface Properties.
    descriptor.getInterfacePolicy().addParentInterface(org.eclipse.persistence.testing.models.employee.interfaces.LargeProject.class);

        // Descriptor Properties.
        descriptor.setAlias("LargeProject");

        // Cache Invalidation Policy
        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Named Queries.
        // Event Manager.
        // Mappings.
        DirectToFieldMapping budgetMapping = new DirectToFieldMapping();
        budgetMapping.setAttributeName("budget");
        budgetMapping.setFieldName("LPROJECT.BUDGET");
        descriptor.addMapping(budgetMapping);

        DirectToFieldMapping milestoneVersionMapping = new DirectToFieldMapping();
        milestoneVersionMapping.setAttributeName("milestoneVersion");
        milestoneVersionMapping.setFieldName("LPROJECT.MILESTONE");
        descriptor.addMapping(milestoneVersionMapping);

        return descriptor;
    }

    public ClassDescriptor buildPhoneNumberDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.PhoneNumber.class);
        descriptor.addTableName("PHONE");
        descriptor.addPrimaryKeyFieldName("PHONE.EMP_ID");
        descriptor.addPrimaryKeyFieldName("PHONE.TYPE");

        // Descriptor Properties.
        descriptor.useSoftCacheWeakIdentityMap();
        descriptor.setIdentityMapSize(100);
        descriptor.setAlias("PhoneNumber");

        // Cache Invalidation Policy
        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Named Queries.
        //Named Query -- localNumbers
        ReadObjectQuery namedQuery0 = new ReadObjectQuery(org.eclipse.persistence.testing.models.employee.domain.PhoneNumber.class);
        namedQuery0.setName("localNumbers");
        namedQuery0.setCascadePolicy(1);
        namedQuery0.setShouldUseWrapperPolicy(true);
        namedQuery0.setShouldMaintainCache(true);
        namedQuery0.setShouldPrepare(true);
        namedQuery0.setMaxRows(0);
        namedQuery0.setShouldRefreshIdentityMapResult(false);
        namedQuery0.setCacheUsage(2);
        namedQuery0.setLockMode((short)0);
        namedQuery0.setDistinctState((short)0);
        namedQuery0.setInMemoryQueryIndirectionPolicy(new InMemoryQueryIndirectionPolicy(0));
        ExpressionBuilder expBuilder0 = namedQuery0.getExpressionBuilder();
        namedQuery0.setSelectionCriteria(expBuilder0.get("id").equal(expBuilder0.getParameter("ID")).and(expBuilder0.get("areaCode").equal("613")));
        namedQuery0.addArgument("ID", java.lang.Number.class);
        descriptor.getQueryManager().addQuery("localNumbers", namedQuery0);

        // Event Manager.
        // Query keys.
        descriptor.addDirectQueryKey("id", "PHONE.EMP_ID");

        // Mappings.
        DirectToFieldMapping areaCodeMapping = new DirectToFieldMapping();
        areaCodeMapping.setAttributeName("areaCode");
        areaCodeMapping.setFieldName("PHONE.AREA_CODE");
        descriptor.addMapping(areaCodeMapping);

        DirectToFieldMapping numberMapping = new DirectToFieldMapping();
        numberMapping.setAttributeName("number");
        numberMapping.setFieldName("PHONE.P_NUMBER");
        descriptor.addMapping(numberMapping);

        DirectToFieldMapping typeMapping = new DirectToFieldMapping();
        typeMapping.setAttributeName("type");
        typeMapping.setFieldName("PHONE.TYPE");
        descriptor.addMapping(typeMapping);

        OneToOneMapping ownerMapping = new OneToOneMapping();
        ownerMapping.setAttributeName("owner");
        ownerMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        ownerMapping.useBasicIndirection();
        ownerMapping.addForeignKeyFieldName("PHONE.EMP_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(ownerMapping);

        return descriptor;
    }

    public ClassDescriptor buildProjectDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);
        descriptor.addTableName("PROJECT");
        descriptor.addPrimaryKeyFieldName("PROJECT.PROJ_ID");

        // Inheritance Properties.
        descriptor.getInheritancePolicy().setClassIndicatorFieldName("PROJECT.PROJ_TYPE");
        descriptor.getInheritancePolicy().addClassIndicator(org.eclipse.persistence.testing.models.employee.domain.SmallProject.class, "S");
        descriptor.getInheritancePolicy().addClassIndicator(org.eclipse.persistence.testing.models.employee.domain.LargeProject.class, "L");

    // Interface Properties.
    descriptor.getInterfacePolicy().addParentInterface(org.eclipse.persistence.testing.models.employee.interfaces.Project.class);

        // Descriptor Properties.
        descriptor.useSoftCacheWeakIdentityMap();
        descriptor.setIdentityMapSize(100);
        descriptor.setSequenceNumberFieldName("PROJECT.PROJ_ID");
        descriptor.setSequenceNumberName("PROJ_SEQ");
        VersionLockingPolicy lockingPolicy = new VersionLockingPolicy();
        lockingPolicy.setWriteLockFieldName("PROJECT.VERSION");
        descriptor.setOptimisticLockingPolicy(lockingPolicy);
        descriptor.setAlias("Project");

        // Cache Invalidation Policy
        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Named Queries.
        // Event Manager.
        // Mappings.
        DirectToFieldMapping descriptionMapping = new DirectToFieldMapping();
        descriptionMapping.setAttributeName("description");
        descriptionMapping.setFieldName("PROJECT.DESCRIP");
        descriptionMapping.setNullValue("");
        descriptor.addMapping(descriptionMapping);

        DirectToFieldMapping idMapping = new DirectToFieldMapping();
        idMapping.setAttributeName("id");
        idMapping.setFieldName("PROJECT.PROJ_ID");
        descriptor.addMapping(idMapping);

        DirectToFieldMapping nameMapping = new DirectToFieldMapping();
        nameMapping.setAttributeName("name");
        nameMapping.setFieldName("PROJECT.PROJ_NAME");
        nameMapping.setNullValue("");
        descriptor.addMapping(nameMapping);

        OneToOneMapping teamLeaderMapping = new OneToOneMapping();
        teamLeaderMapping.setAttributeName("teamLeader");
        teamLeaderMapping.setReferenceClass(org.eclipse.persistence.testing.models.employee.domain.Employee.class);
        teamLeaderMapping.useBasicIndirection();
        teamLeaderMapping.addForeignKeyFieldName("PROJECT.LEADER_ID", "EMPLOYEE.EMP_ID");
        descriptor.addMapping(teamLeaderMapping);

        return descriptor;
    }

    public ClassDescriptor buildSmallProjectDescriptor() {
        RelationalDescriptor descriptor = new RelationalDescriptor();
        descriptor.setJavaClass(org.eclipse.persistence.testing.models.employee.domain.SmallProject.class);
        descriptor.addTableName("PROJECT");

        // Inheritance Properties.
        descriptor.getInheritancePolicy().setParentClass(org.eclipse.persistence.testing.models.employee.domain.Project.class);
        descriptor.getInheritancePolicy().dontReadSubclassesOnQueries();

    // Interface Properties.
    descriptor.getInterfacePolicy().addParentInterface(org.eclipse.persistence.testing.models.employee.interfaces.SmallProject.class);

        // Descriptor Properties.
        descriptor.setAlias("SmallProject");

        // Cache Invalidation Policy
        // Query Manager.
        descriptor.getQueryManager().checkCacheForDoesExist();

        // Named Queries.
        // Event Manager.
        return descriptor;
    }
}
