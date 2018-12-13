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
package com.espertech.esperio.kafka;

import com.espertech.esper.common.client.EventBean;
import com.espertech.esper.common.internal.util.SerializerUtil;
import com.espertech.esper.runtime.client.EPRuntime;
import com.espertech.esper.runtime.client.EPStatement;
import junit.framework.TestCase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static com.espertech.esperio.kafka.SupportCompileUtil.compileDeploy;
import static com.espertech.esperio.kafka.SupportConstants.DEV_INPUT_TOPIC_SUPPORTBEAN_JAVASERIALIZED;

public class TestKafkaInputMsgTimestampBeanSerValue extends TestCase {

    private static final String TOPIC = DEV_INPUT_TOPIC_SUPPORTBEAN_JAVASERIALIZED;

    public void testInput() {

        Properties pluginProperties = SupportConstants.getInputPluginProps(TOPIC, SupportBeanFromByteArrayDeserializer.class.getName(), EsperIOKafkaInputTimestampExtractorConsumerRecord.class.getName());

        EPRuntime runtime = SupportConstants.getEngineWKafkaInput(this.getClass().getSimpleName(), pluginProperties);
        runtime.getEventService().advanceTime(0);

        EPStatement stmt = compileDeploy(runtime, "select * from SupportBean").getStatements()[0];
        SupportListener listener = new SupportListener();
        stmt.addListener(listener);

        // send single message with Java serialization (Java serialization is used for testing here and may not be the best choice)
        Properties producerProperties = SupportConstants.getProducerProps(org.apache.kafka.common.serialization.ByteArraySerializer.class.getName());
        KafkaProducer<String, byte[]> producer = new KafkaProducer<>(producerProperties);
        String generatedUUID = UUID.randomUUID().toString();
        int randomNumber = (int) (Math.random() * 100000000);
        long timestamp = 100000;
        byte[] serializedEvent = SerializerUtil.objectToByteArr(new SupportBean(generatedUUID, randomNumber));
        producer.send(new ProducerRecord<>(TOPIC, 0, timestamp, "key", serializedEvent));

        SupportAwaitUtil.awaitOrFail(10, TimeUnit.SECONDS, "failed to receive expected event", (Supplier<Object>) () -> {
            for (EventBean[] events : listener.getEvents()) {
                for (EventBean event : events) {
                    SupportBean bean = (SupportBean) event.getUnderlying();
                    if (bean.getStringProp().equals(generatedUUID) && bean.getIntProp() == randomNumber) {
                        return true;
                    }
                }
            }
            return null;
        });

        assertEquals(timestamp, runtime.getEventService().getCurrentTime());

        producer.close();
        runtime.destroy();
    }
}
