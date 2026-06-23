package com.empresa.empresa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="postulante") 
public interface PostulanteFeignClient {

    @GetMapping("/api/v1/postulantes/{id}")
    Object getPostulanteById(@PathVariable("id") Long id); 
    
   
}