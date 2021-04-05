package com.wlhse.dto.inDto;

public class WeChatBindInfo {
    private int userId;
    private String permissionCode;

    public WeChatBindInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
