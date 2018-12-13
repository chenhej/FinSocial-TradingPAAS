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
package com.espertech.esper.common.internal.event.bean.introspect;

import com.espertech.esper.common.client.configuration.common.ConfigurationCommonEventTypeBean;
import com.espertech.esper.common.internal.event.bean.core.PropertyHelper;
import com.espertech.esper.common.internal.event.bean.core.PropertyStem;

import java.util.List;

/**
 * Implementation for a property list builder that considers JavaBean-style methods
 * as the exposed event properties, plus any explicitly configured props.
 */
public class PropertyListBuilderJavaBean implements PropertyListBuilder {
    private ConfigurationCommonEventTypeBean optionalLegacyConfig;

    /**
     * Ctor.
     *
     * @param optionalLegacyConfig configures legacy type, or null information
     *                             has been supplied.
     */
    public PropertyListBuilderJavaBean(ConfigurationCommonEventTypeBean optionalLegacyConfig) {
        this.optionalLegacyConfig = optionalLegacyConfig;
    }

    public List<PropertyStem> assessProperties(Class clazz) {
        List<PropertyStem> result = PropertyHelper.getProperties(clazz);
        if (optionalLegacyConfig != null) {
            PropertyListBuilderExplicit.getExplicitProperties(result, clazz, optionalLegacyConfig);
        }
        return result;
    }
}
