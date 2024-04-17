package Task3;

public enum Race {
    HUMAN("Человек"),
    HYPER_INTELLIGENT_ENTITY("Гиперразумное существо");

    private final String value;

    Race(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
