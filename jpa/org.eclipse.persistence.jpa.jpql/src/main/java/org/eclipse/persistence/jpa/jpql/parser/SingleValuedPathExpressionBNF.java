/*
 * Copyright (c) 2006, 2020 Oracle and/or its affiliates. All rights reserved.
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
//     Oracle - initial API and implementation
//
package org.eclipse.persistence.jpa.jpql.parser;

/**
 * The query BNF for a single valued path expression.
 * <p>
 * JPA 1.0:
 * <div><b>BNF:</b> <code>single_valued_path_expression ::= state_field_path_expression |
 *                                                                 single_valued_association_path_expression</code><p></div>
 * <p>
 * JPA 2.0:
 * <div><b>BNF:</b> <code>single_valued_path_expression ::= qualified_identification_variable |
 *                                                                 state_field_path_expression |
 *                                                                 single_valued_object_path_expression</code><p></div>
 *
 * @version 2.4
 * @since 2.3
 * @author Pascal Filion
 */
@SuppressWarnings("nls")
public final class SingleValuedPathExpressionBNF extends JPQLQueryBNF {

    /**
     * The unique identifier of this BNF rule.
     */
    public static final String ID = "single_valued_path_expression";

    /**
     * Creates a new <code>SingleValuedPathExpressionBNF</code>.
     */
    public SingleValuedPathExpressionBNF() {
        super(ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialize() {
        super.initialize();
        registerChild(StateFieldPathExpressionBNF.ID);
    }
}
