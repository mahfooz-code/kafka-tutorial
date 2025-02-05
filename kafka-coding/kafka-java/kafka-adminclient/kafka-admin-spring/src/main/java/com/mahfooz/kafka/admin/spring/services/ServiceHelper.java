/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahfooz.kafka.admin.spring.services;

/**
 *
 * @author malam
 */
import com.mahfooz.kafka.admin.spring.model.ClusterNode;
import com.mahfooz.kafka.admin.spring.model.ClusterNode.ClusterNodeBuilder;
import com.mahfooz.kafka.admin.spring.model.Topic;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.Node;

public class ServiceHelper {

  private ServiceHelper() {
  }

  public static NewTopic fromTopic(Topic topic) {
    return new NewTopic(topic.getName(), topic.getPartitions(), topic.getReplicationFactor());
  }

  public static int compareByName(Topic a, Topic b) {
    return a.getName().compareTo(b.getName());
  }

  public static ClusterNode fromNode (Node node){
    return ClusterNodeBuilder.aClusterNode()
        .withHost(node.host())
        .withId(node.idString())
        .withPort(node.port())
        .withRack(node.rack())
        .build();
  }

}
