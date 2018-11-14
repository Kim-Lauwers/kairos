package be.kairos.representation;

public class ActionR {
    private final long id;
    private final String content;

    public ActionR(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}