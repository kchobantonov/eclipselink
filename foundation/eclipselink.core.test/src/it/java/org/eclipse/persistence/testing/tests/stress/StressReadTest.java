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
package org.eclipse.persistence.testing.tests.stress;

import org.eclipse.persistence.testing.framework.*;
import org.eclipse.persistence.expressions.*;

/**
 * Test insert many times.
 */

public class StressReadTest extends AutoVerifyTestCase {
    public int stressLevel;
public StressReadTest(int stressLevel)
{
    this.stressLevel = stressLevel;
}
public void test( )
{
    Address address = (Address)getSession().readObject(Address.class);
    for (int i = 0; i < stressLevel; i++) {
        getSession().readAllObjects(Address.class);
        getSession().readObject(Address.class, new ExpressionBuilder().get("id").equal(address.getId()));
    }
}
}
