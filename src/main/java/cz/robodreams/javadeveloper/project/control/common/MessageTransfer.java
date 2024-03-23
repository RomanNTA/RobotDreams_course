package cz.robodreams.javadeveloper.project.control.common;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record MessageTransfer(String task, String replyTask, String label, String remark,
                              List menu, int output, boolean loop) implements Serializable {
}
