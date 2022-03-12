package com.iedaas.notification.dto;

import java.util.UUID;

public class UserDTO {

    private UUID userUid;
    private String userName;

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
