package com.payload.request;

import javax.validation.constraints.NotBlank;

public class DecryptRequest {
    
    @NotBlank
    private String username;

    @NotBlank
    private String path;

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

    
}
