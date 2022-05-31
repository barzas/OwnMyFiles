package com.payload.request;

import javax.validation.constraints.NotBlank;

public class EncryptRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String path;

    @NotBlank
    private String type;

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

    public String getTypeOfEncrypt() {
        return type;
    }

    public void setTypeOfEncrypt(String typeOfEncrypt) {
        this.type = typeOfEncrypt;
    }
}
