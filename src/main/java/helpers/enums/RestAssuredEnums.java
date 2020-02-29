package helpers.enums;

public enum RestAssuredEnums {

    CONTENT_TYPE ( "Content-Type" ),
    APPLICATION_JSON("application/json"),
    TEXT_HTML("text/html; charset=utf-8");

    private String type;

    RestAssuredEnums(String inType) {
        type = inType;
    }

    @Override
    public String toString() {
        return type;
    }
}