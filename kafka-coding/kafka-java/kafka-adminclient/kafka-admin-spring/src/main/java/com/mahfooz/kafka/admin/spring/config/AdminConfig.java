/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahfooz.kafka.admin.spring.config;

/**
 *
 * @author malam
 */
import java.util.Collections;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "kafka")
public class AdminConfig {


  private Map<String, String> admin;

  public void setAdmin(Map<String, String> admin) {
    this.admin = admin;
  }

  public Map<String, String> getAdmin() {
    return admin;
  }

  public Map<String, Object> getGenericProperties() {
    return Collections.<String, Object>unmodifiableMap(admin);
  }

  @Bean
  public AdminClient getClient() {
    return AdminClient.create(getGenericProperties());
  }

}
