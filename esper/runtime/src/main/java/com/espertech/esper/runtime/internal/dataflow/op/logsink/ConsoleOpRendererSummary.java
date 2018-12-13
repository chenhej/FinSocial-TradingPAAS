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
package com.espertech.esper.runtime.internal.dataflow.op.logsink;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.util.EventBeanSummarizer;

import java.io.StringWriter;

public class ConsoleOpRendererSummary implements ConsoleOpRenderer {
    public void render(EventBean theEvent, StringWriter writer) {
        EventBeanSummarizer.summarize(theEvent, writer);
    }
}
