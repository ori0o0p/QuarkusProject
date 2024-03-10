package com.example.resource;


import com.example.model.Article;
import com.example.model.SaveArticleRequest;
import com.example.service.DeleteArticle;
import com.example.service.SaveArticle;
import com.example.service.UpdateArticle;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleResource {

    @Inject
    SaveArticle saveArticle;

    @Inject
    UpdateArticle updateArticle;

    @Inject
    DeleteArticle deleteArticle;

    @GET
    public Multi<Article> list() {
        return Article.findAllUser();
    }

    @GET
    @Path("/{articleId}")
    public Uni<Article> findOne(@PathParam("articleId") String id) {
        return Article.findArticleById(id);
    }

    @POST
    public Uni<Void> save(@RequestBody SaveArticleRequest request) {
        return saveArticle.execute(request);
    }

    @PATCH
    @Path("/{articleId}")
    public Uni<Void> update(@RequestBody SaveArticleRequest request, @PathParam("articleId") String id) {
        return updateArticle.execute(id, request);
    }

    @DELETE
    @Path("/{articleId}")
    public Uni<Void> delete(@PathParam("articleId") String id) {
        return deleteArticle.execute(id);
    }

}
