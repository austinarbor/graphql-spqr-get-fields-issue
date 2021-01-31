package dev.aga.graphql.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/queries")
public class GraphQLController {

    private final GraphQL graphQL;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> graphqlQuery(@RequestBody Map<String, Object> request, HttpServletRequest req) {
        GraphQLContext ctx = GraphQLContext.newContext().build();
        ExecutionResult result = graphQL.execute(
                ExecutionInput.newExecutionInput()
                        .query((String) request.get("query"))
                        .operationName((String) request.get("operationName"))
                        .variables(Optional.ofNullable((Map<String, Object>) request.get("variables")).orElse(Collections.emptyMap()))
                        .context(ctx)
                        .build()
        );

        return result.toSpecification();
    }

}
