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
package com.espertech.esper.example.autoid;

import com.espertech.esper.runtime.client.EPUndeployException;
import com.espertech.esper.runtime.client.plugin.PluginLoader;
import com.espertech.esper.runtime.client.plugin.PluginLoaderInitContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PluginLoader for added this example as part of an Esper configuration file and therefore execute it during startup.
 */
public class AutoIdSamplePlugin implements PluginLoader {
    private static final Logger log = LoggerFactory.getLogger(AutoIdSamplePlugin.class);

    private static final String RUNTIME_URI = "runtimeURI";

    private String runtimeURI;
    private AutoIdSimMain autoIdSimMain;
    private Thread simulationThread;

    public void init(PluginLoaderInitContext context) {
        if (context.getProperties().getProperty(RUNTIME_URI) != null) {
            runtimeURI = context.getProperties().getProperty(RUNTIME_URI);
        } else {
            runtimeURI = context.getRuntime().getURI();
        }
    }

    public void postInitialize() {
        log.info("Starting AutoID-example for runtime URI '" + runtimeURI + "'.");

        try {
            autoIdSimMain = new AutoIdSimMain(1000, runtimeURI, true);
            simulationThread = new Thread(autoIdSimMain, this.getClass().getName() + "-simulator");
            simulationThread.setDaemon(true);
            simulationThread.start();
        } catch (Exception e) {
            log.error("Error starting AutoID example: " + e.getMessage());
        }

        log.info("AutoID-example started.");
    }

    public void destroy() {
        if (autoIdSimMain != null) {
            try {
                autoIdSimMain.destroy();
            } catch (EPUndeployException e) {
                log.warn("Failed to undeploy: " + e.getMessage(), e);
            }
        }
        try {
            simulationThread.interrupt();
            simulationThread.join();
        } catch (InterruptedException e) {
            log.info("Interrupted", e);
        }
        autoIdSimMain = null;
        log.info("AutoID-example stopped.");
    }
}

