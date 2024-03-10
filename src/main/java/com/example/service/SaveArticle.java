package com.example.service;

import com.example.model.Article;
import com.example.model.SaveArticleRequest;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SaveArticle {

    public Uni<Void> execute(SaveArticleRequest request) {
        Article article = Article.builder()
                .title(request.title())
                .content(request.content())
                .build();

        return article.persist()
                .onItem()
                .ignore()
                .andContinueWithNull();
    }

}
