//package cz.robodreams.javadeveloper.project.article.articlemagazines;
//
//import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
//import cz.robodreams.javadeveloper.project.article.articlebooks.BooksGeneratorImpl;
//import cz.robodreams.javadeveloper.project.article.articlebooks.PeriodicsGeneratorImpl;
//import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
//import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Periodic;
//import cz.robodreams.javadeveloper.project.article.interfaces.Article;
//import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
//import cz.robodreams.javadeveloper.project.common.SubjectAdd;
//
//public class ServicePeriodicGenerator implements SubjectAdd<Periodic> {
//
//    String MESSAGE_BUY_COMPLETED = "\t... nákup knihy v ceně %d Kč. Zbývá %d Kč";
//
//    private Integer money;
//    private ArticlesRepository articlesRepository;
//
//    public ServicePeriodicGenerator(ArticlesRepository articlesRepository) {
//        this.articlesRepository = articlesRepository;
//    }
//
//
////
////    private ArticlesRepository articles;
////
////    public ServiceBookGenerator(ArticlesRepositoryImpl rep) {
////        this.articles = rep;
////    }
//
//    public void generator(int count) {
//        new PeriodicsGeneratorImpl(this, count);
//    }
//
//    @Override
//    public Boolean add(Book value) {
//        return this.articlesRepository.add(value);
//    }
//
////
////    public Integer buyBooks(int money) {
////        this.money = money;
////        new BooksGeneratorImpl(this, -1);
////        // kolik stály knihy
////        return money - this.money;
////    }
////
////    @Override
////    public Boolean add(Book value) {
////
////        if (value.getPrice() <= money) {
////            //line();
////            articlesRepository.add(value);
////            money -= value.getPrice();
////            System.out.println(value.getShortInfoBuying());
////            System.out.println(String.format(MESSAGE_BUY_COMPLETED, value.getPrice(), money));
////            return true;
////        }
////        // nejsou money ... konec
////        return false;
////    }
//
//}