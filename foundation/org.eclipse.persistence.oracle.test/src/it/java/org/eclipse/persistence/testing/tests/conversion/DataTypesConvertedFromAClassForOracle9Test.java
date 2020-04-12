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
package org.eclipse.persistence.testing.tests.conversion;

import org.eclipse.persistence.platform.database.oracle.Oracle9Platform;
import org.eclipse.persistence.testing.framework.TestWarningException;

// This test retrieves all the classes that can be converted from a given class by
// calling getDataTypesConvertedFrom() in Oracle9Platform.
public class DataTypesConvertedFromAClassForOracle9Test extends DataTypesConvertedFromAClassTest {

    public DataTypesConvertedFromAClassForOracle9Test() {
        setDescription("Test getDataTypesConvertedFrom() in Oracle9Platform.");
    }

    public void setup() {
        if(!getSession().getPlatform().isOracle()) {
            throw new TestWarningException("This test requires Oracle database");
        }
        cm = getSession().getPlatform();
    }

    protected boolean isChar(Class aClass) {
        return super.isChar(aClass) || aClass == Oracle9Platform.NCHAR || aClass == Oracle9Platform.NSTRING ||
            aClass == Oracle9Platform.NCLOB;
    }
}
