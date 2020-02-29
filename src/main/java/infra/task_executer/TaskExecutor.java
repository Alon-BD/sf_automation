package infra.task_executer;

import java.util.concurrent.*;

public class TaskExecutor {

    private static int NUM_THREADS = 40;
    private static ScheduledExecutorService taskExecutor = null;
    private static TaskExecutor instance = null;

    private TaskExecutor() {
        taskExecutor = Executors.newScheduledThreadPool(NUM_THREADS);
    }

    public static TaskExecutor getInstance() {
        if (instance == null)
            instance = new TaskExecutor();

        return instance;
    }

    public <T> ScheduledFuture<T> executeTask(Callable<T> task, int timeout) {
        ScheduledFuture<T> future = taskExecutor.schedule(task, 0, TimeUnit.SECONDS);
        taskExecutor.schedule(() -> {
            if (!future.isDone()) {
                future.cancel(true);
            }
        }, timeout, TimeUnit.SECONDS);
        return future;
    }

    public void shutDown() {
        taskExecutor.shutdown();
    }

    public void awaitTermination(int waitingTime, TimeUnit unit) {
        try {
            taskExecutor.awaitTermination(waitingTime, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
