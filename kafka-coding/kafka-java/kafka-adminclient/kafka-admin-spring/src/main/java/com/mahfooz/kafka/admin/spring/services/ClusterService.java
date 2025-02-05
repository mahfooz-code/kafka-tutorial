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
import com.mahfooz.kafka.admin.spring.model.ClusterInfo;
import com.mahfooz.kafka.admin.spring.model.ClusterInfo.ClusterInfoBuilder;
import com.mahfooz.kafka.admin.spring.model.ClusterNode;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClusterService {

  @Autowired
  private AdminClient adminClient;

  public ClusterInfo getClusterInfo() throws ExecutionException, InterruptedException {

    DescribeClusterResult call = adminClient.describeCluster();

    ClusterInfoBuilder builder = ClusterInfoBuilder.aClusterInfo()
        .withClusterId(call.clusterId().get());

    call
        .nodes()
        .get()
        .forEach(node ->
            builder
                .addNode(ServiceHelper.fromNode(node))
        );

    builder.withLeaderNodeId(call.controller().get().idString());

    return builder.build();
  }

  public ClusterNode getNodeById(String id) throws ExecutionException, InterruptedException {

    DescribeClusterResult call = adminClient.describeCluster();

    return call
        .nodes()
        .get()
        .stream()
        .filter(node -> id.equals(node.idString()))
        .map(ServiceHelper::fromNode)
        .findFirst()
        .orElseGet(null);
  }

  public ClusterNode getLeaderNode() throws ExecutionException, InterruptedException {
    return ServiceHelper.fromNode(adminClient.describeCluster().controller().get());
  }

  public Collection<ClusterNode> getNodes() throws ExecutionException, InterruptedException {
    DescribeClusterResult call = adminClient.describeCluster();
    return call.nodes()
        .get()
        .stream()
        .map(ServiceHelper::fromNode)
        .collect(Collectors.toList());
  }
}