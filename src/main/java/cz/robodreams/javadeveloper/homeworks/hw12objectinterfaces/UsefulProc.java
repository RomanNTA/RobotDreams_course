package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public class UsefulProc {

    public static Integer getRandomId() {
        int min = 100000000;
        int max = 900000000;
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static Integer getRandomId(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
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
        return String.format("%0" + len + "d", (int) ((Math.random() * (max - min)) + min));
    }

}
