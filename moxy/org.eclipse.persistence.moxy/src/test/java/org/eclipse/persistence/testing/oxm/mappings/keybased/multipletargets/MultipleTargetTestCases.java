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
package org.eclipse.persistence.testing.oxm.mappings.keybased.multipletargets;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//import org.eclipse.persistence.testing.oxm.mappings.keybased.multipletargets.compositekey.CompositeKeyTestCases;
import org.eclipse.persistence.testing.oxm.mappings.keybased.multipletargets.singlekey.SingleKeyTestCases;

public class MultipleTargetTestCases extends TestCase {
    public static Test suite() {
        TestSuite suite = new TestSuite("Multiple target test suite");
        suite.addTest(SingleKeyTestCases.suite());

        // Testing fix for bug# 5683210
        //suite.addTest(CompositeKeyTestCases.suite());
        return suite;
    }

    public static void main(String[] args) {
        String[] arguments = { "-c", "org.eclipse.persistence.testing.oxm.mappings.keybased.multipletargets.MultipleTargetTestCases"};
        junit.textui.TestRunner.main(arguments);
    }
}
