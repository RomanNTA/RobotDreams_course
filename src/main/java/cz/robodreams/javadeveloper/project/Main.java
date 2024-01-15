package cz.robodreams.javadeveloper.project;
import cz.robodreams.javadeveloper.project.users.People;
import cz.robodreams.javadeveloper.project.users.Users;


public class Main {


    public static void main(String[] args) {

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Hello virtual libray :)");
        System.out.println("------------------------------------------------------------------");

        ICityLibrary library = new CityLibrary();
        library.oneMonth();







    }











    //
//        String strFile1 = "db_books";

//        Loader loader = new Loader();
//
//        DataFromFile data = new DataFromFile(strFile1,loader);
//        data.invalidateAndReload();
//        System.out.println(data.getFields(0,0 ));

//        {
//            AColumn x = new AColumn(1);
//            x.setName( "Roman");
//            x.setType(IColumn.TypeINTEGER);
//            x.setAdditional("Přídavek");
//            x.add(0, 1230);
//            x.add(2, 1232);
//            x.add(4, 1234);
//            System.out.println(x.toString());
//        }
//
//        AColumn xa = new AColumn(2);
//        xa.setName( "Roman");
//        xa.setType(IColumn.TypeSTRING);
//        xa.setAdditional("Přídavek");
//        xa.add(0, "1230");
//        xa.add(2, "1232");
//        xa.add(4, "1234");
//        System.out.println(xa.toString());
//
//        {
//            LocalDateTime now = LocalDateTime.now();
//            System.out.println(now);
//            AColumn x = new AColumn(3);
//            x.setName( "Roman");
//            x.setType(IColumn.TypeDATETIME);
//            x.setAdditional("Přídavek");
//
//            x.add(0, now.plusDays(1));
//            x.add(2, now.plusHours(1));
//            x.add(4, now.plusMonths(1));
//            System.out.println(x.toString());
//        }


//        {
//            ITable x = new ATable(strFile1);
//
//            x.



//            AColumn col1 = x.addColumn("sloupec A", IColumn.TypeSTRING);
//            AColumn col2 = x.addColumn("sloupec B", IColumn.TypeINTEGER);
//            AColumn col3 = x.addColumn("sloupec C", IColumn.TypeDATETIME);

//            col1.setValueString(2,"Roman 1");
//            col1.setValueString(4,"Roman 2");
//            col1.setValueString(5,"Roman 3");
//            col1.setValueString(7,"Roman 7");
//
//            col2.setValueInteger(2,1);
//            col2.setValueInteger(4,2);
//            col2.setValueInteger(5,3);
//            col2.setValueInteger(7,7);
//
//            LocalDateTime dt = LocalDateTime.now();
//            dt.now();
//            col3.setValueDateTime(2,dt);
//            col3.setValueDateTime(4,dt.plusMinutes(10));
//            col3.setValueDateTime(5,dt.plusMinutes(20));
//            col3.setValueDateTime(7,dt.plusMinutes(30));
//
//
//            System.out.println(x);
//
//            System.out.println(col1.typeToString());
//
//            System.out.println(col2.typeToString());
//
//            System.out.println(col3.typeToString());


/*
            AColumn col2 = x.addColumn();
            col2.setType(IColumn.TypeINTEGER);

            AColumn col3 = x.addColumn();
            col3.setType(IColumn.TypeDATETIME);
*/
//            x.
//
//            x.setName("Roman");
//            x.setType(IColumn.TypeINTEGER);
//            x.setAdditional("Přídavek");
//            x.add(0, 1230);
//            x.add(2, 1232);
//            x.add(4, 1234);
//            System.out.println(x.toString());
//        }
//
//    }

//    private static void printInputStream(InputStream is) {
//
//        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
//             BufferedReader reader = new BufferedReader(streamReader)) {
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }


    public void testovaci(){




//        for(IDb.EnumDbBooks m : IDb.EnumDbBooks.values()) {
//            System.out.println(m.name() + " => " + m.value()  + " - " + m.value());
//        }
//
//
//
////§        IDbBooks db = IDbBooks(IDbBooks.DbBooks.NAKLADATEL).DbBooks();
//
//        DbBooks db;
//
//        db.test();
//        System.out.println(db.);
//
//        //= IDbBooks.DbBooks();
//
//        // System.out.println(db.toString());


    }



}
