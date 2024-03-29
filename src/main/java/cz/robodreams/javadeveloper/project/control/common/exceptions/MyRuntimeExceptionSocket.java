package cz.robodreams.javadeveloper.project.control.common.exceptions;

import lombok.Getter;

@Getter
public class MyRuntimeExceptionSocket extends RuntimeException {

    private Integer id;
    private String host;
    private Integer port;
    private String message1;
    private String stack;

    public MyRuntimeExceptionSocket(String host, Integer port, String message1, String stack) {
        super(String.format(message1));
        this.host = host;
        this.port = port;
        this.message1 = message1;
        this.stack = stack;
    }

    @Override
    public String getMessage() {
        return String.format("Host: %s Port: %d : %s\n%s", host, port, message1, stack);
    }
}
