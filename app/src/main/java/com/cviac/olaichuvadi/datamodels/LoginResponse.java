package com.cviac.olaichuvadi.datamodels;

/**
 * Created by User on 1/12/2017.
 */
public class LoginResponse {

    private String success;
    private String token;

    private ErrorInfo error;

    public LoginResponse() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }
}
