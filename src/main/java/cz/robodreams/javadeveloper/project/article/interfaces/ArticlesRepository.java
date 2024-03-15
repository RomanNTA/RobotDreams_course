package cz.robodreams.javadeveloper.project.article.interfaces;

import cz.robodreams.javadeveloper.project.common.Subjects;

public interface ArticlesRepository extends Subjects<Article> {

    void loadBooks(Integer count);

    void loadMagazines(Integer count);

    void loadNews(Integer count);


}
