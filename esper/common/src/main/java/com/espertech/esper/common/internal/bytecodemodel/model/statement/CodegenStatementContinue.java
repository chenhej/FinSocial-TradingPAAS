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
package com.espertech.esper.common.internal.bytecodemodel.model.statement;

import java.util.Map;
import java.util.Set;

public class CodegenStatementContinue extends CodegenStatementBase {
    public final static CodegenStatementContinue INSTANCE = new CodegenStatementContinue();

    public void renderStatement(StringBuilder builder, Map<Class, String> imports, boolean isInnerClass) {
        builder.append("continue");
    }

    public void mergeClasses(Set<Class> classes) {
    }
}
