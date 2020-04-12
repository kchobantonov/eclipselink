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
package org.eclipse.persistence.testing.sdo.helper.xmlhelper.loadandsave.changesummary;

import commonj.sdo.ChangeSummary;
import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import junit.textui.TestRunner;
import org.eclipse.persistence.sdo.SDOChangeSummary;

public class ChangeSummaryRootLoggingOffLoadAndSaveTestCases extends ChangeSummaryRootLoadAndSaveTestCases {
    public ChangeSummaryRootLoggingOffLoadAndSaveTestCases(String name) {
        super(name);
    }

    public static void main(String[] args) {
        String[] arguments = { "-c", "org.eclipse.persistence.testing.sdo.helper.xmlhelper.loadandsave.changesummary.ChangeSummaryRootLoggingOffLoadAndSaveTestCases" };
        TestRunner.main(arguments);
    }

    protected String getControlFileName() {
        return ("./org/eclipse/persistence/testing/sdo/helper/xmlhelper/changesummary/team_csroot_log_off.xml");
    }


    protected void verifyAfterLoad(XMLDocument document) {
        super.verifyAfterLoad(document);
        ChangeSummary teamCS = document.getRootObject().getChangeSummary();
        assertNotNull(teamCS);
        DataObject manager = document.getRootObject().getDataObject("manager");
        assertNotNull(manager);
        ChangeSummary managerCS = manager.getChangeSummary();
        assertEquals(teamCS, managerCS);
        assertFalse(teamCS.isLogging());
        assertFalse(((SDOChangeSummary)teamCS).isLogging());
    }
}
