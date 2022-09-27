package com.developer.DeveloperManager.controller;

import com.developer.DeveloperManager.dto.PostRequest;
import com.developer.DeveloperManager.service.implementation.ImplementExternalAPI;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ExternalController {
    private final ImplementExternalAPI implementExternalAPI;
    @GetMapping(value="/getCountries", produces= MediaType.APPLICATION_XML_VALUE)
    public Object getAllCountries(){
        return ResponseEntity.ok(implementExternalAPI.getCountries()).getBody();
    }

    @GetMapping("/getCountriesByName/{name}")
    public Object getCountriesByName(@PathVariable("name") String name){
        return implementExternalAPI.getCountriesByName(name);
    }
    @PostMapping("/getCountriesByNamePost")
    public Object getCountriesByNamePost(@RequestBody PostRequest postRequest){
        return implementExternalAPI.getCountriesByName(postRequest);
    }

}
