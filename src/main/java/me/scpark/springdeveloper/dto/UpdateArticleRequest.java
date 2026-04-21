package me.scpark.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor // nw UpdateArticleequest('title', 'content');
@NoArgsConstructor // new UpdateArticleRequest();
@Getter // UpdateArticleRequestContent();
public class UpdateArticleRequest {
    private String title;
    private String content;
}
