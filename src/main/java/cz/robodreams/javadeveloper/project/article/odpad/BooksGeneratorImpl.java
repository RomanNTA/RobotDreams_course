//package cz.robodreams.javadeveloper.project.article.articlebooks;
//
//import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
//import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.BooksGenerator;
//import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
//import cz.robodreams.javadeveloper.project.common.SubjectAdd;
//import cz.robodreams.javadeveloper.project.db.ATable;
//import cz.robodreams.javadeveloper.project.db.ITable;
//
//public class BooksGeneratorImpl implements BooksGenerator {
//
//    private static Integer counter = 0;
//    private ITable allBooks;
//
//    public BooksGeneratorImpl(SubjectAdd<Book> destination, int countOfNewBooks) {
//
//        if (destination == null) return;
//
//        this.allBooks = new ATable(DATABASE_FILE);
//
//        if (countOfNewBooks > 0) {
//            for (int i = 0; i < countOfNewBooks; i++) {
//                destination.add((getBook(counter++)));
//            }
//        }
//
////        // nákup knih podle ceny. Ukončí až odmítnutí knihy
////        if (countOfNewBooks < 0) {
////            while (destination.add((getBook(counter)))) {
////                counter++;
////            }
////        }
//    }
//
//    public Book getBook(int id) {
//
//        return BookImpl.builder()
//                .articleType(ArticleType.BOOKS)
//                .idArticle(id)
//                .title(allBooks.getString(id, BooksGenerator.TITLE))
//                .author(allBooks.getString(id, BooksGenerator.AUTHOR))
//                .numberOfPages(allBooks.getInteger(id, BooksGenerator.NUMBEROFPAGES))
//                .price(allBooks.getInteger(id, BooksGenerator.PRICE))
//                .isbn(allBooks.getString(id, BooksGenerator.ISBN))
//                .ean(allBooks.getString(id, BooksGenerator.EAN))
//                .custody(allBooks.getString(id, BooksGenerator.CUSTODY))
//                .genre(allBooks.getString(id, BooksGenerator.GENRE))
//                .publisher(allBooks.getString(id, BooksGenerator.PUBLISHER))
//                .profit(allBooks.getInteger(id, BooksGenerator.PROFIT))
//                .borrowed(false)
//                .borrowedReference(null)
//                .build();
//    }
//
//    public Integer getCountOfBooks() {
//        return allBooks.getRowsCount(1);
//    }
//
//}
