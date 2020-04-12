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
package org.eclipse.persistence.testing.tests.unitofwork.writechanges;

import org.eclipse.persistence.sessions.UnitOfWork;
import org.eclipse.persistence.testing.framework.AutoVerifyTestCase;


/**
 *  @author  smcritch
 */
public class WriteChanges_CommitAndResume_TestCase extends AutoVerifyTestCase {
    public WriteChanges_CommitAndResume_TestCase() {
        super();
    }

    public void test() {
        UnitOfWork uow = getSession().acquireUnitOfWork();
        uow.writeChanges();
        uow.commitAndResume();
    }
}
