/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahfooz.kafka.admin.spring.model;

/**
 *
 * @author malam
 */
import java.util.Collection;
import java.util.HashSet;

public class ClusterInfo {

  private String clusterId;

  private Collection<ClusterNode> nodes;

  private String leaderNodeId;

  public String getLeaderNodeId() {
    return leaderNodeId;
  }

  public String getClusterId() {
    return clusterId;
  }

  public Collection<ClusterNode> getNodes() {
    return nodes;
  }

  public static final class ClusterInfoBuilder {

    private String clusterId;
    private final Collection<ClusterNode> nodes = new HashSet<>();

    private String leaderNodeId;

    private ClusterInfoBuilder() {
    }

    public static ClusterInfoBuilder aClusterInfo() {
      return new ClusterInfoBuilder();
    }

    public ClusterInfoBuilder withClusterId(String clusterId) {
      this.clusterId = clusterId;
      return this;
    }

    public ClusterInfoBuilder addNode(ClusterNode node) {
      this.nodes.add(node);
      return this;
    }

    public ClusterInfoBuilder withLeaderNodeId(String leaderNodeId) {
      this.leaderNodeId = leaderNodeId;
      return this;
    }

    public ClusterInfo build() {
      ClusterInfo clusterInfo = new ClusterInfo();
      clusterInfo.nodes = this.nodes;
      clusterInfo.clusterId = this.clusterId;
      clusterInfo.leaderNodeId = this.leaderNodeId;
      return clusterInfo;
    }
  }
}