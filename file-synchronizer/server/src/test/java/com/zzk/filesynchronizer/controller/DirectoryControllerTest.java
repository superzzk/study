package com.zzk.filesynchronizer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DirectoryControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    void getFiles() {
        String uri = "/directories";
        String prefix = "http://localhost:" + port;
//        Map<String, String> params = new HashMap<>();
        final String url = UriComponentsBuilder.fromHttpUrl(prefix + uri)
                .queryParam("dir", "/Users/Kun/Downloads/new")
                .encode().toUriString();
//        params.put("dir", "/Users/Kun/Documents/Download/new/");
//        params.put("dir", "aaa");
        final String r = template.getForObject(url, String.class);
        System.out.println(r);
    }
}