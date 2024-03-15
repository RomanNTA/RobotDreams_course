package cz.robodreams.javadeveloper.project.article;


import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ArticleImpl  {

    private ArticleType articleType;
    private Integer id;




}
