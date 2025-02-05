package com.mahfooz.kafka.serde.kryo.deser;

import com.mahfooz.kafka.serde.kryo.util.KryoUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class KryoDeserializer implements Deserializer<Object> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return KryoUtils.deserialize(bytes);
    }

    @Override
    public void close() {

    }

}