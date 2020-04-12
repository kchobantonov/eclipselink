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
// Matt MacIvor - 2011 March 21 - 2.3 - Initial implementation
package org.eclipse.persistence.testing.jaxb.xmlvirtualaccessmethods.proporder;

import org.eclipse.persistence.oxm.annotations.XmlVirtualAccessMethods;

public class PhoneNumber {

    public boolean equals(Object obj) {
        return obj.getClass() == PhoneNumber.class;
    }

}
