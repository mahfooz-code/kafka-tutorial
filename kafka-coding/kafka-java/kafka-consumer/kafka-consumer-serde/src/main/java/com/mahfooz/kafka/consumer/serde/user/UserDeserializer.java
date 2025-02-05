package com.mahfooz.kafka.consumer.serde.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahfooz.kafka.model.User;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class UserDeserializer implements Deserializer<User> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public User deserialize(String arg, byte[] userByte) {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(userByte, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void close() {

    }
}
