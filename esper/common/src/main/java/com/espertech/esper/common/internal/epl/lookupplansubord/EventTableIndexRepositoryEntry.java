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
package com.espertech.esper.common.internal.epl.lookupplansubord;

import com.espertech.esper.common.internal.epl.index.base.EventTable;

public class EventTableIndexRepositoryEntry extends EventTableIndexEntryBase {
    private final EventTable table;

    public EventTableIndexRepositoryEntry(String optionalIndexName, String optionalIndexModuleName, EventTable table) {
        super(optionalIndexName, optionalIndexModuleName);
        this.table = table;
    }

    public EventTable getTable() {
        return table;
    }
}
