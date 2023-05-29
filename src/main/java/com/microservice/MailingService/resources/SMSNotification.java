package com.microservice.MailingService.resources;

public class SMSNotification implements Notification{
    @Override
    public String notifyUser()
    {
        String response="SMS NOTIFICATION IS SENT SUCCESSFULLY !!";
        return response;
    }
}
