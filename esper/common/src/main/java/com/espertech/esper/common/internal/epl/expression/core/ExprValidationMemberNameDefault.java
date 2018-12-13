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
package com.espertech.esper.common.internal.epl.expression.core;

import com.espertech.esper.common.internal.bytecodemodel.name.*;

public class ExprValidationMemberNameDefault implements ExprValidationMemberName {
    public final static ExprValidationMemberNameDefault INSTANCE = new ExprValidationMemberNameDefault();

    private ExprValidationMemberNameDefault() {
    }

    public CodegenFieldName aggregationResultFutureRef() {
        return CodegenFieldNameAgg.INSTANCE;
    }

    public CodegenFieldName priorStrategy(int streamNum) {
        return new CodegenFieldNamePrior(streamNum);
    }

    public CodegenFieldName previousStrategy(int streamNumber) {
        return new CodegenFieldNamePrevious(streamNumber);
    }

    public CodegenFieldName previousMatchrecognizeStrategy() {
        return CodegenFieldNameMatchRecognizePrevious.INSTANCE;
    }
}
