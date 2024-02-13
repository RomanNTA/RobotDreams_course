package cz.robodreams.javadeveloper.project;

public class Main {

    public static void main(String[] args) {

        System.out.println();
        System.out.println();
        System.out.println("   Dnes byla slavnostně otevřená nová knihovna ve Švestkové Lhotě.");
        System.out.println();
        System.out.println();

        ICityLibrary library = new CityLibrary();
        library.oneMonth();
    }

}
