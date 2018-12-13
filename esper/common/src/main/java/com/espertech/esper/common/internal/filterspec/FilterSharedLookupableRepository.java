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
package com.espertech.esper.common.internal.filterspec;

import com.espertech.esper.common.client.EventType;
import com.espertech.esper.common.internal.epl.expression.core.ExprFilterSpecLookupable;

public interface FilterSharedLookupableRepository {
    void registerLookupable(int statementId, EventType eventType, ExprFilterSpecLookupable lookupable);

    void removeReferencesStatement(int statementId);

    void applyLookupableFromType(EventType asEventType, EventType eventType, int statementId);
}
