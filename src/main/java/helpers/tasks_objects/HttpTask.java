package helpers.tasks_objects;

import com.jayway.restassured.response.Response;
import helpers.entities.StoryEntity;
import infra.general_utils.DateParser;
import infra.rest.RestAssuredWrapper;
import infra.rest.RestUtils;

import java.time.LocalDateTime;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ScheduledFuture;

public abstract class HttpTask extends Task implements Comparable<HttpTask> {

    protected String url = null;
    protected RestAssuredWrapper rest = new RestAssuredWrapper();
    private ScheduledFuture<Response> response = null;

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
            res = response.get();
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
        if (response == null)
            return false;

        return response.isDone();
    }

    public boolean isCanceled() {
        if (response == null)
            return false;

        return response.isCancelled();
    }

    public boolean cancel(boolean flag) {
        return response.cancel(flag);
    }



    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setFutureResponse(ScheduledFuture<Response> res) {
        response = res;
    }

    @Override
    public int compareTo(HttpTask friend) {
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";

        if(isCanceled())
            return 0;

        if(friend.isCanceled())
            return 1;

        StoryEntity thisEntity = RestUtils.convertJsonToObject(getResponse().getBody().asString(), StoryEntity.class);
        LocalDateTime thisTimeStamp = DateParser.parseDate(datePattern, thisEntity.timeStamp);

        StoryEntity friendEntity = RestUtils.convertJsonToObject(friend.getResponse().getBody().asString(), StoryEntity.class);
        LocalDateTime friendTimestamp = DateParser.parseDate(datePattern, friendEntity.timeStamp);

        if (thisTimeStamp == null && friendTimestamp == null) {
            return 0;
        }

        if (thisTimeStamp == null) {
            return -1;
        }

        if (friendTimestamp == null) {
            return 1;
        }

        return thisTimeStamp.compareTo(friendTimestamp);
    }




}
