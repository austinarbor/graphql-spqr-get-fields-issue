package dev.aga.graphql.service;

import dev.aga.graphql.model.Model;
import graphql.schema.SelectedField;
import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SomeService {

    @GraphQLMutation(name = "saveData")
    public Model saveData(@GraphQLNonNull Integer id, @GraphQLEnvironment ResolutionEnvironment env) {
        List<SelectedField> fields = env.dataFetchingEnvironment.getSelectionSet().getFields();
        log.info("Fields: {}", fields);
        return new Model();
    }

    @GraphQLQuery(name = "getData")
    public Model getData(@GraphQLEnvironment ResolutionEnvironment env) {
        List<SelectedField> fields = env.dataFetchingEnvironment.getSelectionSet().getFields();
        log.info("Fields: {}", fields);
        return new Model();
    }
}
