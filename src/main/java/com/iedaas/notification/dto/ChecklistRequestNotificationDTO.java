package com.iedaas.notification.dto;

import java.util.UUID;

public class ChecklistRequestNotificationDTO {

    private UUID checklistRequestUid;
    private String ChecklistRequestTitle;

    public UUID getChecklistRequestUid() {
        return checklistRequestUid;
    }

    public void setChecklistRequestUid(UUID checklistRequestUid) {
        this.checklistRequestUid = checklistRequestUid;
    }

    public String getChecklistRequestTitle() {
        return ChecklistRequestTitle;
    }

    public void setChecklistRequestTitle(String checklistRequestTitle) {
        ChecklistRequestTitle = checklistRequestTitle;
    }
}
