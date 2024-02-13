package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.common.ISubject;
import cz.robodreams.javadeveloper.project.common.ISubjectAdd;

public interface IBooks<T> extends ISubject<T>, ISubjectAdd<T> {

    void showBooksAccordingToGenre(String genre);

    String showBookGenre(Boolean returnRandomGenre, Boolean showAllGenre);


    /**
     * getter + setter
     */


}
