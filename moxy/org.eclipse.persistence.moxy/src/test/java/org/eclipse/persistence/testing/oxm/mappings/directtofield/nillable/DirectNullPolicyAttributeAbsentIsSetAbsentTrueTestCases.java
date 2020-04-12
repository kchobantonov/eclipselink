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
package org.eclipse.persistence.testing.oxm.mappings.directtofield.nillable;

import org.eclipse.persistence.oxm.mappings.nullpolicy.AbstractNullPolicy;
import org.eclipse.persistence.oxm.mappings.nullpolicy.NullPolicy;
import org.eclipse.persistence.oxm.mappings.nullpolicy.XMLNullRepresentationType;

import org.eclipse.persistence.oxm.mappings.XMLDirectMapping;
import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.testing.oxm.mappings.XMLWithJSONMappingTestCases;

public class DirectNullPolicyAttributeAbsentIsSetAbsentTrueTestCases extends XMLWithJSONMappingTestCases {
    // TC  UC 6-1, 4-9
    private final static String XML_RESOURCE = //
    "org/eclipse/persistence/testing/oxm/mappings/directtofield/nillable/DirectNullPolicyAttributeAbsentIsSetAbsentTrue.xml";
    private final static String JSON_RESOURCE = //
    "org/eclipse/persistence/testing/oxm/mappings/directtofield/nillable/DirectNullPolicyAttributeAbsentIsSetAbsentTrue.json";

    public DirectNullPolicyAttributeAbsentIsSetAbsentTrueTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        setControlJSON(JSON_RESOURCE);
            AbstractNullPolicy aNullPolicy = new NullPolicy();
            // Alter unmarshal policy state
            ((NullPolicy)aNullPolicy).setSetPerformedForAbsentNode(true);
            aNullPolicy.setNullRepresentedByEmptyNode(false); // no effect
            aNullPolicy.setNullRepresentedByXsiNil(false);  // no effect
            // Alter marshal policy state
            aNullPolicy.setMarshalNullRepresentation(XMLNullRepresentationType.ABSENT_NODE);
            //((IsSetNullPolicy)aNullPolicy).setIsSetMethodName("isSetFirstName");
            Project aProject = new DirectNodeNullPolicyProject(false);
            XMLDirectMapping aMapping = (XMLDirectMapping)aProject.getDescriptor(Employee.class)//
            .getMappingForAttributeName("firstName");
            aMapping.setNullPolicy(aNullPolicy);
            setProject(aProject);
        }

        protected Object getControlObject() {
            Employee anEmployee = new Employee();
            anEmployee.setId(123);
            anEmployee.setFirstName(null);
            anEmployee.setLastName("Doe");
            return anEmployee;
        }
}
