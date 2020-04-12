/*
 * Copyright (c) 2016, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

package org.eclipse.persistence.testing.jaxb.xmlschema.defaultns.singleemptyprefix;

import java.lang.reflect.Type;

import org.eclipse.persistence.testing.jaxb.JAXBTestCases;

public class DefaultNamespaceSingleEmptyStringTestCases extends JAXBTestCases {
    protected final static String XML_RESOURCE = "org/eclipse/persistence/testing/jaxb/xmlschema/defaultns/singleemptyprefix/instance.xml";

    public DefaultNamespaceSingleEmptyStringTestCases(String name) throws Exception {
        super(name);
    }

    public void setUp() throws Exception {
        setControlDocument(XML_RESOURCE);

        super.setUp();
        Type[] types = new Type[1];
        types[0] = Person.class;
        setTypes(types);
    }

    protected Object getControlObject() {
        Person p = new Person();
        p.name = "Bob Smith";
        return p;
    }

}
