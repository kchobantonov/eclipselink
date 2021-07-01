/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     04/24/2021 - Will Dazey
 *       - 573094: TRIM function generates incorrect SQL for CriteriaBuilder
 ******************************************************************************/
package org.eclipse.persistence.jpa.test.query.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TrimEntity.class)
public class TrimEntity_ {
    public static volatile SingularAttribute<TrimEntity, Long> id;
    public static volatile SingularAttribute<TrimEntity, String> strVal1;
}