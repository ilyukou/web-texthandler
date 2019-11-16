package model;

import model.text.Text;

public class Response {
    private ResponseStatus status;
    private Text response;

    public Response(ResponseStatus status, Text response) {
        this.status = status;
        this.response = response;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Text getResponse() {
        return response;
    }

    public void setResponse(Text response) {
        this.response = response;
    }
}
