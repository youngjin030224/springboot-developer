package me.scpark.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.dto.ArticleResponse;
import me.scpark.springdeveloper.dto.UpdateArticleRequest;
import me.scpark.springdeveloper.service.BlogService;
import me.scpark.springdeveloper.dao.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    private Object updatedArticle;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest articleRequest) {
        Article article = blogService.save(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<Article> articles = blogService.findAll();
        List<ArticleResponse> result = articles.stream().map(ArticleResponse::new).toList();
//        List<ArticleResponse> result = new ArrayList<>();
//        for (Article a: articles) {
//            result.add(new ArticleResponse(a));
//        }

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/articles/{id}") // /api/articles/3
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));

    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,@RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id, request);
        return ResponseEntity.ok().body(updateArticle);
    }
}