package com.example.service;

import com.example.model.Article;
import com.example.model.SaveArticleRequest;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateArticle {

    public Uni<Void> execute(String id, SaveArticleRequest request) {
        return Article.findArticleById(id)
                .onItem()
                .ifNotNull()
                .transform(article -> update(article, request))
                .call(article -> article.persistOrUpdate())
                .replaceWithVoid();
    }

    private Article update(Article article, SaveArticleRequest request) {
        article.setTitle(request.title());
        article.setContent(request.content());

        return article;
    }

}
