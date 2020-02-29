package automation_tests;

import api.http_tasks.HttpGet;
import api.tasks_objects.HttpTask;
import com.jayway.restassured.response.Response;
import helpers.enums.StatusCodeEnums;
import infra.general_utils.Randomizer;
import infra.task_executer.TaskExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Question1 extends BaseTest {


    private final String DELAY_URL = "https://postman-echo.com/delay/";
    private final String STATUS_URL = "https://postman-echo.com/status/";
    private final int TERMINATION_TIMEOUT = 10;
    private final int MAX_API_CALLS = 30;
    private final int MIN_API_CALLS = 2;

    private int numTotalRequests;
    private int numDelayRequests;
    private int numStatusRequests;

    private TaskExecutor taskExecuter = new TaskExecutor(MAX_API_CALLS);
    private List<HttpTask> delayTasks = new ArrayList();
    private List<HttpTask> statusTasks = new ArrayList();

    @BeforeClass
    private void setUp() {
        initNumRequests();
        initDelayTasks();
        initStatusTasks();
    }

    @Test
    public void Question1() {
        executeAllTasks();
        taskExecuter.shutDown();

        for (HttpTask task : statusTasks) {
            Response response = null;
            response = task.getResponse();

           if (response != null) {
                String value = response.jsonPath().get("status").toString();
                System.out.println(task.toString() + "returned status value of " + value + "\n");
            }
        }

        for (HttpTask task : delayTasks) {
            Response response = null;
            response = task.getResponse();

            if (response != null) {
                String value = response.jsonPath().get("delay").toString();
                System.out.println(task.toString() + "returned status value of " + value + "\n");
            }
        }
    }

    private void initNumRequests() {
        numTotalRequests = Randomizer.getRandomNum(MIN_API_CALLS, MAX_API_CALLS);
        numDelayRequests = numTotalRequests / 2;
        numStatusRequests = numTotalRequests - numDelayRequests;
    }

    private void initDelayTasks() {
        for (int i = 0; i < numDelayRequests; i++) {
            int randomNum = Randomizer.getRandomNum(1, 10);
            delayTasks.add(new HttpGet(DELAY_URL + randomNum));
        }
    }

    private void initStatusTasks() {
        for (int i = 0; i < numStatusRequests; i++) {
            int index = Randomizer.getRandomNum(0, StatusCodeEnums.values().length - 1);
            StatusCodeEnums res = StatusCodeEnums.values()[index];
            statusTasks.add(new HttpGet(STATUS_URL + res.getValue()));
        }
    }

    private void executeDelayTasks() {
        for (HttpTask task : delayTasks) {
            task.setFutureResponse(taskExecuter.executeTask(task, TERMINATION_TIMEOUT));
        }
    }

    private void executeStatusTasks() {
        for (HttpTask task : statusTasks) {
            task.setFutureResponse(taskExecuter.executeTask(task, TERMINATION_TIMEOUT));
        }
    }

    private void executeAllTasks() {
        executeDelayTasks();
        executeStatusTasks();
    }
}
