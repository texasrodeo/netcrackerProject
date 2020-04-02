package com.netcrackerTask.backend.business.payloads;

import javax.validation.constraints.NotBlank;

public class AddAccRequest {
    @NotBlank
    private String gameId;

    @NotBlank
    private String description;

    @NotBlank
    private String price;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
