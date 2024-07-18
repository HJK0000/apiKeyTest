package com.example.view.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
public class MainController {

	@Value("${google.maps.api.key}")
	private String googleMapsApiKey;
	
	private final RestTemplate restTemplate;
	
	
	
	public MainController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}



	@GetMapping("/")
	public String getMap() {
		return "index";
		
	}
	
	@GetMapping("/proxy/maps-api")
	public ResponseEntity<String> proxyMapsApi(@RequestParam Map<String, String> params) {
        String url = "https://maps.googleapis.com/maps/api/js?key=" + googleMapsApiKey;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            url += "&" + entry.getKey() + "=" + entry.getValue();
        }
        return restTemplate.getForEntity(url, String.class);
    }
	
}
