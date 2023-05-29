package com.microservice.MailingService;

import com.microservice.MailingService.models.User;
import com.microservice.MailingService.resources.MailService;
import com.microservice.MailingService.services.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class MailingServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private NotificationService notificationService;

	@InjectMocks
	private MailService mailService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testMailService() throws Exception {

		// Mock response from the restTemplate
		List<User> mockUserList = new ArrayList<>();
		mockUserList.add(new User("test1","test1",20,"test@gmail.com"));
		mockUserList.add(new User("test2","test2",21,"1233456789"));

		ResponseEntity<List<User>> mockResponse = ResponseEntity.ok(mockUserList);
		when(restTemplate.exchange(
				eq("http://user-reg-service/User/allUser"),
				eq(HttpMethod.GET),
				isNull(),
				Mockito.<ParameterizedTypeReference<List<User>>>any()
		)).thenReturn(mockResponse);

		List<String> expectedNotificationResponse = new ArrayList<>();

		// Mock the response from notificationService
		List<String> contacts=mockResponse.getBody().stream().map(i->i.getContact()).toList();
		for (String contact:contacts) {
			if(contact.contains("@") && contact.contains("gmail") && contact.contains("."))
			{
				expectedNotificationResponse.add("EMAIL NOTIFICATION IS SENT SUCCESSFULLY !!") ;
			}
			else
			{
				expectedNotificationResponse.add("SMS NOTIFICATION IS SENT SUCCESSFULLY !!");
			}
		}

		when(notificationService.mailService(mockUserList)).thenReturn(expectedNotificationResponse);

		List<String> actualNotificationResponse = notificationService.mailService(mockUserList);

		// Assertions
		assertEquals(expectedNotificationResponse, actualNotificationResponse);
	}
	@Test
	void contextLoads() {
	}

}
