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
package com.espertech.esper.common.internal.epl.enummethod.eval;

import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethodScope;
import com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpression;
import com.espertech.esper.common.internal.epl.enummethod.codegen.EnumForgeCodegenParams;
import com.espertech.esper.common.internal.epl.expression.core.ExprForge;
import com.espertech.esper.common.internal.event.arr.ObjectArrayEventType;

public class EnumOrderByAscDescScalarLambdaForge extends EnumForgeBase {

    protected final boolean descending;
    protected final ObjectArrayEventType resultEventType;

    public EnumOrderByAscDescScalarLambdaForge(ExprForge innerExpression, int streamCountIncoming, boolean descending, ObjectArrayEventType resultEventType) {
        super(innerExpression, streamCountIncoming);
        this.descending = descending;
        this.resultEventType = resultEventType;
    }

    public EnumEval getEnumEvaluator() {
        return new EnumOrderByAscDescScalarLambdaForgeEval(this, innerExpression.getExprEvaluator());
    }

    public CodegenExpression codegen(EnumForgeCodegenParams premade, CodegenMethodScope codegenMethodScope, CodegenClassScope codegenClassScope) {
        return EnumOrderByAscDescScalarLambdaForgeEval.codegen(this, premade, codegenMethodScope, codegenClassScope);
    }
}
