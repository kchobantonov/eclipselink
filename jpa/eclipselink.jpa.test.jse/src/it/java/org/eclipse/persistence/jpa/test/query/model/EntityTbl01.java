/*
 * Copyright (c) 2018, 2020 IBM Corporation, Oracle, and/or affiliates. All rights reserved.
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
//     IBM - Bug 537795: CASE THEN and ELSE scalar expression Constants should not be casted to CASE operand type
package org.eclipse.persistence.jpa.test.query.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="SIMPLE_TBL01")
public class EntityTbl01 {

    @Id
    @Column(name="KEY_CHAR")
    private String KeyString;

    @Column(name="ITEM_STRING1")
    private String itemString1;

    @Column(name="ITEM_STRING2")
    private String itemString2;

    @Column(name="ITEM_STRING3")
    private String itemString3;

    @Column(name="ITEM_STRING4")
    private String itemString4;

    @Column(name="ITEM_INTEGER1")
    private Integer itemInteger1;

    public String getKeyString() {
        return KeyString;
    }
    public void setKeyString(String keyString) {
        KeyString = keyString;
    }
    public String getItemString1() {
        return itemString1;
    }
    public void setItemString1(String itemString1) {
        this.itemString1 = itemString1;
    }
    public String getItemString2() {
        return itemString2;
    }
    public void setItemString2(String itemString2) {
        this.itemString2 = itemString2;
    }
    public String getItemString3() {
        return itemString3;
    }
    public void setItemString3(String itemString3) {
        this.itemString3 = itemString3;
    }
    public String getItemString4() {
        return itemString4;
    }
    public void setItemString4(String itemString4) {
        this.itemString4 = itemString4;
    }
    public Integer getItemInteger1() {
        return itemInteger1;
    }
    public void setItemInteger1(Integer itemInteger1) {
        this.itemInteger1 = itemInteger1;
    }

    @Override
    public String toString() {
        return "EntityTbl01 [KeyString=" + KeyString + ", itemString1=" + itemString1 + ", itemString2=" + itemString2 + ", itemString3=" + itemString3 + ", itemString4=" + itemString4 + ", itemInteger1=" + itemInteger1 + "]";
    }
}
