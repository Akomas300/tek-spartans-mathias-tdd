package tek.tdd.api.models;

public enum EndPoints {
    TOKEN("/api/token"),
    GET_PRIMARY_ACCOUNT("/api/accounts/get-primary-account"),

    GET_ACCOUNT("/api/accounts/get-account"),
    USER_PROFILE("/api/user/profile"),
    CREATE_PRIMARY_ACCOUNT("/api/accounts/add-primary-account");

    private final String value;

    EndPoints(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
