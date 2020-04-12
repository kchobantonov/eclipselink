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
package org.eclipse.persistence.testing.oxm.xmlconversionmanager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XMLConversionManagerTestSuite extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite("XML Conversion Manager Test Suite");
        suite.addTestSuite(DateAndTimeTestCases.class);
        suite.addTestSuite(QNameTestCases.class);
        suite.addTestSuite(Base64TestCases.class);
        suite.addTestSuite(ListTestCases.class);
        suite.addTestSuite(DoubleToBigDecimalTestCases.class);
        suite.addTestSuite(XMLGregorianCalendarTestCases.class);
        suite.addTestSuite(NumberTestCases.class);
        suite.addTestSuite(BooleanTestCases.class);
        suite.addTestSuite(UrlTestCases.class);
        return suite;
    }

}
