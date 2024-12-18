package JuanJose.ForoHub.model;

public enum TopicStatus {
    UNANSWERED("No respondido", "UnansWered"),
    ANSWERED("Respondido", "Anwered"),
    RESOLVED("Resuelto", "Resolve");

    private final String spanish;
    private final String english;

    TopicStatus(String spanish, String english) {
        this.spanish = spanish;
        this.english = english;
    }

    public String getSpanish() {
        return spanish;
    }

    public String getEnglish() {
        return english;
    }

    public static TopicStatus fromString(String value) {
        for (TopicStatus status : TopicStatus.values()) {
            if (status.spanish.equalsIgnoreCase(value) || status.english.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid TopicType: " + value);
    }
}
