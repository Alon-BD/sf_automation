package infra.general_utils;

public class StatusCodeHandler {

    public static void handle(int status) throws Exception {
        switch (status){
            case 404:
                throw new RuntimeException("Not Found - server has not found anything matching the Request-URI");
            case 500:
                throw new RuntimeException("Internal Server Error - server encountered an unexpected condition which prevented it from fulfilling the request");
            case 503:
                throw new RuntimeException("Service Unavailable - The server is currently unable to handle the request due to a temporary overloading or maintenance of the server");

        }
    }
}
