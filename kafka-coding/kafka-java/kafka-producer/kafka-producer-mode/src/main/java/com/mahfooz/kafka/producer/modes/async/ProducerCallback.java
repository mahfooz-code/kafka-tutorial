/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahfooz.kafka.producer.modes.async;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 *
 * @author Mahfooz Alam
 */

public class ProducerCallback implements Callback {

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
        else {
            System.out.println("Record written to offset : "+recordMetadata.offset());
            System.out.println("Record written to partition : "+recordMetadata.partition());
        }
    }
}
