package automation_tests;

import helpers.tasks_objects.http_tasks.HttpGet;
import helpers.tasks_objects.HttpTask;
import com.jayway.restassured.response.Response;
import helpers.entities.StoryEntity;
import infra.rest.RestUtils;
import infra.task_executer.TaskExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Question2 extends BaseTest {

    private final String DATOERROR_URL = "http://ptsv2.com/t/datoerror/post";
    private final String DATOTEST1_URL = "http://ptsv2.com/t/datotest1/post";
    private final String DATOTEST2_URL = "http://ptsv2.com/t/datotest2/post";
    private final String DATOTEST3_URL = "http://ptsv2.com/t/datotest3/post";
    private final String DELAY_URL = "https://postman-echo.com/delay/10";
    private final int TERMINATION_TIMEOUT = 5;
    private final int MAX_THREADS = 5;

    private TaskExecutor taskExecuter = new TaskExecutor(MAX_THREADS);
    private StringBuilder output = new StringBuilder("");
    private List<HttpTask> tasks = new ArrayList();


    @BeforeMethod
    private void setUp() {
        initTasksArr();
    }

    @Test
    public void Question2() {
        executeAllTasks();
        taskExecuter.shutDown();
        taskExecuter.awaitTermination(TERMINATION_TIMEOUT +5, TimeUnit.SECONDS);

        Collections.sort(tasks);
        for (HttpTask task : tasks) {
            Response response = null;
            response = task.getResponse();

            if (response != null) {
                StoryEntity myEntity = RestUtils.convertJsonToObject(response.getBody().asString(), StoryEntity.class);
                output.append(myEntity.story + " (" + myEntity.timeStamp + ")\n");
            }
        }
        System.out.println(output);
    }

    private void initTasksArr() {
        tasks.add(new HttpGet(DATOTEST1_URL));
        tasks.add(new HttpGet(DATOERROR_URL));
        tasks.add(new HttpGet(DATOTEST3_URL));
        tasks.add(new HttpGet(DATOTEST2_URL));
        tasks.add(new HttpGet(DELAY_URL));
    }

    private void executeAllTasks() {
        for (HttpTask task : tasks) {
            task.setFutureResponse(taskExecuter.executeTask(task, TERMINATION_TIMEOUT));
        }
    }
}
