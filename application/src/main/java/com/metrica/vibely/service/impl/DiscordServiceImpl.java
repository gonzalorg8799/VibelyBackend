package com.metrica.vibely.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.metrica.vibely.service.DiscordService;

/**
 * <h1>Discord Service Implementation</h1>
 * 
 * @since 2023-11-21
 * @version 1.0
 * @author Alex
 */
@Service
public class DiscordServiceImpl implements DiscordService {
    
    // <<-CONSTANTS->>
    private static final String BASE_URL = "https://canary.discord.com/api/v10";
    private static final String USER_ENDPOINT = "/users/%s";
    private static final String TOKEN = "MTE3NzI1MDI1NDQ1NDc5NjM4OQ.Gba32-.YYyqyGOwIIiVHY79s2kwaQyThIkZCmRKGs6TGA";

    // <<-FIELD->>
    private RestTemplate restTemplate;

    // <<-CONSTRUCTOR->>
    public DiscordServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    // <<-METHODS->>
    @Override
    public String authenticate(String username, String password) {
        String url = BASE_URL + USER_ENDPOINT;
        String info = UriComponentsBuilder.fromHttpUrl(String.format(url, username)).toString();
        return null;
    }

    @Override
    public String getUserInfo(String username) {
        // TODO Auto-generated method stub
        return null;
    }
    
}