package com.microservice.MailingService.resources;

public class NotificationFactory {

    public static Notification createNotification(String medium)
    {
        if(medium == null || medium.isEmpty())
            throw  new IllegalArgumentException();
        switch (medium)
        {
            case "SMS" : return new SMSNotification();
            case "EMAIL" : return new EmailNotification();
            default:throw  new IllegalArgumentException( "unknown medium is entered verify it once !! ");
        }
    }
}
