package api.http_tasks;

import api.tasks_objects.HttpTask;
import com.jayway.restassured.response.Response;
import infra.general_utils.StatusCodeHandler;

import static helpers.enums.RestAssuredEnums.APPLICATION_JSON;
import static helpers.enums.RestAssuredEnums.CONTENT_TYPE;

public class HttpGet extends HttpTask {

    public HttpGet(String url) {
        super(url);
    }

    @Override
    public Response call() throws Exception {
        Response res = rest
                .ra()
                .header(CONTENT_TYPE.toString(), APPLICATION_JSON.toString())
                .get(url);

        StatusCodeHandler.handle(res.getStatusCode());
        return res;
    }
}



