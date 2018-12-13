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
package com.espertech.esper.common.client.hook.aggmultifunc;

/**
 * Factory for aggregation multi-function table readers
 */
public interface AggregationMultiFunctionTableReaderFactory {
    /**
     * Returns a new table reader
     *
     * @param context contextual information
     * @return table reader
     */
    AggregationMultiFunctionTableReader newReader(AggregationMultiFunctionTableReaderFactoryContext context);
}
