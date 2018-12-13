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
package com.espertech.esper.runtime.internal.subscriber;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.collection.UniformPair;
import com.espertech.esper.common.internal.event.core.NaturalEventBean;
import com.espertech.esper.common.internal.settings.ClasspathImportService;
import com.espertech.esper.runtime.client.EPStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A result delivery strategy that uses an "update" method that accepts a underlying array
 * for use in wildcard selection.
 */
public class ResultDeliveryStrategyTypeArr implements ResultDeliveryStrategy {
    private final static Logger log = LoggerFactory.getLogger(ResultDeliveryStrategyImpl.class);
    protected final EPStatement statement;
    protected final Object subscriber;
    protected final Method method;
    protected final Class componentType;

    /**
     * Ctor.
     *
     * @param subscriber             is the receiver to method invocations
     * @param method                 is the method to deliver to
     * @param statement              statement
     * @param componentType          component type
     * @param classpathImportService runtime imports
     */
    public ResultDeliveryStrategyTypeArr(EPStatement statement, Object subscriber, Method method, Class componentType, ClasspathImportService classpathImportService) {
        this.statement = statement;
        this.subscriber = subscriber;
        this.method = method;
        this.componentType = componentType;
    }

    public void execute(UniformPair<EventBean[]> result) {
        Object newData;
        Object oldData;

        if (result == null) {
            newData = null;
            oldData = null;
        } else {
            newData = convert(result.getFirst());
            oldData = convert(result.getSecond());
        }

        Object[] parameters = new Object[]{newData, oldData};
        try {
            method.invoke(subscriber, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            ResultDeliveryStrategyImpl.handle(statement.getName(), log, e, parameters, subscriber, method);
        }
    }

    protected Object convert(EventBean[] events) {
        if ((events == null) || (events.length == 0)) {
            return null;
        }

        Object array = Array.newInstance(componentType, events.length);
        int length = 0;
        for (int i = 0; i < events.length; i++) {
            if (events[i] instanceof NaturalEventBean) {
                NaturalEventBean natural = (NaturalEventBean) events[i];
                Array.set(array, length, natural.getNatural()[0]);
                length++;
            }
        }

        if (length == 0) {
            return null;
        }
        if (length != events.length) {
            Object reduced = Array.newInstance(componentType, events.length);
            System.arraycopy(array, 0, reduced, 0, length);
            array = reduced;
        }
        return array;
    }
}
