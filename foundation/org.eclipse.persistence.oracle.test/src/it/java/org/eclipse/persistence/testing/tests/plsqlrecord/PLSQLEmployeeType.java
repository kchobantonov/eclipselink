/*
 * Copyright (c) 2018, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

package org.eclipse.persistence.testing.tests.plsqlrecord;

import java.math.BigDecimal;
import java.sql.Date;

public class PLSQLEmployeeType {

    public BigDecimal employeeNumber;
    public String name;
    public String job;
    public BigDecimal manager;
    public Date hireDate;
    public Float salary;
    public Float commission;
    public BigDecimal department;

}
