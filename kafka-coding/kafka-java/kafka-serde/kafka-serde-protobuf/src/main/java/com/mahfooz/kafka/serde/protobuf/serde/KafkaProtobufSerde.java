package com.mahfooz.kafka.serde.protobuf.serde;

import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.mahfooz.kafka.serde.protobuf.deserializer.KafkaProtobufDeserializer;
import com.mahfooz.kafka.serde.protobuf.serializer.KafkaProtobufSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class KafkaProtobufSerde<T extends MessageLite> implements Serde<T> {

    private final Serializer<T> serializer;
    private final Deserializer<T> deserializer;

    /**
     * Returns a new instance of {@link KafkaProtobufSerde}.
     *
     * @param parser The Protobuf {@link Parser}.
     */
    public KafkaProtobufSerde(Parser<T> parser) {
        serializer = new KafkaProtobufSerializer<>();
        deserializer = new KafkaProtobufDeserializer<>(parser);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }

    @Override
    public Serializer<T> serializer() {
        return serializer;
    }

    @Override
    public Deserializer<T> deserializer() {
        return deserializer;
    }
}
