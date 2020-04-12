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
//     03/27/2009-2.0 Guy Pelletier
//       - 241413: JPA 2.0 Add EclipseLink support for Map type attributes
package org.eclipse.persistence.testing.models.jpa.xml.inherited;

public class Becks extends Beer implements Cloneable {
    public Becks() {}

    public Becks clone() throws CloneNotSupportedException {
        return (Becks) super.clone();
    }

    public boolean equals(Object anotherBecks) {
        if (anotherBecks.getClass() != Becks.class) {
            return false;
        }

        return (getId().equals(((Becks)anotherBecks).getId()));
    }
}
