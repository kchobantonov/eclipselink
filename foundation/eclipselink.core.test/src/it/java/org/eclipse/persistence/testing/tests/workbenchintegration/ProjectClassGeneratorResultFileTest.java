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
package org.eclipse.persistence.testing.tests.workbenchintegration;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.eclipse.persistence.sessions.Project;
import org.eclipse.persistence.testing.framework.AutoVerifyTestCase;
import org.eclipse.persistence.testing.framework.TestErrorException;
import org.eclipse.persistence.sessions.factories.ProjectClassGenerator;


/**
 * Superclass for classes that test generated project java
 * setup() should be implemented in subclasses to set up a project as required.
 * This test will generate project java for a project and test to ensure an provided
 * line of code is generated.
 */
public class ProjectClassGeneratorResultFileTest extends AutoVerifyTestCase {
    protected Exception generationException = null;
    protected Exception fileReadException = null;
    protected String testString = null;
    protected Project project = null;
    protected ProjectClassGenerator generator = null;
    protected String fileName = null;

    /**
     * Instantiate ProjectClassGeneratorResultFileTest with a project.
     */
    public ProjectClassGeneratorResultFileTest(Project project) {
        this.project = project;
    }

    /**
     * Instantiate ProjectClassGeneratorResultFileTest with a project and a string
     * to search for in the generated code.
     */
    public ProjectClassGeneratorResultFileTest(Project project, String testString) {
        this.project = project;
        this.testString = testString;
    }

    /**
     * test() will do a default generation of a project.
     */
    public void test() {
        try {
            ProjectClassGenerator generator = new ProjectClassGenerator(project);
            generator.generate();
            fileName = generator.getOutputFileName();
        } catch (Exception exception) {
            generationException = exception;
        }
    }

    /**
     * verify ensures we have not generated any exception
     * and that the appropriate string exists in the project java source.
     */
    public void verify() {
        if (generationException != null) {
            throw new TestErrorException("Exception thrown while generating Java source. ", generationException);
        }
        if (!findStringInFile(testString, fileName)) {
            String exceptionString =
                "String: " + testString + " not found in " + fileName + ". This string should be generated by the ProjectClassGenerator.";
            if (fileReadException != null) {
                exceptionString =
                        exceptionString + " Exception thrown while reading file. - " + fileReadException.toString();
            }
            throw new TestErrorException(exceptionString, fileReadException);
        }
    }

    public void reset() {
        getSession().getIdentityMapAccessor().initializeAllIdentityMaps();
    }

    public boolean findStringInFile(String string, String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            LineNumberReader lnr = new LineNumberReader(reader);
            String line = lnr.readLine();
            while (line != null) {
                if (line.indexOf(string) > -1) {
                    return true;
                }
                line = lnr.readLine();
            }
        } catch (Exception exception) {
            fileReadException = exception;
        }
        ;
        return false;
    }
}
