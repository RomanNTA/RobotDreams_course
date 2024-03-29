
package cz.robodreams.javadeveloper.project.control.common;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface Const {

    String MESSAGES_FIRST_CONNECT = "CONNECT";


    String MESSAGES_SEND_MENU = "MESSAGES_SEND_MENU";
    String MESSAGES_SEND_MENU_CHOICE_BOOKS = "MESSAGES_SEND_MENU_CHOICE_BOOKS";
    String MESSAGES_PRINT_TEXT = "MESSAGES_PRINT_TEXT";



    String DRIVER_DLG2_WHAT_ROLE_label = "Role.";
    String DRIVER_DLG2_WHAT_ROLE_text = "Vyber jsi, jaká bude Tvá role ?";


    String DRIVER_RETURN_TO_ROOT = "DRIVER_RETURN_TO_ROOT";
    String DRIVER_DLG2_ROOT_MENU_label = "Hlavní menu.";
    String DRIVER_DLG2_ROOT_MENU_text = "V hlavním menu jsou obsažené výstupy ze systému knihovny";

    String DRIVER_SEND_DLG_LIST = "DRIVER_SEND_DLG_LIST";
    String DRIVER_SEND_DLG_LIST_label = "Výpis knih.";
    String DRIVER_SEND_DLG_LIST_text = "Výpis knih včetně zapůjčení a termínu.";


    String DRIVER_SEND_DLG_LIST_BORROWED = "DRIVER_SEND_DLG_LIST_BORROWED";
    String DRIVER_SEND_DLG_LIST_BORROWED_label = "Výpis vypůjčených knih.";
    String DRIVER_SEND_DLG_LIST_BORROWED_text = "Výpis vypůjčených knih včetně uživatele, zapůjčení a termínu.";


    String DRIVER_SEND_DLG_LIST_ALL_USERS = "DRIVER_SEND_DLG_LIST_ALL_USERS";
    String DRIVER_SEND_DLG_LIST_ALL_USERS_label = "Výpis uživatelů.";
    String DRIVER_SEND_DLG_LIST_ALL_USERS_text = "Výpis všech uživatelů knihovny včetně zapůjčených knih a termínu.";



    String DRIVER_SEND_DLG_LIST_ACCORDING_GENRE = "DRIVER_SEND_DLG_LIST_ACCORDING_GENRE";
    String DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_label = "Knižních obory vydavatelů knih.";
    String DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_text = "Seznam nabízených knižních oborů nových knih pro knihovnu.";


    String DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW = "DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW";
    String DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_label = "Nabídka knih.";
    String DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_text = "Seznam dostupných knih od vyadavatelů.";


    String CLIENT_RETURN_TO_ROOT = "CLIENT_RETURN_TO_ROOT";
    String CLIENT_DLG2_ROOT_MENU_label = "Hlavní menu";
    String CLIENT_DLG2_ROOT_MENU_text = "V hlavním menu jsou obsažené výstupy ze systému knihovny, přihlášení a výběr knih.";

    String CLIENT_SEND_DLG_LOGIN_USER = "CLIENT_SEND_DLG_LOGIN_USER";
    String CLIENT_SEND_DLG_LOGIN_USER_label = "Registrovaní uživatélé knihovny.";
    String CLIENT_SEND_DLG_LOGIN_USER_text = "Seznam obsahuje registrované uživatelé knihovny. Vyberte si svou postavu.";


    String CLIENT_SEND_DLG_LOAN_BOOKS = "CLIENT_SEND_DLG_LOAN_BOOKS";
    String CLIENT_SEND_DLG_LOAN_BOOKS_label = "Vyběr dostupných knih.";
    String CLIENT_SEND_DLG_LOAN_BOOKS_text = "Seznam obsahuje dostupné knihy v knihovně.";


    String CLIENT_SEND_DLG_LOAN_LIST = "CLIENT_SEND_DLG_LOAN_LIST";
    String CLIENT_SEND_DLG_LOAN_LIST_label = "Výpis půjčených knih uživatele";
    String CLIENT_SEND_DLG_LOAN_LIST_text = "Seznam obsahuje půjčené knihy klienta knihovny, včetně termínů.";



    String DRIVER_SEND_DLG_RELEASE_BOOKS = "DRIVER_SEND_DLG_RELEASE_BOOKS";
    String DRIVER_SEND_DLG_RELEASE_BOOKS_label = "Vrácení knihy";
    String DRIVER_SEND_DLG_RELEASE_BOOKS_text = "Vyberte knihu, kterou vrací %s";

    String DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION = "DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION";
    String DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION_label = "Vyběr uživatele";
    String DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION_text = "Vyberte uživatelé, který vrací knihy";











//
//    String MESSAGES_CLIENT_TO_SERVER = "MESSAGES CLIENT TO SERVER";
//    String MESSAGES_SERVER_TO_CLIENT = "MESSAGES SERVER TO CLIENT";
////    String MESSAGES_CONNECT_TO_ROOM = "MESSAGES CONNECT TO ROOM";
////    String MESSAGE_PATTERN = "%s//-//%s";
//    String MESSAGE_DELIMITER = "//-//";


    String EXIT = "EXIT";

    String EMPTY = "EMPTY";

    String REPLY = "reply";


    String SOCKET_HOST = "localhost";
    String CLIENT_ERROR = "Systémuvá chyba na straně klienta.";

//    String STR3_ANY_MESSAGE = "%s: %s";

    int SOCKET_PORT = 5001;


    BiPredicate<Integer, Integer> testIndex = (index, max) -> (index < max && index >= 0);

    Predicate<String> isStringFullAndNotNull = (str) -> ((str != null) && (!str.isBlank()));

    BiPredicate<MessageTransfer, String> doesThisTaskEquals = (mt, receiveTask) -> (mt != null && mt.task().equals(receiveTask));

    Predicate<Object> isNotNull = (o) -> ((o != null));



}
