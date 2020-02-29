package helpers.enums;

public enum StatusCodeEnums {
    OK ( 200 ),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    private int value;

    StatusCodeEnums(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
