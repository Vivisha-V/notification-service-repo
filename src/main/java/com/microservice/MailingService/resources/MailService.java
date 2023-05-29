package com.microservice.MailingService.resources;

import com.microservice.MailingService.models.User;
import com.microservice.MailingService.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/Mail")
public class MailService
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService service;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/sendNotification")
    public List<String> mailService()
    {
        ResponseEntity<List<User>> response = restTemplate.exchange("http://user-reg-service/User/allUser", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> userList = response.getBody();//list of users
        List<String> notificationResponse= service.mailService(userList);
        return notificationResponse;
    }
}
