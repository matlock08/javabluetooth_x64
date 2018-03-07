package com.roni.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties {
    private int connectTimeout;
    private int readTimeout;
    
    private String registroUrl;
    private String registroUser;
    private String registroPassword;

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }


    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    
    public String getRegistroUrl() {
        return registroUrl;
    }

    public void setRegistroUrl(String registroUrl) {
        this.registroUrl = registroUrl;
    }

    public String getRegistroUser() {
        return registroUser;
    }

    public void setRegistroUser(String registroUser) {
        this.registroUser = registroUser;
    }

    public String getRegistroPassword() {
        return registroPassword;
    }

    public void setRegistroPassword(String registroPassword) {
        this.registroPassword = registroPassword;
    }

}