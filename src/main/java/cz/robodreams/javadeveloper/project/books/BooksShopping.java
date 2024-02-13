package cz.robodreams.javadeveloper.project.books;


public class BooksShopping extends Article implements IBooksShopping {

    private Integer money;

    public Integer buyBooks(int money){
        this.money = money;
        new BooksGenerator(this, -1);
        // kolik stály knihy
        return money - this.money;
    }

    @Override
    public Boolean add(int key, IItem value) {

        if (value.getPrice() <= money ){
            //line();
            super.add(key, value);
            money -= value.getPrice();
            System.out.println(value.getShortInfoBuying());
            System.out.println("\t... nákup knihy v ceně " + value.getPrice() + " Kč. Zbývá " + money + " Kč");
            return true;
        }
        // nejsou money ... konec
        return false;
    }

}
