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
//     Oracle - initial API and implementation from Oracle TopLink
package org.eclipse.persistence.testing.oxm.xmlmarshaller.structureValidation.sequence;

import java.util.Calendar;

public class Employee {
    Period _StartDateAndEndDate;

    public Employee() {
        _StartDateAndEndDate = new Period();
    }

    public Period getStartDateAndEndDate() {
        return _StartDateAndEndDate;
    }

    public void setStartDateAndEndDate(Period value) {
        _StartDateAndEndDate = value;
    }

    public static class Period {
        Calendar _StartDate;
        Calendar _EndDate;

        public Period() {
        }

        public void setStartDate(Calendar value) {
            _StartDate = value;
        }

        public Calendar getStartDate() {
            return _StartDate;
        }

        public void setEndDate(Calendar value) {
            _EndDate = value;
        }

        public Calendar getEndDate() {
            return _EndDate;
        }
    }
}
