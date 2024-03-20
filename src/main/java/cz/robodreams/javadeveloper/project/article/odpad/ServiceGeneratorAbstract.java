//package cz.robodreams.javadeveloper.project.article.articlebooks;
//
//import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
//import cz.robodreams.javadeveloper.project.article.interfaces.Article;
//import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
//import cz.robodreams.javadeveloper.project.common.SubjectAdd;
//
//public class ServiceGeneratorAbstract<T extends Article> implements SubjectAdd<T> {
//
//    protected ArticlesRepository repository;
//
//    public ServiceGeneratorAbstract(ArticlesRepositoryImpl repository) {
//        this.repository = repository;
//    }
//
//    public abstract void generator(int count);
//
//    @Override
//    public Boolean add(T value) {
//        return this.repository.add(value);
//    }
//
//}
