/*

kafka-console-consumer --zookeeper 10.133.43.94:2181/kafka --topic test --from-beginning

kafka-console-consumer --bootstrap-server 10.133.43.94:9092 --topic input-topic --from-beginning


 */

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Mahfooz Alam
 */
public class KafkaConsoleConsumer {

    private static final Logger log= LoggerFactory.getLogger(KafkaConsoleConsumer.class);

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "CountryCounter");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getCanonicalName());
        KafkaConsumer<String, String> consumer =
                new KafkaConsumer<String, String>(props);

        //Once we create a consumer, the next step is to subscribe to one or more topics
        consumer.subscribe(Collections.singletonList("customerCountries"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records)
                {
                    log.debug("topic = %s, partition = %d, offset = %d,customer = %s, country = %s\n",
                    record.topic(), record.partition(), record.offset(),
                            record.key(), record.value());

                    Map<String,Integer> custCountryMap=new HashMap<>();
                    int updatedCount = 1;
                    if (custCountryMap.containsKey(record.value())) {
                        updatedCount = custCountryMap.get(record.value()) + 1;
                    }
                    custCountryMap.put(record.value(), updatedCount);
                    JSONObject json = new JSONObject(custCountryMap);
                    System.out.println(json.toString(4));
                }
            }
        } finally {
            consumer.close();
        }
    }
}
