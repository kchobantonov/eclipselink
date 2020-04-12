/*
 * Copyright (c) 2018, 2020 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2018 IBM Corporation. All rights reserved.
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
//     10/01/2018: Will Dazey
//       - #253: Add support for embedded constructor results with CriteriaBuilder
package org.eclipse.persistence.jpa.test.criteria.model;

public class L1Model {

    private Integer id;
    private String name;
    private L2Model l2;

    public L1Model(final Integer id, final String name, final L2Model l2) {
        this.id = id;
        this.name = name;
        this.l2 = l2;
    }

    public L2Model getL2() {
        return l2;
    }

    public void setL2(L2Model l2) {
        this.l2 = l2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
