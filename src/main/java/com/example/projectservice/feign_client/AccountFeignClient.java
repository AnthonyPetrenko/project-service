package com.example.projectservice.feign_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account", url = "http://localhost:8080")
public interface AccountFeignClient {

    @PostMapping("/remove-all-project-id")
    ResponseEntity<String> removeAllProjectId(@RequestParam("project-id") long projectId);

}
