package com.example.Project.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertDto {

    private long id;

    private String title;

    private String message;

    private boolean isRead;

    public AlertDto() {
    }

    public AlertDto(String title, String message, boolean isRead) {
        this.title = title;
        this.message = message;
        this.isRead = isRead;
    }
}
