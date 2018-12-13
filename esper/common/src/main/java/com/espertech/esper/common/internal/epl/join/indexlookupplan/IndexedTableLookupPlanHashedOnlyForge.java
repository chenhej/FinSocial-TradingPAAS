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
package com.espertech.esper.common.internal.epl.join.indexlookupplan;

import com.espertech.esper.common.client.EventType;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethod;
import com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpression;
import com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbol;
import com.espertech.esper.common.internal.epl.expression.core.ExprForge;
import com.espertech.esper.common.internal.epl.expression.core.ExprNodeUtilityCodegen;
import com.espertech.esper.common.internal.epl.expression.core.ExprNodeUtilityQuery;
import com.espertech.esper.common.internal.epl.join.querygraph.QueryGraphValueEntryHashKeyedForge;
import com.espertech.esper.common.internal.epl.join.querygraph.QueryGraphValueEntryRangeForge;
import com.espertech.esper.common.internal.epl.join.queryplan.*;
import com.espertech.esper.common.internal.event.core.EventPropertyGetterSPI;
import com.espertech.esper.common.internal.event.core.EventTypeUtility;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Plan to perform an indexed table lookup.
 */
public class IndexedTableLookupPlanHashedOnlyForge extends TableLookupPlanForge {
    private QueryGraphValueEntryHashKeyedForge[] hashKeys;
    private QueryPlanIndexForge indexSpecs;
    private Class[] optionalCoercionTypes;

    public IndexedTableLookupPlanHashedOnlyForge(int lookupStream, int indexedStream, boolean indexedStreamIsVDW, EventType[] typesPerStream, TableLookupIndexReqKey indexNum, QueryGraphValueEntryHashKeyedForge[] hashKeys, QueryPlanIndexForge indexSpecs, Class[] optionalCoercionTypes) {
        super(lookupStream, indexedStream, indexedStreamIsVDW, typesPerStream, new TableLookupIndexReqKey[]{indexNum});
        this.hashKeys = hashKeys;
        this.indexSpecs = indexSpecs;
        this.optionalCoercionTypes = optionalCoercionTypes;
    }

    public TableLookupKeyDesc getKeyDescriptor() {
        return new TableLookupKeyDesc(Arrays.asList(hashKeys), Collections.<QueryGraphValueEntryRangeForge>emptyList());
    }

    public String toString() {
        return "IndexedTableLookupPlan " +
                super.toString() +
                " keyProperty=" + getKeyDescriptor();
    }

    public Class typeOfPlanFactory() {
        return IndexedTableLookupPlanHashedOnlyFactory.class;
    }

    public QueryGraphValueEntryHashKeyedForge[] getHashKeys() {
        return hashKeys;
    }

    public Collection<CodegenExpression> additionalParams(CodegenMethod method, SAIFFInitializeSymbol symbols, CodegenClassScope classScope) {

        EventPropertyGetterSPI[] getterSPIS = QueryGraphValueEntryHashKeyedForge.getGettersIfPropsOnly(hashKeys);
        ExprForge[] forges = QueryGraphValueEntryHashKeyedForge.getForges(hashKeys);
        Class[] types = ExprNodeUtilityQuery.getExprResultTypes(forges);

        // we take coercion types from the index plan as the index plan is always accurate but not always available (for tables it is not)
        Class[] coercionTypes;
        QueryPlanIndexItemForge indexForge = indexSpecs.getItems().get(getIndexNum()[0]);
        if (indexForge != null) {
            coercionTypes = indexForge.getHashTypes();
        } else {
            coercionTypes = optionalCoercionTypes;
        }

        CodegenExpression getter;
        if (getterSPIS != null) {
            getter = EventTypeUtility.codegenGetterMayMultiKeyWCoerce(typesPerStream[getLookupStream()], getterSPIS, types, coercionTypes, method, this.getClass(), classScope);
        } else {
            getter = ExprNodeUtilityCodegen.codegenEvaluatorMayMultiKeyWCoerce(forges, coercionTypes, method, this.getClass(), classScope);
        }
        return Collections.singletonList(getter);
    }
}
