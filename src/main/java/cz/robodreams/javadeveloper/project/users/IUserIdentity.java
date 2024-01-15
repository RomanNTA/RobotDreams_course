package cz.robodreams.javadeveloper.project.users;

public interface IUserIdentity {

    enum GENDER {
        MAN,
        WOMAN;
    }


    String[] NAME_OF_MAN = {"Jan", "Adam", "Tomáš", "Matyáš", "Filip", "Ondřej", "Vojtěch", "Matěj", "David",
            "Lukáš", "Dominik", "Martin", "Daniel", "Šimon", "Petr", "Štěpán", "Antonín", "Jiří", "Marek",
            "Michal", "Kryštof", "Václav", "Tobiáš", "Josef", "Samuel", "Mikuláš", "Patrik", "František",
            "Tadeáš", "Pavel", "Sebastian", "Oliver", "Jonáš", "Richard", "Jáchym", "Viktor", "Michael",
            "Vít", "Jaroslav"
    };
    int COUNT_NAME_OF_MAN = NAME_OF_MAN.length;

    String[] SURNAME_OF_MAN = {"Bartoš", "Beneš", "Čech", "Čermák", "Černý", "Doležal", "Dostál", "Dvořák",
            "Fiala", "Hájek", "Horák", "Horváth", "Jelínek", "Kadlec", "Kolář", "Konečný", "Kopecký", "Král",
            "Kratochvíl", "Krejčí", "Kříž", "Kučera", "Malý", "Marek", "Musil", "Navrátil", "Němec", "Novotný",
            "Pokorný", "Polák", "Pospíšil", "Procházka", "Růžička", "Sedláček", "Soukup", "Svoboda", "Šimek",
            "Urban", "Vaněk", "Veselý", "Zeman"
    };
    int COUNT_SURNAME_OF_MAN = SURNAME_OF_MAN.length;

    String[] NAME_OF_WOMAN = {"Tereza", "Anna", "Adéla", "Natálie", "Ema", "Viktorie", "Sofie", "Karolína",
            "Kristýna", "Barbora", "Veronika", "Nela", "Lucie", "Julie", "Laura", "Kateřina", "Marie", "Emma",
            "Klára", "Aneta", "Amálie", "Rozálie", "Anežka", "Zuzana", "Nikol", "Nikola", "Sára", "Ella",
            "Michaela", "Gabriela", "Markéta", "Magdaléna", "Alžběta", "Elena", "Vanesa", "Adriana", "Linda",
            "Stella", "Jana"
    };
    int COUNT_NAME_OF_WOMAN = NAME_OF_WOMAN.length;

    String[] SURNAME_OF_WOMAN = {"Bartošová", "Benešová", "Čechová", "Čermáková", "Doležalová", "Dušková",
            "Dvořáková", "Fialová", "Hájková", "Holubová", "Horáková", "Horváthová", "Jelínková", "Kolářová",
            "Kovář", "Králová", "Kratochvílová", "Kučerová", "Marková", "Navrátilová", "Němcová", "Novák",
            "Nováková", "Poláková", "Pospíšilová", "Procházková", "Růžičková", "Sedláčková", "Svobodová",
            "Šimková", "Urbanová", "Vaňková", "Zemanová"
    };
    int COUNT_SURNAME_OF_WOMAN = SURNAME_OF_WOMAN.length;


    String[] STREETS = {"Breitfeldova", "Březinova", "Hlávkův most", "Hybešova", "Jirsíkova", "K Olympiku",
            "Kaizlovy sady", "Karlínské náměstí", "Karolinská", "Ke Štvanici", "Kollárova", "Korybutova",
            "Křižíkova", "Kubova", "Libeňský ostrov", "Lyčkovo náměstí", "Malého", "Molákova", "Na střelnici",
            "Na Špitálsku", "Nekvasilova", "Peckova", "Pernerova", "Petra Slezáka", "Pobřežní",
            "Pod plynojemem", "Pod výtopnou", "Prvního pluku", "Rohanské nábřeží", "Sokolovská", "Sovova",
            "Sudkovy sady", "Šaldova", "Štvanická lávka", "Těšnov", "Těšnovský tunel", "Thámova", "U invalidovny",
            "U Mlýnského kanálu", "U nádražní lávky", "U Sluncové", "Urxova", "Vítkova", "Wilsonova",
            "Za invalidovnou", "Za Karlínským přístavem", "Za Poříčskou bránou", "Zábranská"
    };
    int COUNT_STREETS = STREETS.length;




}


