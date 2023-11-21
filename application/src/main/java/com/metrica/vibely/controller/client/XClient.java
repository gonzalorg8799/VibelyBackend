package com.metrica.vibely.controller.client;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @since 2023-11-20
 * @version 1.0
 * @author Alex
 */
public class XClient {
    
    // <<-CONSTANTS->>
    private static final String BASE_URL = "https://api.twitter.com/2";
    private static final String USER_INFO_URL = "/users/by/username/%s";
    private static final String FRIENDS_URL = "/friends/ids.json";

    // <<-FIELD->>
    private RestTemplate restTemplate;

    // <<-CONSTRUCTOR->>
    public XClient() {
        this.restTemplate = new RestTemplate();
    }

    // <<-METHODS->>
    public String dd() {
        String auth = UriComponentsBuilder.fromHttpUrl(BASE_URL).toString();
        
//        Object ss = this.restTemplate..getForObject(null, Object.class);
        
        return "";
    }

}
