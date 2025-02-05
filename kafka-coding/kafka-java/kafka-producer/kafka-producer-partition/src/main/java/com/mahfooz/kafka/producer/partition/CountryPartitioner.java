package com.mahfooz.kafka.producer.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import java.util.HashMap;
import java.util.Map;

public class CountryPartitioner implements Partitioner {

    private static Map<String, Integer> countryToPartitionMap;

<<<<<<< HEAD
    // This method will get called at the start, you should use it to do one time startup activity
=======
    // This method will gets called at the start, you should use it to do one time
    // startup activity
>>>>>>> 691e64dbe24618d3f48895ab1e33d5777267d869
    public void configure(Map<String, ?> configs) {
        System.out.println("Inside CountryPartitioner.configure " + configs);
        countryToPartitionMap = new HashMap<>();
        for (Map.Entry<String, ?> entry : configs.entrySet()) {
            if (entry.getKey().startsWith("partitions.")) {
                String keyName = entry.getKey();
                String value = (String) entry.getValue();
                System.out.println(keyName.substring(11));
                int partitionId = Integer.parseInt(keyName.substring(11));
                countryToPartitionMap.put(value, partitionId);
            }
        }
    }

    // This method will get called once for each message
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String countryName = ((String) value).split(":")[0];
        if (countryToPartitionMap.containsKey(countryName)) {
            // If the country is mapped to particular partition return it
            return countryToPartitionMap.get(countryName);
        } else {
            // If no country is mapped to particular partition distribute between remaining
            // partitions
            int noOfPartitions = cluster.topics().size();
            return value.hashCode() % noOfPartitions + countryToPartitionMap.size();
        }
    }

<<<<<<< HEAD
    // This method will get called at the end and gives your partitioner class chance to clean up
=======
    // This method will get called at the end and gives your partitioner class
    // chance to cleanup
>>>>>>> 691e64dbe24618d3f48895ab1e33d5777267d869
    public void close() {
    }
}