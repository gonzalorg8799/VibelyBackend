package com.metrica.vibely.controller.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.model.response.create.CreateAdminResponse;
import com.metrica.vibely.model.response.create.CreateChatResponse;
import com.metrica.vibely.model.response.create.CreatePostResponse;
import com.metrica.vibely.model.response.create.CreateUserResponse;
import com.metrica.vibely.model.response.get.BasicInfoResponse;
import com.metrica.vibely.model.response.update.UpdateChatResponse;

@Component
public class ResponseManager {

//	<<--GET RESPONSE-->> 
    public ResponseEntity<BasicInfoResponse> generateGetResponse(UserDTO userDto) {
        BasicInfoResponse basicResponse = new BasicInfoResponse();
        return ResponseEntity.ok().body(basicResponse.generateResponse(userDto));
    }
    
    public ResponsEntity<GetPostResponse> generateGetResponse(PostDTO postDto) {
    	GetPostResponse postResponse = new GetPostResponse();
        return ResponseEntity.ok().body(postResponse.generateResponse(postDto));
    }
    
    public ResponsEntity<GetChatResponse> generateGetResponse(ChatDTO chatDto) {
    	GetChatResponse chatResponse = new GetChatResponse();
    	return ResponseEntity.ok().body(chatResponse.generateResponse(chatDto));
    }
    
    public ResponsEntity<GetMessageResponse> generateGetResponse(MessageDTO messageDto) {
    	GetMessageResponse messageResponse = new GetMessageResponse();
    	return ResponseEntity.ok().body(postResponse.generateResponse(messageDto));
    }
    
//	<<--CREATE RESPONSE-->>   
    public ResponseEntity<CreateUserResponse> generateCreateResponse(UserDTO userDto) {
    	CreateUserResponse userResponse = new CreateUserResponse();
    	return ResponseEntity.ok().body(userResponse.generateResponse(userDto));
    }
    
    public ResponseEntity<CreateAdminResponse> generateCreateResponse(AdminDTO adminDto) {
    	CreateAdminResponse adminResponse = new CreateAdminResponse();
    	return ResponseEntity.ok().body(adminResponse.generateResponse(adminDto));
    }
    
    public ResponseEntity<CreatePostResponse> generateCreateResponse(PostDTO postDto) {
    	CreatePostResponse postResponse = new CreatePostResponse();
    	return ResponseEntity.ok().body(postResponse.generateResponse(postDto));
    }
    
    public ResponseEntity<CreateChatResponse> generateCreateResponse(ChatDTO chatDto) {
    	CreateChatResponse chatResponse = new CreateChatResponse();
    	return ResponseEntity.ok().body(chatResponse.generateResponse(chatDto));
    }
    
    public ResponseEntity<CreateMessageResponse> generateCreateResponse(MessageDTO messageDto) {
    	CreateMessageResponse messageResponse = new CreateMessageResponse();
    	return ResponseEntity.ok().body(messageResponse.generateResponse(messageDto));
    }
    
//	<<--UPDATE RESPONSE-->>
    public ResponseEntity<UpdateUserResponse> generateUpdateResponse(UserDTO userDto) {
    	UpdateUserResponse userResponse = new UpdateUserResponse();
    	return ResponseEntity.ok().body(userResponse.generateResponse(userDto));
    }
    
    public ResponseEntity<UpdateAdminResponse> generateUpdateResponse(AdminDTO adminDto) {
    	UpdateAdminResponse adminResponse = new UpdateAdminResponse();
    	return ResponseEntity.ok().body(adminResponse.generateResponse(adminDto));
    }
    
    public ResponseEntity<UpdateChatResponse> generateUpdateResponse(ChatDTO chatDto) {
    	UpdateChatResponse chatResponse = new UpdateChatResponse();
    	return ResponseEntity.ok().body(chatResponse.generateResponse(chatDto));
    }
    
    public ResponseEntity<UpdateMessageResponse> generateUpdateResponse(MessageDTO messageDto) {
    	UpdateMessageResponse messageResponse = new UpdateMessageResponse();
    	return ResponseEntity.ok().body(messageResponse.generateResponse(messageDto));
    }
    
}
