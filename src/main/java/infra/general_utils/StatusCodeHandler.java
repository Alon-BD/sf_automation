package infra.general_utils;

import infra.exceptions.InternalServerErrorException;
import infra.exceptions.NotFoundException;
import infra.exceptions.ServiceUnavailableException;

public class StatusCodeHandler {

    public static void handle(int status) throws Exception {
        switch (status){
            case 404:
                throw new NotFoundException("Server has not found anything matching the Request-URI");
            case 500:
                throw new InternalServerErrorException("Server encountered an unexpected condition which prevented it from fulfilling the request");
            case 503:
                throw new ServiceUnavailableException("The server is currently unable to handle the request due to a temporary overloading or maintenance of the server");
        }
    }
}
