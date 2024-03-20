
package cz.robodreams.javadeveloper.project.control.common;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface Const {

    String MESSAGES_FIRST_CONNECT = "CONNECT";
    String MESSAGES_SET_CLIENT_ROLE = "SET CLIENT ROLE";






    String MESSAGES_SEND_NAME = "MESSAGES SEND NAME";
    String MESSAGES_CLIENT_TO_SERVER = "MESSAGES CLIENT TO SERVER";
    String MESSAGES_SERVER_TO_CLIENT = "MESSAGES SERVER TO CLIENT";
    String MESSAGES_CONNECT_TO_ROOM = "MESSAGES CONNECT TO ROOM";
    String MESSAGE_PATTERN = "%s//-//%s";
    String MESSAGE_DELIMITER = "//-//";
    String EXIT = "EXIT";
    String EMPTY = "EMPTY";
    String SOCKET_HOST = "localhost";
    String CLIENT_ERROR = "Systémuvá chyba na straně klienta.";
    String STR3_ANY_MESSAGE = "%s: %s";

    String MESSAGES_SEND_MENU = "MESSAGES SEND MENU";
    String MESSAGES_PRINT_TEXT = "MESSAGES PRINT TEXT";


    int SOCKET_PORT = 5001;


    BiPredicate<Integer, Integer> testIndex = (index, max) -> (index < max && index >= 0);
    Predicate<String> isStringFullAndNotNull = (str) -> ((str != null) && (!str.isBlank()));
    BiPredicate<MessageTransfer, String> doesThisTaskContain = (mt, receiveTask) -> (mt != null && mt.task().contains(receiveTask));
    Predicate<Object> isNotNull = (o) -> ((o != null));


    String MESSAGE_DLG2_WHAT_ROLE_label = "Role:";
    String MESSAGE_DLG2_WHAT_ROLE_text = "Jaká bude role ? ";


}
