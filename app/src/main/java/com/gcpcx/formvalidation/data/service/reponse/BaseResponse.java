package com.gcpcx.formvalidation.data.service.reponse;

public class BaseResponse {
    private Exception error;
    private String errorMessage;

    public BaseResponse(Exception error, String errorMessage) {
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
