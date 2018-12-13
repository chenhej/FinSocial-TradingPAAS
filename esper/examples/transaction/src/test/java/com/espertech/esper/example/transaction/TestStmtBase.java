/*
 ***************************************************************************************
 *  Copyright (C) 2006 EsperTech, Inc. All rights reserved.                            *
 *  http://www.espertech.com/esper                                                     *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 ***************************************************************************************
 */
package com.espertech.esper.example.transaction;

import com.espertech.esper.common.client.configuration.Configuration;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPRuntimeProvider;
import junit.framework.TestCase;

public abstract class TestStmtBase extends TestCase {
    protected EPRuntime runtime;

    public void setUp() {
        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType("TxnEventA", TxnEventA.class.getName());
        configuration.getCommon().addEventType("TxnEventB", TxnEventB.class.getName());
        configuration.getCommon().addEventType("TxnEventC", TxnEventC.class.getName());
        configuration.getCommon().getLogging().setEnableQueryPlan(true);

        runtime = EPRuntimeProvider.getRuntime("TestStmtBase", configuration);
        runtime.initialize();
    }

    public void tearDown() throws Exception {
        runtime.destroy();
    }

    protected void sendEvent(Object theEvent) {
        runtime.getEventService().sendEventBean(theEvent, theEvent.getClass().getSimpleName());
    }

}
