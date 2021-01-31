package dev.aga.graphql.config;

import dev.aga.graphql.service.SomeService;
import graphql.GraphQL;
import graphql.analysis.MaxQueryDepthInstrumentation;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    GraphQLSchema graphQLSchema(SomeService service) {
        return new GraphQLSchemaGenerator()
                .withBasePackages("dev.aga")
                .withOperationsFromSingleton(service, AopUtils.getTargetClass(service))
                .generate();
    }

    @Bean
    GraphQL graphQL(GraphQLSchema schema) {
        return new GraphQL.Builder(schema).instrumentation(new MaxQueryDepthInstrumentation(30)).build();
    }
}
