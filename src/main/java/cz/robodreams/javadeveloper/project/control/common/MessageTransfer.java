package cz.robodreams.javadeveloper.project.control.common;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record MessageTransfer(String task, String replyTask, String label, String remark, List menu, int output, boolean loop) implements Serializable {
//
//    public static final class Builder {
//
//        String task;
//        String replyTask;
//        String label;
//        String remark;
//        List menu;
//        Integer output;
//
//        public Builder(String task) {
//            this.task = task;
//        }
//
//        public Builder(String replyTask) {
//            this.replyTask = replyTask;
//        }
//
//        public Builder(String label) {
//            this.label = label;
//        }
//
//        public Builder(String remark) {
//            this.remark = remark;
//        }
//
//        public Builder(List menu) {
//            this.menu = menu;
//        }
//
//        public Builder(Integer output) {
//            this.output = output;
//        }
//
//        public MessageTransfer build() {
//            return new MessageTransfer(String task, String replyTask, String label, String remark, List menu, Integer output);
//        }
//
//    }

}
