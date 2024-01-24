package cz.robodreams.javadeveloper.project.util;

public class TerminalColorConst implements ITerminal {

    /**
     *  barvičky pro konzoli v InteliJ
     *
     */

    public String colBlack(String string) {
        return BLACK_BOLD + string + ANSI_RESET;
    }
    public String colBlue(String string) {
        return BLUE_BOLD + string + ANSI_RESET;
    }
    public String colCyan(String string) {
        return CYAN_BOLD + string + ANSI_RESET;
    }
    public String colGreen(String string) {
        return GREEN_BOLD + string + ANSI_RESET;
    }
    public String colPurple(String string) {
        return PURPLE_BOLD + string + ANSI_RESET;
    }
    public String colRed(String string) {
        return RED_BOLD + string + ANSI_RESET;
    }
    public String colWhite(String string) {
        return WHITE_BOLD + string + ANSI_RESET;
    }
    public String colYellow(String string) {
        return YELLOW_BOLD + string + ANSI_RESET;
    }


}
