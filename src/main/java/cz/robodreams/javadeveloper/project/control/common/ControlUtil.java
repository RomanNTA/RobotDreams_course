package cz.robodreams.javadeveloper.project.control.common;

public interface ControlUtil {

    String MESSAGES_TO_CLIENT = "MESSAGES TO CLIENT";
    String MESSAGES_TO_SERVER = "MESSAGES TO SERVER";
    String ENTRANCE_TO_ROOM = "ENTRANCE TO ROOM";
    String EXIT_ROOM = "EXIT ROOM";
    String SEND_NAME = "SEND NAME";
    String CONNECT = "CONNECT";

    String MESSAGE_PATTERN = "%s: %s";
    String EXIT = "EXIT";
    //    String SEPARATOR = ":";
//    String SEPARATOR_LINE = "||";
    String SOCKET_HOST = "localhost";

    String CLIENT_ERROR = "Systémuvá chyba na straně klienta.";
    String SERVER_ERROR = "Systémuvá chyba na straně serveru.";


    String STR3_ANY_MESSAGE = "<%s>%s> %s";
    String STR2_ENTRY_ROOM = "<%s>%s> vstoupil do pokoje.";
    String STR2_LEAVE_ROOM = "<%s>%s> odešel z pokoje.";

    String STR1_ALL_ROOMS =  "[%s]";

    String STR_BYE_BYE =  "Chat server Bubu přeje hezký den !";



    String STR_CHOICE_ROOM = String.format(ControlUtil.MESSAGE_PATTERN, ControlUtil.MESSAGES_TO_CLIENT,
            "Aktivní rooms, do kterých můžeš vstoupit, nebo můžeš vytvořit novou:");






//    int TASK = 0;
//    int ROOM = 1;
//    int USER = 2;
//    int MESSAGE = 3;

    int SOCKET_PORT = 5001;


}
