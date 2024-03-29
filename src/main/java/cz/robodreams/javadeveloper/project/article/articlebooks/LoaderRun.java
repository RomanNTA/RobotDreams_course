package cz.robodreams.javadeveloper.project.article.articlebooks;

import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;

public class LoaderRun extends Thread implements SubjectAdd<Article> {

    private ArticlesRepository articlesRepository;
    public LoaderRun(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @Override
    public void run() {
        new GeneratorImpl(this);
    }

    public Boolean add(Article value) {
        articlesRepository.add(value);
        return true;
    }



}
