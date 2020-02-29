package api.http_tasks;

import api.tasks_objects.HttpTask;
import com.jayway.restassured.response.Response;

import static helpers.enums.RestAssuredEnums.APPLICATION_JSON;
import static helpers.enums.RestAssuredEnums.CONTENT_TYPE;

public class HttpPost extends HttpTask {

    private String url = null;
    private String userName = null;
    private String password = null;


    public HttpPost(String url) {
        this.url = url;
        this.userName= "alonb@playbuzz.com";
        this.password = "25802580";
    }

    @Override
    public Response call() throws Exception {
        return rest
                .ra()
                .header(CONTENT_TYPE.toString(),  APPLICATION_JSON.toString())
                .header("email", userName)
                .header("password", password)
                .header("loginType", "Email")
                .post(url);
    }
}
