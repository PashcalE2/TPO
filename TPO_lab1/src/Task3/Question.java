package Task3;

public class Question {
    private final String topic;

    public Question(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return String.format("%s", topic);
    }
}
