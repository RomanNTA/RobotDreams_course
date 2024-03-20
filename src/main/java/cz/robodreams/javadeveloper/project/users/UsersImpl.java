package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.common.SubjectsImpl;
import cz.robodreams.javadeveloper.project.common.Util;


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