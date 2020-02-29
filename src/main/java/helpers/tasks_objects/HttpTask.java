package helpers.tasks_objects;

import com.jayway.restassured.response.Response;
import helpers.entities.StoryEntity;
import infra.general_utils.DateParser;
import infra.rest.RestAssuredWrapper;
import infra.rest.RestUtils;

import java.time.LocalDateTime;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ScheduledFuture;

public abstract class HttpTask extends Task {

    protected String url = null;
    protected RestAssuredWrapper rest = new RestAssuredWrapper();

    public HttpTask() {
    }

    public HttpTask(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("")
                .append("task : ")
                .append(id)
                .append("\nisDone : ")
                .append(isDone())
                .append("\nurl : ")
                .append(getUrl())
                .append("\n");

        return output.toString();
    }

    public Response getResponse() {
        Response res = null;
        try {
            res = (Response) future.get();
        } catch (CancellationException e) {
            System.out.println(toString() + "has reached timeout\n");

        } catch (Exception e) {
            StringBuilder output = new StringBuilder(toString())
                    .append("Has thrown ")
                    .append(e.getClass().getSimpleName())
                    .append(e.getLocalizedMessage())
                    .append("\n");

            System.out.println(output);
        }
        return res;
    }

    public boolean isDone() {
        if (future == null)
            return false;

        return future.isDone();
    }

    public boolean isCanceled() {
        if (future == null)
            return false;

        return future.isCancelled();
    }

    public boolean cancel(boolean flag) {
        return future.cancel(flag);
    }



    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setFutureResponse(ScheduledFuture<Response> res) {
        future = res;
    }
}
