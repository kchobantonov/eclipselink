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
package org.eclipse.persistence.testing.models.aggregate;

import org.eclipse.persistence.tools.schemaframework.*;

/**
 * This class was generated by the TopLink table creator generator.
 * It stores the meta-data (tables) that define the database schema.
 * @see org.eclipse.persistence.sessions.factories.TableCreatorClassGenerator
 */
public class SwitchTableCreator extends TableCreator {
    public SwitchTableCreator() {
        setName("Switch");

        addTableDefinition(buildSWITCHTable());
    }

    public TableDefinition buildSWITCHTable() {
        TableDefinition table = new TableDefinition();
        table.setName("SWITCH");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMERIC");
        fieldID.setSize(0);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(true);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldSTATE = new FieldDefinition();
        fieldSTATE.setName("STATE");
        fieldSTATE.setTypeName("VARCHAR");
        fieldSTATE.setSize(20);
        fieldSTATE.setSubSize(0);
        fieldSTATE.setIsPrimaryKey(false);
        fieldSTATE.setIsIdentity(false);
        fieldSTATE.setUnique(false);
        fieldSTATE.setShouldAllowNull(true);
        table.addField(fieldSTATE);

        return table;
    }
}
