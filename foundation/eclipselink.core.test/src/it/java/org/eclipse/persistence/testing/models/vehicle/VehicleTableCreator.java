/*
 * Copyright (c) 2011, 2020 Oracle and/or its affiliates. All rights reserved.
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
//     Vikram Bhatia - initial API and implementation
package org.eclipse.persistence.testing.models.vehicle;

import org.eclipse.persistence.tools.schemaframework.FieldDefinition;
import org.eclipse.persistence.tools.schemaframework.TableDefinition;


/**
 * This class was generated by the TopLink table creator generator.
 * It stores the meta-data (tables) that define the database schema.
 * @see org.eclipse.persistence.sessions.factories.TableCreatorClassGenerator
 */

public class VehicleTableCreator extends org.eclipse.persistence.tools.schemaframework.TableCreator {

    public VehicleTableCreator() {
        setName("Vehicle");

        addTableDefinition(buildCAROWNERTable());
        addTableDefinition(buildSPORTSCARTable());
        addTableDefinition(buildFUELTYPETable());
        addTableDefinition(buildENGINETYPETable());
    }

    public TableDefinition buildCAROWNERTable() {
        TableDefinition table = new TableDefinition();
        table.setName("CAROWNER");

        FieldDefinition fieldCARID = new FieldDefinition();
        fieldCARID.setName("CARID");
        fieldCARID.setTypeName("NUMBER");
        fieldCARID.setSize(4);
        fieldCARID.setSubSize(0);
        fieldCARID.setIsPrimaryKey(false);
        fieldCARID.setIsIdentity(false);
        fieldCARID.setUnique(false);
        fieldCARID.setShouldAllowNull(false);
        table.addField(fieldCARID);

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(8);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldLASTCARID = new FieldDefinition();
        fieldLASTCARID.setName("LASTCARID");
        fieldLASTCARID.setTypeName("NUMBER");
        fieldLASTCARID.setSize(4);
        fieldLASTCARID.setSubSize(0);
        fieldLASTCARID.setIsPrimaryKey(false);
        fieldLASTCARID.setIsIdentity(false);
        fieldLASTCARID.setUnique(false);
        fieldLASTCARID.setShouldAllowNull(false);
        table.addField(fieldLASTCARID);

        FieldDefinition fieldNAME = new FieldDefinition();
        fieldNAME.setName("NAME");
        fieldNAME.setTypeName("VARCHAR2");
        fieldNAME.setSize(60);
        fieldNAME.setSubSize(0);
        fieldNAME.setIsPrimaryKey(false);
        fieldNAME.setIsIdentity(false);
        fieldNAME.setUnique(false);
        fieldNAME.setShouldAllowNull(false);
        table.addField(fieldNAME);

        return table;
    }

    public TableDefinition buildENGINETYPETable() {
        TableDefinition table = new TableDefinition();
        table.setName("ENGINETYPE");

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(4);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        FieldDefinition fieldTYPE = new FieldDefinition();
        fieldTYPE.setName("TYPE");
        fieldTYPE.setTypeName("VARCHAR2");
        fieldTYPE.setSize(40);
        fieldTYPE.setSubSize(0);
        fieldTYPE.setIsPrimaryKey(false);
        fieldTYPE.setIsIdentity(false);
        fieldTYPE.setUnique(false);
        fieldTYPE.setShouldAllowNull(false);
        table.addField(fieldTYPE);

        return table;
    }

    public TableDefinition buildFUELTYPETable() {
        TableDefinition table = new TableDefinition();
        table.setName("FUELTYPE");

        FieldDefinition fieldDESCRIPTION = new FieldDefinition();
        fieldDESCRIPTION.setName("DESCRIPTION");
        fieldDESCRIPTION.setTypeName("VARCHAR2");
        fieldDESCRIPTION.setSize(60);
        fieldDESCRIPTION.setSubSize(0);
        fieldDESCRIPTION.setIsPrimaryKey(false);
        fieldDESCRIPTION.setIsIdentity(false);
        fieldDESCRIPTION.setUnique(false);
        fieldDESCRIPTION.setShouldAllowNull(false);
        table.addField(fieldDESCRIPTION);

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(4);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        return table;
    }

    public TableDefinition buildSPORTSCARTable() {
        TableDefinition table = new TableDefinition();
        table.setName("SPORTSCAR");

        FieldDefinition fieldDESCRIPTION = new FieldDefinition();
        fieldDESCRIPTION.setName("DESCRIPTION");
        fieldDESCRIPTION.setTypeName("VARCHAR2");
        fieldDESCRIPTION.setSize(20);
        fieldDESCRIPTION.setSubSize(0);
        fieldDESCRIPTION.setIsPrimaryKey(false);
        fieldDESCRIPTION.setIsIdentity(false);
        fieldDESCRIPTION.setUnique(false);
        fieldDESCRIPTION.setShouldAllowNull(false);
        table.addField(fieldDESCRIPTION);

        FieldDefinition fieldENGINEID = new FieldDefinition();
        fieldENGINEID.setName("ENGINEID");
        fieldENGINEID.setTypeName("NUMBER");
        fieldENGINEID.setSize(4);
        fieldENGINEID.setSubSize(0);
        fieldENGINEID.setIsPrimaryKey(false);
        fieldENGINEID.setIsIdentity(false);
        fieldENGINEID.setUnique(false);
        fieldENGINEID.setShouldAllowNull(false);
        table.addField(fieldENGINEID);

        FieldDefinition fieldFUELCAPACITY = new FieldDefinition();
        fieldFUELCAPACITY.setName("FUELCAPACITY");
        fieldFUELCAPACITY.setTypeName("NUMBER");
        fieldFUELCAPACITY.setSize(4);
        fieldFUELCAPACITY.setSubSize(0);
        fieldFUELCAPACITY.setIsPrimaryKey(false);
        fieldFUELCAPACITY.setIsIdentity(false);
        fieldFUELCAPACITY.setUnique(false);
        fieldFUELCAPACITY.setShouldAllowNull(false);
        table.addField(fieldFUELCAPACITY);

        FieldDefinition fieldFUELID = new FieldDefinition();
        fieldFUELID.setName("FUELID");
        fieldFUELID.setTypeName("NUMBER");
        fieldFUELID.setSize(4);
        fieldFUELID.setSubSize(0);
        fieldFUELID.setIsPrimaryKey(false);
        fieldFUELID.setIsIdentity(false);
        fieldFUELID.setUnique(false);
        fieldFUELID.setShouldAllowNull(false);
        table.addField(fieldFUELID);

        FieldDefinition fieldID = new FieldDefinition();
        fieldID.setName("ID");
        fieldID.setTypeName("NUMBER");
        fieldID.setSize(4);
        fieldID.setSubSize(0);
        fieldID.setIsPrimaryKey(true);
        fieldID.setIsIdentity(false);
        fieldID.setUnique(false);
        fieldID.setShouldAllowNull(false);
        table.addField(fieldID);

        return table;
    }
}
