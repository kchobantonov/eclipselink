/*******************************************************************************
 * Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * Roman Grigoriadi
 ******************************************************************************/

package org.eclipse.persistence.json.bind.model;

/**
 * Customization, which could be applied on a class or package level.
 *
 * @author Roman Grigoriadi
 */
public class ClassCustomization extends Customization {

    private final JsonbCreator creator;

    /**
     * Copies properties from builder an creates immutable instance.
     *
     * @param builder not null
     */
    ClassCustomization(CustomizationBuilder builder) {
        super(builder);
        this.creator = builder.getCreator();
    }

    public JsonbCreator getCreator() {
        return creator;
    }
}