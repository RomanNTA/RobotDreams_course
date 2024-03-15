package cz.robodreams.javadeveloper.project.article.articlebooks.interfaces;

import cz.robodreams.javadeveloper.project.common.Subjects;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;


public interface Books<T> extends Subjects<T>, SubjectAdd<T> {

    void showBooksAccordingToGenre(String genre);

    String showBookGenre(Boolean returnRandomGenre, Boolean showAllGenre);


}
