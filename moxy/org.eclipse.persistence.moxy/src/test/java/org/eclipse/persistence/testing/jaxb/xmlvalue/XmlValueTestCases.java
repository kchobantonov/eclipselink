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
package org.eclipse.persistence.testing.jaxb.xmlvalue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.testing.jaxb.JAXBWithJSONTestCases;

public class XmlValueTestCases extends JAXBWithJSONTestCases {

    private final static String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlvalue/phone_number.xml";
    private final static String JSON_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlvalue/phone_number.json";
    private final static String CONTROL_NUMBER = "123-4567";

    public XmlValueTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_RESOURCE);
        setControlJSON(JSON_RESOURCE);
        Class[] classes = new Class[1];
        classes[0] = PhoneNumber.class;
        setClasses(classes);
    }

    protected Object getControlObject() {
        PhoneNumber pn = new PhoneNumber();
        pn.number = CONTROL_NUMBER;

        return pn;
    }


    public void testSchemaGen() throws Exception{
        InputStream controlInputStream = ClassLoader.getSystemResourceAsStream("org/eclipse/persistence/testing/jaxb/xmlvalue/phone_number.xsd");
        List<InputStream> controlSchemas = new ArrayList<InputStream>();
        controlSchemas.add(controlInputStream);
        this.testSchemaGen(controlSchemas);
    }
}
