//package cz.robodreams.javadeveloper.project.event;
//
//import cz.robodreams.javadeveloper.project.article.articlebooks.ServiceBooksShopping;
//import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Books;
//import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
//import cz.robodreams.javadeveloper.project.common.*;
//import cz.robodreams.javadeveloper.project.lending.Lending;
//import cz.robodreams.javadeveloper.project.users.Users;
//import cz.robodreams.javadeveloper.project.users.UsersImplNewMembers;
//
//public class EventManager extends SubjectsImpl<IEvent> {
//
//    private Books books;
//    private Users users;
//    private Lending lending;
//
//    //  private EventAccount account;
//
//    public EventManager() {
//        this.books = Service.getInstance().getBooks();
//        this.users = Service.getInstance().getUser();
//        this.lending = Service.getInstance().getLending();
////        this.account = Acc.getInstance();
////        this.account IEventAdd
//
//    }
//
//
//    @Override
//    public ArticleType getArticleType() {
//        return null;
//    }
//
//    @Override
//    public Integer getId() {
//        return null;
//    }
//
//    public Boolean add(IEvent value) {
//        Boolean result = super.add(value);
//        value.run();
//        return result;
//    }
//
//    @Override
//    public void generator(int count) {
//
//        EventAccount.getInstance();
//        int i = EventAccount.getCash();
//        i = random((int) (i * 0.2), (int) (i * 0.8));
//        add(new EventDonate(random(3, 8) * 1000));
//
//        EventAccount.getInstance();
//        i = (int) (EventAccount.getCash() * 0.8);
//        add(new EventBuyBooks().setSuplier(new ServiceBooksShopping()).setMoneyToBuyBooks(i));
//
//        EventAccount.getInstance();
//        i = (int) (EventAccount.getCash() * 0.8);
//        add(new EventBuyBooks().setSuplier(new ServiceBooksShopping()).setMoneyToBuyBooks(i));
//
//        i = random(2);
//        add(new EventNewUsers().setPeople(new UsersImplNewMembers()).setCountOfNewPerson(i));
//
//    }
//
//
//    @Override
//    public void show(int id, Parameters parameters) {
//        return;
//    }
//
//    private Integer random(int max) {
//        return Util.getRandomId(0, max);
//    }
//
//    private Integer random(int min, int max) {
//        return Util.getRandomId(min, max);
//    }
//
//
//}
