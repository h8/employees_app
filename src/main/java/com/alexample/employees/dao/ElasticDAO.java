package com.alexample.employees.dao;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.elasticsearch.rest.RestStatus.OK;

@Service
public class ElasticDAO {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RestHighLevelClient client;

    @Autowired
    public ElasticDAO(RestHighLevelClient client) {
        this.client = client;
    }

    public Optional<String> hasClicksAndViews(int id) throws IOException {
        SearchResponse searchResponse = client.search(getSearchRequest(id));

        if (searchResponse.status() != OK) {
            log.error("Error while querying ElasticSearch. Status was: " + searchResponse.status());
            throw new IOException("Failed to get response from ES.");
        }

        for (SearchHit hit : searchResponse.getHits()) {
            String depId = hit.getId();
            for (Object stats : hit.getSourceAsMap().values()) {
                for (Object employee : (List) stats) {
                    if (parseCheckId(employee, id)) {
                        return Optional.of(depId);
                    }
                }
            }
        }

        return Optional.empty();
    }

    private SearchRequest getSearchRequest(int id) {
        SearchRequest searchRequest = new SearchRequest("department");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
                .boolQuery()
                .must(QueryBuilders.matchQuery("stats_per_employee.emp_no", id))
        );

        searchRequest.source(searchSourceBuilder);

        return searchRequest;
    }

    private boolean parseCheckId(Object o, int id) {
        try {
            Map data = (HashMap) o;

            int empNo = (int) data.get("emp_no");
            int views = (int) data.get("views");
            int clicks = (int) data.get("clicks");

            return (views > 0 && clicks > 0 && empNo == id);
        } catch (Throwable t) {
            return false;
        }
    }
}
