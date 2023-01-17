package com.ninjaone.backendinterviewproject.controller.responses;

public class RestControllerErrorTemplate {
    private String issue;

    private Integer code;

    private String reason;


    public RestControllerErrorTemplate(String reason, Integer code, String issue) {
        this.reason = reason;
        this.code = code;
        this.issue = issue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
