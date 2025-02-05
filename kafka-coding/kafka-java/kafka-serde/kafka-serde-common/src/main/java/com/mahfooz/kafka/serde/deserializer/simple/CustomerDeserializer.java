package com.mahfooz.kafka.serde.deserializer.simple;

import com.mahfooz.kafka.model.Customer;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Nothing to configure
    }

    @Override
    public Customer deserialize(String topic, byte[] data) {

        if (data!=null)
        {
            ByteBuffer buffer = ByteBuffer.wrap(data);
            int id=buffer.getInt();
            int stringSize=buffer.getInt();
            byte [] nameByte=new byte[stringSize];
            buffer.get(nameByte,0,stringSize);
            String name=new String(nameByte);
            return new Customer(id,name);
        }
        return null;
    }

    @Override
    public void close() {
        //Nothing to close
    }
}
