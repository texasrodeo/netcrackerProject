package com.netcrackerTask.backend.business.payloads;

import java.util.List;

/**
 * This is a JWT response.
 */
public class  JwtResponse {
    /**
     * Token.
     */
    private String token;

    /**
     * Token type.
     */
    private String type = "Bearer";

    /**
     * User id.
     */
    private Long id;

    /**
     * User name.
     */
    private String username;

    /**
     * User email.
     */
    private String email;

    /**
     * User roles.
     */
    private List<String> roles;

    /**
     * Constructor.
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * @return token
     */
    public String getAccessToken() {
        return token;
    }

    /**
     *
     * @param accessToken salary to set
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * @return token type
     */
    public String getTokenType() {
        return type;
    }

    /**
     *
     * @param tokenType salary to set
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * @return user id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id salary to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email salary to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username salary to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return user roles
     */
    public List<String> getRoles() {
        return roles;
    }
}