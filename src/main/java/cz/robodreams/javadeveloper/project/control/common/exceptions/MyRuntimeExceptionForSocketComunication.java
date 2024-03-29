package cz.robodreams.javadeveloper.project.control.common.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyRuntimeExceptionForSocketComunication extends RuntimeException {

    @Getter
    private String message;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");


    public MyRuntimeExceptionForSocketComunication(String message) {
        super(String.format("%s : Chyba: '%s' ", DATE_TIME_FORMATTER.format(LocalDateTime.now()), message));
        this.message = message;
    }


}
