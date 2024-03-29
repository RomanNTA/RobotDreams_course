package cz.robodreams.javadeveloper.project.exceptions;

import lombok.Getter;

public class LibraryRuntimeExceptionBadIndex extends RuntimeException{

    @Getter
    private Integer id;

    private String message;

    public LibraryRuntimeExceptionBadIndex(String message, Integer id) {
        super(String.format(message));
        this.id = id;
        this.message = message;
    }
    public LibraryRuntimeExceptionBadIndex(Integer id) {
        super(String.format(String.format("Chybný index pole: %d", id)));
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
