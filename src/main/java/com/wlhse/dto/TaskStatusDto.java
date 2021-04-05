package com.wlhse.dto;

public class TaskStatusDto {
    private  int  tableId;
    private  String status;

    public TaskStatusDto(int tableId, String status) {
        this.tableId = tableId;
        this.status = status;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
