package com.mahfooz.kafka.serde.serializer.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahfooz.kafka.model.User;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {
        // configuration
    }

    @Override
    public byte[] serialize(String arg0, User user) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(user).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void close() {
        // close
    }
}