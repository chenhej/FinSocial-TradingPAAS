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
package com.espertech.esper.common.internal.epl.resultset.rowpergroup;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.collection.MultiKey;
import com.espertech.esper.common.internal.collection.UniformPair;
import com.espertech.esper.common.internal.epl.resultset.core.ResultSetProcessorOutputHelper;

import java.util.Set;

public interface ResultSetProcessorRowPerGroupOutputLastHelper extends ResultSetProcessorOutputHelper {
    void processView(EventBean[] newData, EventBean[] oldData, boolean isGenerateSynthetic);

    void processJoin(Set<MultiKey<EventBean>> newData, Set<MultiKey<EventBean>> oldData, boolean isGenerateSynthetic);

    UniformPair<EventBean[]> outputView(boolean isSynthesize);

    UniformPair<EventBean[]> outputJoin(boolean isSynthesize);

    void remove(Object key);

    void destroy();
}
