package com.gcpcx.formvalidation.data.service.reponse;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormResponse extends BaseResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public FormResponse(Exception error, String errorMessage) {
        super(error, errorMessage);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
