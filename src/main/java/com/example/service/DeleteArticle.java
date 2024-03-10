package com.example.service;

import com.example.model.Article;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeleteArticle {

    public Uni<Void> execute(String id) {
        return Article.findArticleById(id)
                .call(ReactivePanacheMongoEntityBase::delete)
                .replaceWithVoid();
    }

}
