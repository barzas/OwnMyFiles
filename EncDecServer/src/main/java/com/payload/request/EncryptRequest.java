package com.payload.request;

import javax.validation.constraints.NotBlank;

public class EncryptRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String path;

    @NotBlank
    private String enctype;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getEnctype() {
        return enctype;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }
  
}
