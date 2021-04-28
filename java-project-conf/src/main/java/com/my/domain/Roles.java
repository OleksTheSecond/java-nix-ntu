package com.my.domain;

public enum Roles {
    MODERATOR(1),
    SPEAKER(2),
    LISTENER(3);

    private int roleId;

    Roles(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
