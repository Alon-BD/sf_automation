package infra.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class RestUtils {

    public static <T> T convertJsonToObject(String jsonString, Class<T> clazz) {
        T res = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static String convertObjectToString(Object objToBeConvert) {
        String res = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            res = mapper.writeValueAsString(objToBeConvert);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }


}
