package model;

public class Response {
    private ResponseStatus status;
    private Text text;

    public Response(ResponseStatus status, Text text) {
        this.status = status;
        this.text = text;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
