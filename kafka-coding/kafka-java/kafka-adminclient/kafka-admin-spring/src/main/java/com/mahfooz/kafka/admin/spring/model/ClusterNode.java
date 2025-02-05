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
public class ClusterNode {

  private String id;

  private String host;

  private int port;

  private String rack;

  private ClusterNode() {
  }

  public String getId() {
    return id;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public static final class ClusterNodeBuilder {

    private String id;
    private String host;
    private int port;
    private String rack;

    private ClusterNodeBuilder() {
    }

    public static ClusterNodeBuilder aClusterNode() {
      return new ClusterNodeBuilder();
    }

    public ClusterNodeBuilder withId(String id) {
      this.id = id;
      return this;
    }

    public ClusterNodeBuilder withHost(String host) {
      this.host = host;
      return this;
    }

    public ClusterNodeBuilder withPort(int port) {
      this.port = port;
      return this;
    }

    public ClusterNodeBuilder withRack(String rack) {
      this.rack = rack;
      return this;
    }

    public ClusterNode build() {
      ClusterNode clusterNode = new ClusterNode();
      clusterNode.id = this.id;
      clusterNode.host = this.host;
      clusterNode.port = this.port;
      clusterNode.rack = this.rack;
      return clusterNode;
    }
  }
}