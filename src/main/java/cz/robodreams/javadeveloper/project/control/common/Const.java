
package cz.robodreams.javadeveloper.project.control.common;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface Const {

    String MESSAGES_FIRST_CONNECT = "CONNECT";


    String MESSAGES_SEND_MENU = "MESSAGES SEND MENU";
    String MESSAGES_PRINT_TEXT = "MESSAGES PRINT TEXT";

    String MESSAGES_SEND_ROLE = "MESSAGES SEND ROLE";
    String MESSAGE_DLG2_WHAT_ROLE_label = "Role.";
    String MESSAGE_DLG2_WHAT_ROLE_text = "Vyber jsi, jaká bude Tvá role ?";


    String RETURN_TO_ROOT = "RETURN_TO_ROOT";
    String MESSAGE_DLG2_ROOT_MENU_label = "Hlavní menu.";
    String MESSAGE_DLG2_ROOT_MENU_text = "V hlavním menu jsou obsažené výstupy ze systému knihovny";

    String MESSAGE_SEND_DLG_LIST = "MESSAGE_SEND_DLG_LIST";
    String MESSAGE_SEND_DLG_LIST_label = "Výpis knih.";
    String MESSAGE_SEND_DLG_LIST_text = "Výpis knih včetně zapůjčení a termínu.";


    String MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE = "MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE";
    String MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_label = "Knižních obory vydavatelů knih.";
    String MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_text = "Seznam nabízených knižních oborů nových knih pro knihovnu.";



    String MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW = "MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW";
    String MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_label = "Nabídka knih.";
    String MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_text = "Seznam dostupných knih od vyadavatelů.";













    String MESSAGES_CLIENT_TO_SERVER = "MESSAGES CLIENT TO SERVER";
    String MESSAGES_SERVER_TO_CLIENT = "MESSAGES SERVER TO CLIENT";
//    String MESSAGES_CONNECT_TO_ROOM = "MESSAGES CONNECT TO ROOM";
//    String MESSAGE_PATTERN = "%s//-//%s";
    String MESSAGE_DELIMITER = "//-//";
    String EXIT = "EXIT";
    String EMPTY = "EMPTY";
    String REPLY = "reply";


    String SOCKET_HOST = "localhost";
    String CLIENT_ERROR = "Systémuvá chyba na straně klienta.";

//    String STR3_ANY_MESSAGE = "%s: %s";

    int SOCKET_PORT = 5001;


    BiPredicate<Integer, Integer> testIndex = (index, max) -> (index < max && index >= 0);
    Predicate<String> isStringFullAndNotNull = (str) -> ((str != null) && (!str.isBlank()));
    BiPredicate<MessageTransfer, String> doesThisTaskContain = (mt, receiveTask) -> (mt != null && mt.task().contains(receiveTask));

    Predicate<Object> isNotNull = (o) -> ((o != null));


}
