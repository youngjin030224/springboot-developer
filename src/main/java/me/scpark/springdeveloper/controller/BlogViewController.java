package me.scpark.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.dao.Article;
import me.scpark.springdeveloper.dao.User;
import me.scpark.springdeveloper.dto.ArticleResponse;
import me.scpark.springdeveloper.dto.ArticleViewResponse;
import me.scpark.springdeveloper.service.BlogService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping({"/article", "/articles"})
    public String getArticles(Model model, @AuthenticationPrincipal User user) {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        model.addAttribute("articles", articles);
        addNickname(model, user);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        addNickname(model, user);
        return "article";
    }

    @GetMapping("new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model,
                             @AuthenticationPrincipal User user) {
        if (id != null) {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        } else {
            model.addAttribute("article", new ArticleViewResponse());
        }
        addNickname(model, user);
        return "newarticel";
    }

    private void addNickname(Model model, User user) {
        if (user != null) {
            model.addAttribute("nickname", user.getNickname());
        }
    }
}
