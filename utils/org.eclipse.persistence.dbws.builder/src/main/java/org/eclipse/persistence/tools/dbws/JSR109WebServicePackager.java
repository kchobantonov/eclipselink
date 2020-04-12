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
//     Mike Norman - May 26 2008, creating packager for JAX-WS RI
 package org.eclipse.persistence.tools.dbws;

// javase imports
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

//EclipseLink imports
import static org.eclipse.persistence.internal.xr.Util.WEB_INF_DIR;
import static org.eclipse.persistence.tools.dbws.DBWSPackager.ArchiveUse.noArchive;
import static org.eclipse.persistence.tools.dbws.Util.WEB_XML_FILENAME;

/**
 * <p>
 * <b>INTERNAL:</b> WebServicePackager extends {@link ProviderPackager}. It is responsible for generating<br>
 * the simplified JSR-109 files to deploy the service.
 * <pre>
 * \--- ${stageDir} root directory
 *    |   ...                   -- files generated by parent class
 *    |   web.xml               -- conform to JSR-154 Servlet 2.5
 * </pre>
 *
 * @author Mike Norman - michael.norman@oracle.com
 * @since EclipseLink 1.x
 */
public class JSR109WebServicePackager extends ProviderPackager {

    public static final String WEB_XML_PREAMBLE =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<web-app\n" +
        "  xmlns=\"http://java.sun.com/xml/ns/javaee\"\n" +
        "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
        "  xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd\"\n" +
        "  version=\"2.5\"\n" +
        "  >\n" +
        "  <listener>\n" +
        "    <listener-class>_dbws.ProviderListener</listener-class>\n" +
        "  </listener>\n" +
        "  <servlet>\n" +
        "    <servlet-name>_dbws.DBWSProvider</servlet-name>\n" +
        "    <servlet-class>_dbws.DBWSProvider</servlet-class>\n" +
        "    <load-on-startup>0</load-on-startup>\n" +
        "  </servlet>\n" +
        "  <servlet-mapping>\n" +
        "    <servlet-name>_dbws.DBWSProvider</servlet-name>\n" +
        "    <url-pattern>";
    public static final String WEB_XML_URL_PATTERN =
                               "</url-pattern>\n" +
        "  </servlet-mapping>\n" +
        "</web-app>";

    public JSR109WebServicePackager() {
        this(new WarArchiver(), "wse", noArchive);
    }
    protected JSR109WebServicePackager(Archiver archiver, String packagerLabel, ArchiveUse useJavaArchive) {
        super(archiver, packagerLabel, useJavaArchive);
    }

    @Override
    public Archiver buildDefaultArchiver() {
        return new WarArchiver(this);
    }

    @Override
    public OutputStream getWebXmlStream() throws FileNotFoundException {
        return new FileOutputStream(new File(stageDir, WEB_XML_FILENAME));
    }
    @Override
    public void writeWebXml(OutputStream webXmlStream, DBWSBuilder dbwsBuilder) {
        StringBuilder sb = new StringBuilder(WEB_XML_PREAMBLE);
        sb.append(dbwsBuilder.getContextRoot());
        sb.append(WEB_XML_URL_PATTERN);
        OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(webXmlStream));
        try {
            osw.write(sb.toString());
            osw.flush();
        }
        catch (IOException e) {/* ignore */}
    }

    @Override
    public String getWSDLPathPrefix() {
        return WEB_INF_DIR + super.getWSDLPathPrefix();
    }
}
