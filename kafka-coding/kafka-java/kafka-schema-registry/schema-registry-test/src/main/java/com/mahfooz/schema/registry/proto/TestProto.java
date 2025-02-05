package com.mahfooz.schema.registry.proto;

import com.google.protobuf.ByteString;
import com.mahfooz.schema.registry.Utils;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;

import java.util.concurrent.ExecutionException;

public class TestProto {

    private TestProto() {
        //prevent instantiation
    }

    private static Test.ProtoTest testValue() {
        return Test.ProtoTest.newBuilder()
                                            .setId(ByteString.copyFrom(Utils.uuidBytes()))
                                            .setCounter(1L)
                                            .setInput("String")
                                            .addResults(ResultOuterClass.Result.newBuilder()
                                                                               .setUp("STRING")
                                                                               .setDown("string")
                                                                               .build())
                                            .setBy(Test.ProtoTest.Language.Java)
                                            .build();
    }

    @SuppressWarnings("unchecked")
    public static void produceOne() throws ExecutionException, InterruptedException {
        Utils.produceOne(testValue(), KafkaProtobufSerializer.class);
    }
}
