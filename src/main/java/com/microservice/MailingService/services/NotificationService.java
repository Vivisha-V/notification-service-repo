package com.microservice.MailingService.services;

import com.microservice.MailingService.models.User;
import com.microservice.MailingService.resources.NotificationFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService
{
    public  List<String>  mailService(List<User> userList)
    {
        String medium="";
        List<String> allResponses=new ArrayList<>();
        List<String> allcontacts= userList.stream().map(i->i.getContact()).toList();
        for (String contact:allcontacts) {
            if(contact.contains("@") && contact.contains("gmail") && contact.contains("."))
            {
                medium="EMAIL";
            }
            else
            {
                medium="SMS";
            }
           String Response= NotificationFactory.createNotification(medium).notifyUser();
            allResponses.add(Response);
        }

        return allResponses;

    }
}
