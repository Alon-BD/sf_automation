package api.tasks_objects;

import java.util.concurrent.Callable;

public abstract class Task<T> implements Callable<T> {

    private static int numInstances;
    protected int id;

    public Task(){
        id = increment();
    }

    private static synchronized int increment() {
        return ++numInstances;
    }

}
