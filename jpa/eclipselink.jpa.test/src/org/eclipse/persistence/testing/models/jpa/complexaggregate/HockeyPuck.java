/*******************************************************************************
 * Copyright (c) 1998, 2020 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     dminsky - initial API and implementation
 *     
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.complexaggregate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CMP3_HOCKEY_PUCK")
public class HockeyPuck {

    @Id
    protected int id; 
    protected String name;
    @Embedded
    protected HockeySponsor sponsor;
    
    public HockeyPuck() {
        super();
        // the Embeddable must be instantiated with real values
        sponsor = new HockeySponsor();
        sponsor.setName("none");
        sponsor.setSponsorshipValue(1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return getClass().getSimpleName() + " id:[" + id + "] name:[" + name + "] hashcode:[" + System.identityHashCode(this) + "] embedded: " + sponsor;
    }

    public HockeySponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(HockeySponsor sponsor) {
        this.sponsor = sponsor;
    }
    
}