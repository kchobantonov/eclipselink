/*******************************************************************************
 * Copyright (c) 2020 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/
package org.eclipse.persistence.jpa.test.cachedeadlock.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cachedeadlock_master")
@Cacheable(true)
@NamedQuery(name="MasterEntity.findById", query="SELECT t FROM CacheDeadLockDetectionMaster t WHERE t.id = :id")
public class CacheDeadLockDetectionMaster {
    @Id
    private long id;

    private String name;

    private List<CacheDeadLockDetectionDetail> details = new ArrayList<>();


    public CacheDeadLockDetectionMaster() {
    }

    public CacheDeadLockDetectionMaster(long id) {
        this.id = id;
    }
    
    public CacheDeadLockDetectionMaster(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "master", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fk", referencedColumnName = "id")
    public List<CacheDeadLockDetectionDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CacheDeadLockDetectionDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "MasterEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details=" + details +
                '}';
    }
}