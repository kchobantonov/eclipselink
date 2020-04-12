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
package org.eclipse.persistence.internal.sessions.coordination.corba.sun;


/**
* org/eclipse/persistence/internal/remotecommand/corba/sun/CommandDataHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from rcm.idl
* Tuesday, March 30, 2004 2:00:14 PM EST
*/
public final class CommandDataHelper implements org.omg.CORBA.portable.BoxedValueHelper {
    private static final String _id = "IDL:org/eclipse/persistence/internal/remotecommand/corba/sun/CommandData:1.0";
    private static final CommandDataHelper _instance = new CommandDataHelper();

    public CommandDataHelper() {
    }

    public static void insert(org.omg.CORBA.Any a, byte[] that) {
        org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
        a.type(type());
        write(out, that);
        a.read_value(out.create_input_stream(), type());
    }

    public static byte[] extract(org.omg.CORBA.Any a) {
        return read(a.create_input_stream());
    }

    private static volatile org.omg.CORBA.TypeCode __typeCode = null;
    private static boolean __active = false;

    synchronized public static org.omg.CORBA.TypeCode type() {
        org.omg.CORBA.TypeCode __typeCode = CommandDataHelper.__typeCode;
        if (__typeCode == null) {
            synchronized (org.omg.CORBA.TypeCode.class) {
                __typeCode = CommandDataHelper.__typeCode;
                if (__typeCode == null) {
                    if (__active) {
                        return org.omg.CORBA.ORB.init().create_recursive_tc(_id);
                    }
                    __active = true;
                    CommandDataHelper.__typeCode = __typeCode = org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.tk_octet);
                    CommandDataHelper.__typeCode = __typeCode = org.omg.CORBA.ORB.init().create_sequence_tc(0, __typeCode);
                    CommandDataHelper.__typeCode = __typeCode = org.omg.CORBA.ORB.init().create_value_box_tc(_id, "CommandData", __typeCode);
                    __active = false;
                }
            }
        }
        return __typeCode;
    }

    public static String id() {
        return _id;
    }

    public static byte[] read(org.omg.CORBA.portable.InputStream istream) {
        if (!(istream instanceof org.omg.CORBA_2_3.portable.InputStream)) {
            throw new org.omg.CORBA.BAD_PARAM();
        }
        return (byte[])((org.omg.CORBA_2_3.portable.InputStream)istream).read_value(_instance);
    }

    @Override
    public java.io.Serializable read_value(org.omg.CORBA.portable.InputStream istream) {
        byte[] tmp;
        int _len0 = istream.read_long();
        tmp = new byte[_len0];
        istream.read_octet_array(tmp, 0, _len0);
        return tmp;
    }

    public static void write(org.omg.CORBA.portable.OutputStream ostream, byte[] value) {
        if (!(ostream instanceof org.omg.CORBA_2_3.portable.OutputStream)) {
            throw new org.omg.CORBA.BAD_PARAM();
        }
        ((org.omg.CORBA_2_3.portable.OutputStream)ostream).write_value(value, _instance);
    }

    @Override
    public void write_value(org.omg.CORBA.portable.OutputStream ostream, java.io.Serializable value) {
        if (!(value instanceof byte[])) {
            throw new org.omg.CORBA.MARSHAL();
        }
        byte[] valueType = (byte[])value;
        ostream.write_long(valueType.length);
        ostream.write_octet_array(valueType, 0, valueType.length);
    }

    @Override
    public String get_id() {
        return _id;
    }
}
