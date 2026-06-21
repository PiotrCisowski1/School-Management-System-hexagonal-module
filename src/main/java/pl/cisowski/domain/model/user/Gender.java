package pl.cisowski.domain.model.user;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String value;

    Gender(String value) {
        if(value != null)
            this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }
}
