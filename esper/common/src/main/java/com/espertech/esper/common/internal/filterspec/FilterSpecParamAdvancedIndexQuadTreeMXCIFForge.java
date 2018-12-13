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

import com.espertech.esper.common.internal.bytecodemodel.base.CodegenClassScope;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethod;
import com.espertech.esper.common.internal.bytecodemodel.base.CodegenMethodScope;
import com.espertech.esper.common.internal.context.aifactory.core.SAIFFInitializeSymbolWEventType;
import com.espertech.esper.common.internal.epl.expression.core.ExprFilterSpecLookupable;
import com.espertech.esper.common.internal.epl.expression.core.ExprFilterSpecLookupableForge;

import static com.espertech.esper.common.internal.bytecodemodel.model.expression.CodegenExpressionBuilder.*;
import static com.espertech.esper.common.internal.filterspec.FilterSpecParamFilterForEvalDoubleForge.makeAnonymous;

public final class FilterSpecParamAdvancedIndexQuadTreeMXCIFForge extends FilterSpecParamForge {
    private FilterSpecParamFilterForEvalDoubleForge xEval;
    private FilterSpecParamFilterForEvalDoubleForge yEval;
    private FilterSpecParamFilterForEvalDoubleForge widthEval;
    private FilterSpecParamFilterForEvalDoubleForge heightEval;

    public FilterSpecParamAdvancedIndexQuadTreeMXCIFForge(ExprFilterSpecLookupableForge lookupable, FilterOperator filterOperator, FilterSpecParamFilterForEvalDoubleForge xEval, FilterSpecParamFilterForEvalDoubleForge yEval, FilterSpecParamFilterForEvalDoubleForge widthEval, FilterSpecParamFilterForEvalDoubleForge heightEval) {
        super(lookupable, filterOperator);
        this.xEval = xEval;
        this.yEval = yEval;
        this.widthEval = widthEval;
        this.heightEval = heightEval;
    }

    public CodegenMethod makeCodegen(CodegenClassScope classScope, CodegenMethodScope parent, SAIFFInitializeSymbolWEventType symbols) {
        CodegenMethod method = parent.makeChild(FilterSpecParamAdvancedIndexQuadTreeMXCIF.class, this.getClass(), classScope);
        method.getBlock()
                .declareVar(ExprFilterSpecLookupable.class, "lookupable", localMethod(lookupable.makeCodegen(method, symbols, classScope)))
                .declareVar(FilterOperator.class, "op", enumValue(FilterOperator.class, filterOperator.name()))
                .declareVar(FilterSpecParamAdvancedIndexQuadTreeMXCIF.class, "fpai", newInstance(FilterSpecParamAdvancedIndexQuadTreeMXCIF.class, ref("lookupable"), ref("op")))
                .exprDotMethod(ref("fpai"), "setxEval", makeAnonymous(xEval, this.getClass(), classScope, method))
                .exprDotMethod(ref("fpai"), "setyEval", makeAnonymous(yEval, this.getClass(), classScope, method))
                .exprDotMethod(ref("fpai"), "setWidthEval", makeAnonymous(widthEval, this.getClass(), classScope, method))
                .exprDotMethod(ref("fpai"), "setHeightEval", makeAnonymous(heightEval, this.getClass(), classScope, method))
                .methodReturn(ref("fpai"));
        return method;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FilterSpecParamAdvancedIndexQuadTreeMXCIFForge)) {
            return false;
        }

        FilterSpecParamAdvancedIndexQuadTreeMXCIFForge other = (FilterSpecParamAdvancedIndexQuadTreeMXCIFForge) obj;
        if (!super.equals(other)) {
            return false;
        }
        return this.xEval.equals(other.xEval) &&
                this.yEval.equals(other.yEval) &&
                this.widthEval.equals(other.widthEval) &&
                this.heightEval.equals(other.heightEval);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
