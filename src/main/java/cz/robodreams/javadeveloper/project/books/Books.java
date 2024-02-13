package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.common.ASubject;
import cz.robodreams.javadeveloper.project.common.UsefulProc;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Books extends ASubject<IBook> implements IBooks<IBook> {

//    private Boolean isBuying;
    private Integer money;



    @Override
    public void generator(int count) {
//        isBuying = false;
        new BooksGenerator(this, count);
    }
//
//    public Integer buyBooks(int money){
//        isBuying = true;
//        this.money = money;
//        new BooksGenerator(this, -1);
//        // kolik stály knihy
//        return money - this.money;
//    }

    @Override
    public Boolean add(int key, IBook value) {

//        // generování podle počtu knih
//        if (!isBuying){
            return super.add(key, value);
//        }
//
//        // generování/ nákup podle ceny knihy
//        if (value.getPrice() <= money ){
//            //line();
//            super.add(key, value);
//            money -= value.getPrice();
//            System.out.println(value.getShortInfoBuying());
//            System.out.println("\t... nákup knihy v ceně " + value.getPrice() + " Kč. Zbývá " + money + " Kč");
//            return true;
//        }
//        // nejsou money ... konec
//        return false;
    }

    @Override
    public void show(int id, Boolean shortLongFormat) {
        line();
        get(id).show(true);
    }


    @Override
    public IBook getRandomSubject() {
        try {
            return (repository.values().stream()
                    .skip(UsefulProc.getRandomId(0,repository.size()-1))
                    .findAny()
            ).get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String showBookGenre(Boolean returnRandomGenre, Boolean showAllGenre) {

        Map<String, Long> booksGenre = repository.values().stream()
                .filter(x -> x.getGenre().length() > 0)
                .filter(x -> !x.getBorrowed())
                .map(x -> x.getGenre())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (showAllGenre) {
            System.out.println("Seznam žánrů v knihovně.");
            System.out.println("=".repeat(30));
            booksGenre.entrySet().stream().forEach(x -> System.out.println(x.getKey()));
        }

        String result = "";
        try {
            if (returnRandomGenre) {
                result = (booksGenre.keySet().stream()
                        .skip(UsefulProc.getRandomId(0,booksGenre.size()-1))
                        .findAny()
                ).get();
            }
        } catch (NullPointerException e) {
            return "";
        }
        return result;
    }

    public void showBooksAccordingToGenre(String genre) {

        List<Integer> listOfBooks = repository.values().stream()
                .filter(x -> !x.getBorrowed())
                .filter(p -> p.getGenre() == genre)
                .map(x -> x.getIdBook())
                .toList();

        System.out.printf("Seznam voných knih v knihovně pro žánr '%s'.\n", genre);
        for (int i = 0; i < listOfBooks.size(); i++) {
            this.show(listOfBooks.get(i), true);
        }
    }
}
