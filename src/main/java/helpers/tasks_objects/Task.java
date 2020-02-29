package helpers.tasks_objects;

import com.jayway.restassured.response.Response;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

public abstract class Task<V> implements Callable<V> {
    private static int numInstances;

    protected ScheduledFuture<V> future = null;
    protected int id;

    public Task() {
        id = increment();
    }

    private static synchronized int increment() {
        return ++numInstances;
    }
}
