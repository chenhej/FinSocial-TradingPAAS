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
package com.espertech.esper.regressionlib.support.multithread;

import com.espertech.esper.common.internal.support.SupportBean_S0;
import com.espertech.esper.common.internal.support.SupportBean_S1;
import com.espertech.esper.runtime.client.EPRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class StmtNamedWindowPriorityCallable implements Callable {
    private final int threadNum;
    private final EPRuntime runtime;
    private final int numRepeats;

    public StmtNamedWindowPriorityCallable(int threadNum, EPRuntime runtime, int numRepeats) {
        this.threadNum = threadNum;
        this.runtime = runtime;
        this.numRepeats = numRepeats;
    }

    public Object call() throws Exception {
        try {
            int offset = threadNum + 1000000;
            for (int i = 0; i < numRepeats; i++) {
                runtime.getEventService().sendEventBean(new SupportBean_S0(i + offset, "c0_" + i + offset, "p01_" + i + offset), "SupportBean_S0");
                runtime.getEventService().sendEventBean(new SupportBean_S1(i + offset, "c0_" + i + offset, "x", "y"), "SupportBean_S1");
            }
        } catch (Exception ex) {
            log.error("Error in thread " + Thread.currentThread().getId(), ex);
            return false;
        }
        return true;
    }

    private static final Logger log = LoggerFactory.getLogger(StmtNamedWindowPriorityCallable.class);
}
