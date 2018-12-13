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
package com.espertech.esper.common.internal.epl.resultset.rowperevent;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.collection.MultiKey;
import com.espertech.esper.common.internal.collection.UniformPair;
import com.espertech.esper.common.internal.event.core.EventBeanUtility;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;

public class ResultSetProcessorRowPerEventOutputAllHelperImpl implements ResultSetProcessorRowPerEventOutputAllHelper {
    private final ResultSetProcessorRowPerEvent processor;
    private final Deque<EventBean> eventsOld = new ArrayDeque<>(2);
    private final Deque<EventBean> eventsNew = new ArrayDeque<>(2);

    public ResultSetProcessorRowPerEventOutputAllHelperImpl(ResultSetProcessorRowPerEvent processor) {
        this.processor = processor;
    }

    public void processView(EventBean[] newData, EventBean[] oldData, boolean isGenerateSynthetic) {
        UniformPair<EventBean[]> pair = processor.processViewResult(newData, oldData, isGenerateSynthetic);
        apply(pair);
    }

    public void processJoin(Set<MultiKey<EventBean>> newEvents, Set<MultiKey<EventBean>> oldEvents, boolean isGenerateSynthetic) {
        UniformPair<EventBean[]> pair = processor.processJoinResult(newEvents, oldEvents, isGenerateSynthetic);
        apply(pair);
    }

    public UniformPair<EventBean[]> output() {
        EventBean[] oldEvents = EventBeanUtility.toArrayNullIfEmpty(eventsOld);
        EventBean[] newEvents = EventBeanUtility.toArrayNullIfEmpty(eventsNew);

        UniformPair<EventBean[]> result = null;
        if (oldEvents != null || newEvents != null) {
            result = new UniformPair<>(newEvents, oldEvents);
        }

        eventsOld.clear();
        eventsNew.clear();
        return result;
    }

    public void destroy() {
        // no action required
    }

    private void apply(UniformPair<EventBean[]> pair) {
        if (pair == null) {
            return;
        }
        EventBeanUtility.addToCollection(pair.getFirst(), eventsNew);
        EventBeanUtility.addToCollection(pair.getSecond(), eventsOld);
    }
}
