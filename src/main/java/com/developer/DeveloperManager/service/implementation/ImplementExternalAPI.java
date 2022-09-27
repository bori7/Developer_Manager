package com.developer.DeveloperManager.service.implementation;

import com.developer.DeveloperManager.dto.PostRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImplementExternalAPI {

    private final RestTemplate restTemplate;
@Value("${countries-Api}")
private String countries;
    @Value("${countriesByName-Api}")
    private String countriesByNameUrl;
    public Object getCountries(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        Response response = null;
        try{
            //countriesByNameUrl = countriesByNameUrl.replace("{name}",name);
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url(countries)
                    .method("GET",null)
                    .build();
            response = client.newCall(request).execute();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body().string());
            return rootNode;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
    public Object getCountriesByName(String name){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        Response response = null;
        Object result = null;
        try{
            countriesByNameUrl = countriesByNameUrl.replace("{name}",name);
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url(countriesByNameUrl)
                    .method("GET",null)
                    .build();
            response = client.newCall(request).execute();
            /*ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body().string());*/
            result = response.body().string();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;

    }
    public Object getCountriesByName(PostRequest postRequest){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        Response response = null;
        Object result = null;
        try{
            countriesByNameUrl = countriesByNameUrl.replace("{name}",postRequest.getName());
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url(countriesByNameUrl)
                    .method("GET",null)
                    .build();
            response = client.newCall(request).execute();
            /*ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body().string());*/
            result = response.body().string();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;

    }


}
