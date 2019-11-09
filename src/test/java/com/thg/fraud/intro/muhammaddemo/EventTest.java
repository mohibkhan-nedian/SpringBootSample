package com.thg.fraud.intro.muhammaddemo;

import com.thg.fraud.intro.muhammaddemo.json.Event;
import com.thg.fraud.intro.muhammaddemo.json.EventResponse;
import com.thg.fraud.intro.muhammaddemo.json.NewEventRequest;
import com.thg.fraud.intro.muhammaddemo.model.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;
import static org.springframework.test.web.client.ExpectedCount.manyTimes;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private ResponseEntity responseEntity;

    private String newEventJson;

    private MockRestServiceServer mockRestServiceServer;


    @Before
    public void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void given_a_valid_event_make_request_and_return() throws Exception {
        givenValidEvent();
        thenIMockTestService();
        whenICallToCreateEvent();
        thenTheEventIsCreated();
    }

    private void givenValidEvent(){
         newEventJson = "{" +
                "  \"identifier\": \"testevent\"," +
                "  \"timestamp\": 1," +
                "  \"products\": [" +
                "    {" +
                "      \"identifier\": 1," +
                "      \"title\": \"My Test Product 1\"," +
                "      \"quantity\": 9" +
                "    }," +
                "    {" +
                "      \"identifier\": 2," +
                "      \"title\": \"My Test Product 2\"," +
                "      \"quantity\": 14" +
                "    }," +
                "    {" +
                "      \"identifier\": 3," +
                "      \"title\": \"My Test Product 3\"," +
                "      \"quantity\": 71" +
                "    }," +
                "    {" +
                "      \"identifier\": 4," +
                "      \"title\": \"My Test Product 4\"," +
                "      \"quantity\": 56" +
                "    }" +
                "  ]," +
                "  \"paymentDetails\": {" +
                "    \"type\": \"CARD\"," +
                "    \"provider\": \"VISA\"," +
                "    \"amount\": 99.99," +
                "    \"currency\": \"GBP\"" +
                "  }" +
                "}";
    }

    private void thenIMockTestService() {
        mockRestServiceServer.expect(manyTimes(), requestTo("http://mytestservice/products/1"))
                .andExpect(method(GET))
                .andRespond(withSuccess("{\"identifier\" : 1, \"inStock\": true}", MediaType.APPLICATION_JSON));

        mockRestServiceServer.expect(manyTimes(), requestTo("http://mytestservice/products/2"))
                .andExpect(method(GET))
                .andRespond(withSuccess("{\"identifier\" : 2, \"inStock\": true}", MediaType.APPLICATION_JSON));

        mockRestServiceServer.expect(manyTimes(), requestTo("http://mytestservice/products/3"))
                .andExpect(method(GET))
                .andRespond(withSuccess("{\"identifier\" : 3, \"inStock\": false}", MediaType.APPLICATION_JSON));

        mockRestServiceServer.expect(manyTimes(), requestTo("http://mytestservice/products/4"))
                .andExpect(method(GET))
                .andRespond(withSuccess("{\"identifier\" : 4, \"inStock\": false}", MediaType.APPLICATION_JSON));
    }

    private void whenICallToCreateEvent() {
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        responseEntity = testRestTemplate.exchange(
                "/events",
                HttpMethod.POST,
                new HttpEntity<>(newEventJson, headers),
                String.class
        );
    }

    private void thenTheEventIsCreated() throws IOException {
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("{1=true, 2=true, 3=false, 4=false}", responseEntity.getBody());
    }

}
