package cz.robodreams.javadeveloper.project.common;

import org.apache.commons.lang3.StringUtils;

public class Util implements UtilConst {

    public static Integer getRandomId() {
        int min = 100000000;
        int max = 999999999;
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

    public static Integer getRandomId(int min, int max) {

        return (int) ((Math.random() * (max + 1 - min)) + min);
    }


    public static String getRandomIdString(int len) {

        return String.format("%0" + len + "d", getRandomId());
    }

    public static String getRandomIdString(int min, int max) {
        int i = max;
        int len = 0;
        while (i > 1) {
            i = i / 10;
            len++;
        }
        return String.format("%0" + len + "d", (int) ((Math.random() * (max + 1 - min)) + min));
    }




    public String normalizeString(String string) {
        return StringUtils.stripAccents(string).toLowerCase();

    }



    public static void line() {
        System.out.println("+" + "-".repeat(90));
    }
    public static String getLine() {
        return "+" + "-".repeat(90);
    }


    /**
     *  barvičky pro konzoli v InteliJ
     *
     */

    public static final String colBlack(String string) {
        return BLACK_BOLD + string + ANSI_RESET;
    }
    public static final String colBlue(String string) {
        return BLUE_BOLD + string + ANSI_RESET;
    }
    public static final String colCyan(String string) {
        return CYAN_BOLD + string + ANSI_RESET;
    }
    public static final String colGreen(String string) {
        return GREEN_BOLD + string + ANSI_RESET;
    }
    public static final String colPurple(String string) {
        return PURPLE_BOLD + string + ANSI_RESET;
    }
    public static final String colRed(String string) {
        return RED_BOLD + string + ANSI_RESET;
    }
    public static final String colWhite(String string) {
        return WHITE_BOLD + string + ANSI_RESET;
    }
    public static final String colYellow(String string) { return YELLOW_BOLD + string + ANSI_RESET; }









}
