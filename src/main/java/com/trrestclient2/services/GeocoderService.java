package com.trrestclient2.services;

import com.trrestclient2.entities.Site;
import com.trrestclient2.json.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GeocoderService {

    private static final Logger logger = LoggerFactory.getLogger(GeocoderService.class);
    private static final String BASE = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String KEY = "AIzaSyAghmZ2jVKWBr1y9MVND6WjvyxiYQg1bdQ";
    private RestTemplate restTemplate;
    private GeocoderService SiteRepository;
    private Site city;
    private Object state;

    @Autowired
    public GeocoderService(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    private String encodeString(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public Site getLatLng(String...address) {
        String encodedAddress = Stream.of(address)
                .map(this::encodeString)
                .collect(Collectors.joining(","));
        String url = String.format("%s?address=%s", BASE, encodedAddress);
        Response response = restTemplate.getForObject(url, Response.class);
        logger.info(String.format("Lat/Lng for %s: %s",
                response.getFormattedAddress(), response.getLocation()));
        return new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());
    }
}
