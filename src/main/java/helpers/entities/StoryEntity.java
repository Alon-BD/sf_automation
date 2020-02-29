package helpers.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import infra.general_utils.DateParser;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryEntity implements Comparable<StoryEntity> {

    @JsonProperty("story")
    public String story;

    @JsonProperty("Timestamp")
    public String timeStamp;

    @Override
    public int compareTo(StoryEntity friend) {
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
        int res;

        if (this.timeStamp == null && friend.timeStamp == null)
            res = 0;

        else if (this.timeStamp == null)
            res = -1;

        else if (friend.timeStamp == null)
            res = 1;
        else {
            LocalDateTime friendTime = DateParser.parseDate(datePattern, friend.timeStamp);
            LocalDateTime thisTime = DateParser.parseDate(datePattern, timeStamp);
            res = thisTime.compareTo(friendTime);
        }
        return res;
    }
}
