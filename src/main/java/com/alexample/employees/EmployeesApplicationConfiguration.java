package com.alexample.employees;

import com.alexample.employees.graphql.Query;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeesApplicationConfiguration {
    @Value( "${es.host}" )
    private String esHost;

    @Value( "${es.port}" )
    private int esPort;

    @Bean
    DSLContext dslContext(DataSourceConnectionProvider provider) {
        /**
         * Required in order to have schema/db name free context
         */
        Settings settings = new Settings().withRenderSchema(false);

        return DSL.using(provider, SQLDialect.MYSQL, settings);
    }

    @Bean
    RestHighLevelClient createESClient() {
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(esHost, esPort, "http")));
    }
}