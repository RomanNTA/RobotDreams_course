package cz.robodreams.javadeveloper.project.control.common;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.concurrent.*;
import java.util.function.Predicate;

public class ServiceThread<T> {

    protected Callable<T> task;
    private final ExecutorService executorService;
    protected Boolean isAllowedRun;

    public ServiceThread()    {

        this.executorService = Executors.newSingleThreadExecutor();
        isAllowedRun = executorService != null;

        if (!isAllowedRun) {
            String errorMessage = (ServerHandler.threadName + "V objektu '%s' newSingleThreadExecutor=null \n");
        }
    }

    protected T start() {

        T result;
        try {

            Future<T> future = executorService.submit(task);
            result = future.get();

            // zkusit změnit na ...
            //result = executorService.submit(task).get();

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
        return result;
    }

}
