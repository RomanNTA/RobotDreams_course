package cz.robodreams.javadeveloper.project.control.common;

import java.io.Serializable;
import java.util.List;

public record MessageTransfer(
        String task,
        String message,
        String label,
        String remark,
        List menu,
        Integer output

) implements Serializable {
}
