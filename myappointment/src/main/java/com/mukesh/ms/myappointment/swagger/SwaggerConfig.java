package com.mukesh.ms.myappointment.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    Server server = new Server().url("http://localhost:9000/health-service");
    server.description("Health Service API");
    List<Server> servers = new ArrayList<>();
    servers.add(server);

    return new OpenAPI()
            .tags(listOfTags())
            .servers(servers);
  }

  private Components getComponents() {
    Components components = new Components();

    // security schemes
    SecurityScheme basicAuth = new SecurityScheme();
    basicAuth.setType(SecurityScheme.Type.HTTP);
    basicAuth.setScheme("basic");
    Map securitySchemeMap = new HashMap();
    securitySchemeMap.put("Basic", basicAuth);
    components.securitySchemes(securitySchemeMap);

    return components;
  }

  private List<Tag> listOfTags() {
    return Arrays.asList(
            new Tag().name("appointment"),
            new Tag().name("user"),
            new Tag().name("health"));
  }
}
