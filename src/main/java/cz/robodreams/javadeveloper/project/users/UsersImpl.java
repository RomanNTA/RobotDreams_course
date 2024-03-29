package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.common.SubjectsImpl;
import cz.robodreams.javadeveloper.project.common.Util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;


public class UsersImpl extends SubjectsImpl<User> implements Users, SubjectAdd<User> {

    @Override
    public void generator(int countOfNewPerson) {
        new UserGenerator(this, countOfNewPerson);
    }

    @Override
    public void show(int id, ShowSubjectItems showItems) {
        Util.line();
        get(id).show(showItems);
    }

    @Override
    public List<String> getUsersDriver(ShowSubjectItems showItems){

        return repository.stream()
                .map(user -> user.getResultShow(showItems))
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public Map<Integer, User> getUsersClient(){

            try {
                AtomicInteger counter = new AtomicInteger(0);
                return repository.stream()
                        .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));

            } catch (RuntimeException e) {
                return new HashMap<>();
            }
    }









    @Override
    public String toString() {
        return "Users{ count of user : " + repository.size() + '}';
    }

    @Override
    public ArticleType getArticleType() {
        return null;
    }

    @Override
    public Integer getId() {
        return null;
    }


}