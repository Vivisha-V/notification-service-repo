package com.microservice.MailingService.resources;

import java.util.List;

public class EmailNotification implements Notification{
    @Override
    public String notifyUser() {
        String response="EMAIL NOTIFICATION IS SENT SUCCESSFULLY !!";
        return response;

    }
}
