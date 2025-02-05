/*
 * Copyright (c) 1998, 2023 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2022 IBM Corporation. All rights reserved.
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
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.descriptors;

import java.sql.Timestamp;

import org.eclipse.persistence.exceptions.OptimisticLockException;
import org.eclipse.persistence.internal.helper.ClassConstants;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.internal.sessions.AbstractRecord;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.queries.ModifyQuery;

/**
 * <p><b>Purpose</b>: Used to allow a single version timestamp to be used for optimistic locking.
 *
 * @since TOPLink/Java 2.0
 */
public class TimestampLockingPolicy extends AbstractTsLockingPolicy<Timestamp> {

    /**
     * Create a new TimestampLockingPolicy.
     * Defaults to using the time retrieved from the server.
     */
    public TimestampLockingPolicy() {
        super();
    }

    /**
     * Create a new TimestampLockingPolicy.
     * Defaults to using the time retrieved from the server.
     *
     * @param fieldName the field where the write lock value will be stored
     */
    public TimestampLockingPolicy(String fieldName) {
        super(fieldName);
    }

    /**
     * Create a new TimestampLockingPolicy.
     * Defaults to using the time retrieved from the server.
     *
     * @param field the field where the write lock value will be stored
     */
    TimestampLockingPolicy(DatabaseField field) {
        super(field);
    }

    @Override
    int compareTsLockValues(Timestamp value1, Timestamp value2) {
        return value1.compareTo(value2);
    }

    @Override
    Class<Timestamp> getDefaultTsLockFieldType() {
        return ClassConstants.TIMESTAMP;
    }

    @Override
    Timestamp getBaseTsValue(){
        return new Timestamp(0);
    }

    @Override
    Timestamp getInitialTsWriteValue(AbstractSession session) {
        if (usesLocalTime()) {
            return new Timestamp(System.currentTimeMillis());
        }
        if (usesServerTime()) {
            AbstractSession readSession = session.getSessionForClass(getDescriptor().getJavaClass());
            while (readSession.isUnitOfWork()) {
                readSession = readSession.getParent()
                        .getSessionForClass(getDescriptor().getJavaClass());
            }

            return readSession.getDatasourceLogin()
                    .getDatasourcePlatform()
                    .getTimestampFromServer(session, readSession.getName());
        }
        return null;
    }

    @Override
    Timestamp getNewTsLockValue(ModifyQuery query) {
        return getInitialTsWriteValue(query.getSession());
    }

    @Override
    Timestamp getTsValueToPutInCache(AbstractRecord row, AbstractSession session) {
        if (isStoredInCache()) {
            return session.getDatasourcePlatform()
                    .convertObject(row.get(getWriteLockField()), ClassConstants.TIMESTAMP);
        } else {
            return null;
        }
    }

    @Override
    Timestamp getWriteTsLockValue(Object domainObject, Object primaryKey, AbstractSession session) {
        Timestamp writeLockFieldValue = null;
        if (isStoredInCache()) {
            writeLockFieldValue = (Timestamp) session.getIdentityMapAccessorInstance()
                    .getWriteLockValue(primaryKey, domainObject.getClass(), getDescriptor());
        } else {
            //CR#2281 notStoredInCache prevent ClassCastException
            Object lockValue = lockValueFromObject(domainObject);
            if (lockValue != null) {
                if (lockValue instanceof Timestamp) {
                    writeLockFieldValue = (Timestamp) lockValueFromObject(domainObject);
                } else {
                    throw OptimisticLockException.needToMapJavaSqlTimestampWhenStoredInObject();
                }
            }
        }
        return writeLockFieldValue;
    }

    @Override
    boolean isNewerTsVersion(Timestamp current, Object domainObject, Object primaryKey, AbstractSession session) {
        Timestamp writeLockFieldValue;
        if (isStoredInCache()) {
            writeLockFieldValue = (Timestamp) session.getIdentityMapAccessorInstance()
                    .getWriteLockValue(primaryKey, domainObject.getClass(), getDescriptor());
        } else {
            writeLockFieldValue = (Timestamp)lockValueFromObject(domainObject);
        }

        return isNewerTsVersion(current, writeLockFieldValue);

    }

    @Override
    boolean isNewerTsVersion(AbstractRecord row, Object domainObject, Object primaryKey, AbstractSession session) {
        Timestamp writeLockFieldValue;
        Timestamp newWriteLockFieldValue = session.getDatasourcePlatform()
                .convertObject(row.get(getWriteLockField()), ClassConstants.TIMESTAMP);
        if (isStoredInCache()) {
            writeLockFieldValue = (Timestamp) session.getIdentityMapAccessorInstance()
                    .getWriteLockValue(primaryKey, domainObject.getClass(), getDescriptor());
        } else {
            writeLockFieldValue = (Timestamp) lockValueFromObject(domainObject);
        }
        return isNewerTsVersion(newWriteLockFieldValue, writeLockFieldValue);
    }

    @Override
    boolean isNewerTsVersion(Timestamp first, Timestamp second) {
        // 2.5.1.6 if the write lock value is null, then what ever we have is treated as newer.
        if (first == null) {
            return false;
        }
        // bug 6342382: first is not null, second is null, so we know first>second.
        if (second == null) {
            return true;
        }
        return first.after(second);
    }

    /**
     * INTERNAL:
     * Return the number of versions different between these objects.
     */
    @Override
    public int getVersionDifference(Object currentValue, Object domainObject, Object primaryKeys, AbstractSession session) {
        Timestamp writeLockFieldValue;
        Timestamp newWriteLockFieldValue = (Timestamp)currentValue;
        if (newWriteLockFieldValue == null) {
            return 0;//merge it as either the object is new or being forced merged.
        }
        if (isStoredInCache()) {
            writeLockFieldValue = (Timestamp)session.getIdentityMapAccessorInstance().getWriteLockValue(primaryKeys, domainObject.getClass(), getDescriptor());
        } else {
            writeLockFieldValue = (Timestamp)lockValueFromObject(domainObject);
        }
        if ((writeLockFieldValue != null) && (newWriteLockFieldValue.equals(writeLockFieldValue))) {
            return 0;
        }
        if ((writeLockFieldValue != null) && !(newWriteLockFieldValue.after(writeLockFieldValue))) {
            return -1;
        }
        return 1;
    }

}
