package com.metrica.vibely.controller.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.model.response.get.BasicInfoResponse;

@Component
public class ResponseManager {

    public ResponseEntity<BasicInfoResponse> generateGetResponse(AdminDTO adminDTO) {
        BasicInfoResponse basicResponse = new BasicInfoResponse();
        return ResponseEntity.ok().body(basicResponse.generateResponse(adminDTO));
    }
    
}
