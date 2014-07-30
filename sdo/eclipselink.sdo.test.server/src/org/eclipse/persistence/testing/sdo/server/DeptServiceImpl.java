/*******************************************************************************
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     etang - April 12/2010 - 2.1 - Initial implementation
 ******************************************************************************/
package org.eclipse.persistence.testing.sdo.server;

import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.eclipse.persistence.sdo.helper.SDOHelperContext;

@Stateless(name = "org.eclipse.persistence.testing.sdo.server.DeptServiceBean", mappedName = "DeptServiceBean")
@Remote
public class DeptServiceImpl implements DeptService {

    private static final Logger LOGGER = Logger.getLogger(DeptServiceImpl.class.getName());

    public DeptServiceImpl() {
    }

    public Dept getDept(Integer deptno) {
        LOGGER.info("\n********** getDept() Called **********\n");
        LOGGER.info("deptno: " + deptno);
        Dept dept = (Dept) SDOHelperContext.getHelperContext().getDataFactory().create(Dept.class);
        dept.setDeptno(deptno);
        dept.setDname("Dname" + deptno);
        dept.setLoc("Loc" + deptno);
        return dept;
    }

    public boolean updateDept(Dept dept) {
        LOGGER.info("\n********** updateDept() Called **********\n");
        if (dept == null)
            return false;
        DeptImpl deptImpl = (DeptImpl) dept;
        SDOHelperContext.getHelperContext().getXMLHelper().save(deptImpl, deptImpl.getType().getURI(), deptImpl.getType().getName());
        return true;
    }
}
