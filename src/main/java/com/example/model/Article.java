package com.example.model;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.Duration;

@Setter
@Getter
public class Article extends ReactivePanacheMongoEntity {

    @Id
    public ObjectId id;

    public String title;

    public String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Uni<Article> findArticleById(String id) {
        return findById(id)
                .ifNoItem()
                .after(Duration.ZERO)
                .fail()
                .onItem().transform(item -> (Article)item);
    }

    public static Multi<Article> findAllUser() {
        return streamAll();
    }

}
