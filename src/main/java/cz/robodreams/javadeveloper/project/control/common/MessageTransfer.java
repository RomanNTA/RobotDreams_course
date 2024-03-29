package cz.robodreams.javadeveloper.project.control.common;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record MessageTransfer(String task, String replyTask, String strInOut1, String strInOut2,
                              List<String> menu, int intResult, boolean loop) implements Serializable {
}
