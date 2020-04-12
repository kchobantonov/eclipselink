/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
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
// Martin Vojtek - July 8/2014
package org.eclipse.persistence.testing.jaxb.externalizedmetadata.xmlelementnillable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.testing.jaxb.JAXBTestCases;
import org.eclipse.persistence.testing.jaxb.externalizedmetadata.xmlelementnillable.packagetypeoverride.PackageTypeOverride;

/**
 * Test demonstrates that XmlElementNillable annotation declared on class overrides xml-element-nillable element declared in oxm.xml file (package level).
 */
public class XmlElementNillablePackageTypeOverrideTestCases extends JAXBTestCases {

    private final static String XML_CONTROL_RESOURCE = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/xmlelementnillable/packagetypeoverride/package_type_override_control.xml";

    private final static String BINDINGS_DOC = "org/eclipse/persistence/testing/jaxb/externalizedmetadata/xmlelementnillable/packagetypeoverride/eclipselink-oxm.xml";

    public XmlElementNillablePackageTypeOverrideTestCases(String name) throws Exception {
        super(name);
        setControlDocument(XML_CONTROL_RESOURCE);
        setClasses(new Class[] { PackageTypeOverride.class });
    }

    public Map getProperties() {

        InputStream iStream = ClassLoader.getSystemResourceAsStream(BINDINGS_DOC);

        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, iStream);

        return properties;
    }

    @Override
    protected PackageTypeOverride getControlObject() {
        PackageTypeOverride controlObject = new PackageTypeOverride();
        controlObject.setB("B");

        return controlObject;
    }

    @Override
    public boolean isUnmarshalTest() {
        return false;
    }

}
