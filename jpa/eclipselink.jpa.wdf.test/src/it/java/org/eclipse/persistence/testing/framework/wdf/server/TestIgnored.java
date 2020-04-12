/*
 * Copyright (c) 2005, 2020 Oracle and/or its affiliates. All rights reserved.
 * Copyright (c) 2005, 2015 SAP. All rights reserved.
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
//     SAP - initial API and implementation
package org.eclipse.persistence.testing.framework.wdf.server;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;

/**
 * A notification indicating that a test has been ignored or skipped.
 */
public class TestIgnored implements Notification {

    private static final long serialVersionUID = 1L;
    private final SerializableDescription description;

    public TestIgnored(Description aDescription) {
        description = SerializableDescription.create(aDescription);
    }

    @Override
    public void notify(RunNotifier notifier) {
        notifier.fireTestIgnored(description.restore());
    }

}
