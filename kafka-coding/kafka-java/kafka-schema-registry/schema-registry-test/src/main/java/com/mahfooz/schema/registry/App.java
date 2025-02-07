package com.mahfooz.schema.registry;

import com.mahfooz.schema.registry.avro.TestAvro;
import com.mahfooz.schema.registry.json.TestJson;
import com.mahfooz.schema.registry.proto.TestProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class App {

    private static final Logger LOGGER;

    static {
        String path = Objects.requireNonNull(App.class.getClassLoader().getResource("logging.properties")).getFile();
        System.setProperty("java.util.logging.config.file", path);
        LOGGER = LoggerFactory.getLogger(App.class);
    }

    public static void main(String args[]) {
        final long start = System.currentTimeMillis();
        try {
            TestAvro.produceOne();
            TestProto.produceOne();
            TestJson.produceOne();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("Some error occurred", e);
            Thread.currentThread().interrupt();
            System.exit(1);
        }
        final long ran = System.currentTimeMillis() - start;
        LOGGER.info("Running the tasks took {} milliseconds", ran);
    }
}
