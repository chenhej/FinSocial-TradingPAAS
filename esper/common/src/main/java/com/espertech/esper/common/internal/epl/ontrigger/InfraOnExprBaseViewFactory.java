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
package com.espertech.esper.common.internal.epl.ontrigger;

import com.espertech.esper.common.client.EventType;
import com.espertech.esper.common.internal.context.aifactory.ontrigger.ontrigger.InfraOnExprFactory;

/**
 * View for the on-delete statement that handles removing events from a named window.
 */
public abstract class InfraOnExprBaseViewFactory implements InfraOnExprFactory {
    protected final EventType infraEventType;

    protected InfraOnExprBaseViewFactory(EventType infraEventType) {
        this.infraEventType = infraEventType;
    }
}
