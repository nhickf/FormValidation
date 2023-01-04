package com.gcpcx.formvalidation.data.model;

import com.gcpcx.formvalidation.utils.EValidation;

public class Validation {
    private String message;
    private EValidation code;

    public Validation() {
        this.message = "";
        this.code = EValidation.NONE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EValidation getCode() {
        return code;
    }

    public void setCode(EValidation code) {
        this.code = code;
    }
}
