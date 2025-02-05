package com.mahfooz.kafka.serde.deserializer.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahfooz.kafka.model.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserDeserializer implements Deserializer<User> {

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
    }

    @Override
    public User deserialize(String key, byte[] userByte) {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(userByte, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
