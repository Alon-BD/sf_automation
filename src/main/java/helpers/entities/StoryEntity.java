package helpers.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryEntity {

    @JsonProperty("story")
    public String story;

    @JsonProperty("Timestamp")
    public String timeStamp;
}
