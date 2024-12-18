package JuanJose.ForoHub.model;
public enum TopicType {
    DUDA("Duda", "Question"),
    SUGERENCIA("Sugerencia", "Suggestion"),
    PROYECTO("Proyecto", "Project"),
    BUG("Bug", "Error"),
    QUEJA("Queja", "Complaint");

    private final String spanish;
    private final String english;

    TopicType(String spanish, String english) {
        this.spanish = spanish;
        this.english = english;
    }

    public String getSpanish() {
        return spanish;
    }

    public String getEnglish() {
        return english;
    }

    public static TopicType fromString(String value) {
        for (TopicType type : TopicType.values()) {
            if (type.spanish.equalsIgnoreCase(value) || type.english.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid TopicType: " + value);
    }
}
