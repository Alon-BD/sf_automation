package helpers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyEntity {

    @JsonProperty("success")
    public String success;

    @JsonProperty("error")
    public String error;




}
