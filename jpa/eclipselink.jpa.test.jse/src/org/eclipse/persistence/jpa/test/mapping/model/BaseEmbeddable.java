/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     04/17/2020 - Will Dazey
 *       - 561664: JoinColumn with same name as referencedColumnName
 ******************************************************************************/
package org.eclipse.persistence.jpa.test.mapping.model;

import javax.persistence.Embeddable;

@Embeddable
public class BaseEmbeddable {

    @javax.persistence.ManyToOne
    @javax.persistence.JoinColumn(
            name = "BASE_PARENT_ID", 
            referencedColumnName = "BASE_PARENT_ID")
    private BaseParent parent;
}