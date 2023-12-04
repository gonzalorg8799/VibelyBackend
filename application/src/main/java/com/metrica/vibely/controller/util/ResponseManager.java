package com.metrica.vibely.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.dto.PostDTO;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.model.response.create.CreateAdminResponse;
import com.metrica.vibely.model.response.create.CreateChatResponse;
import com.metrica.vibely.model.response.create.CreateMessageResponse;
import com.metrica.vibely.model.response.create.CreatePostResponse;
import com.metrica.vibely.model.response.create.CreateUserResponse;
import com.metrica.vibely.model.response.get.BasicInfoResponse;
import com.metrica.vibely.model.response.get.GetChatResponse;
import com.metrica.vibely.model.response.get.GetFriendNetworkResponse;
import com.metrica.vibely.model.response.get.GetMessageResponse;
import com.metrica.vibely.model.response.get.GetPostResponse;
import com.metrica.vibely.model.response.update.UpdateAdminResponse;
import com.metrica.vibely.model.response.update.UpdateChatResponse;
import com.metrica.vibely.model.response.update.UpdateLikedByPostResponse;
import com.metrica.vibely.model.response.update.UpdateMessageResponse;
import com.metrica.vibely.model.response.update.UpdatePostResponse;
import com.metrica.vibely.model.response.update.UpdateUserResponse;

@Component
public class ResponseManager {

	//	<<--GET RESPONSE-->> 
    public ResponseEntity<BasicInfoResponse> generateGetResponse(UserDTO userDto) {
        BasicInfoResponse basicResponse = new BasicInfoResponse();
        return ResponseEntity.ok().body(basicResponse.generateResponse(userDto));
    }
    
    public ResponseEntity<GetPostResponse> generateGetResponse(PostDTO postDto) {
    	GetPostResponse postResponse = new GetPostResponse();
        return ResponseEntity.ok().body(postResponse.generateResponse(postDto));
    }
    
    public ResponseEntity<GetChatResponse> generateGetResponse(ChatDTO chatDto) {
    	GetChatResponse chatResponse = new GetChatResponse();
    	return ResponseEntity.ok().body(chatResponse.generateResponse(chatDto));
    }
    
    public ResponseEntity<GetMessageResponse> generateGetResponse(MessageDTO messageDto) {
    	GetMessageResponse messageResponse = new GetMessageResponse();
    	return ResponseEntity.ok().body(messageResponse.generateResponse(messageDto));
    }
    
    public ResponseEntity<GetFriendNetworkResponse> generateGetNetworkResponse(UserDTO userDto){
    	GetFriendNetworkResponse friendNetworkResponse = new GetFriendNetworkResponse();
    	return ResponseEntity.ok().body(friendNetworkResponse.generateResponse(userDto));
    }
    
    //	<<--CREATE RESPONSE-->>   
    public ResponseEntity<CreateUserResponse> generateCreateResponse(UserDTO userDto) {
    	CreateUserResponse userResponse = new CreateUserResponse();
    	return ResponseEntity.status(HttpStatus.CREATED).body(userResponse.generateResponse(userDto));
    }
    
    public ResponseEntity<CreateAdminResponse> generateCreateResponse(AdminDTO adminDto) {
    	CreateAdminResponse adminResponse = new CreateAdminResponse();
    	return ResponseEntity.status(HttpStatus.CREATED).body(adminResponse.generateResponse(adminDto));
    }
    
    public ResponseEntity<CreatePostResponse> generateCreateResponse(PostDTO postDto) {
    	CreatePostResponse postResponse = new CreatePostResponse();
    	return ResponseEntity.status(HttpStatus.CREATED).body(postResponse.generateResponse(postDto));
    }
    
    public ResponseEntity<CreateChatResponse> generateCreateResponse(ChatDTO chatDto) {
    	CreateChatResponse chatResponse = new CreateChatResponse();
    	return ResponseEntity.status(HttpStatus.CREATED).body(chatResponse.generateResponse(chatDto));
    }
    
    public ResponseEntity<CreateMessageResponse> generateCreateResponse(MessageDTO messageDto) {
    	CreateMessageResponse messageResponse = new CreateMessageResponse();
    	return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse.generateResponse(messageDto));
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
    
    public ResponseEntity<UpdatePostResponse> generateUpdateResponse(PostDTO postDto) {
    	UpdatePostResponse postResponse = new UpdatePostResponse();
    	return ResponseEntity.ok().body(postResponse.generateResponse(postDto));
    }
    
    public ResponseEntity<UpdateChatResponse> generateUpdateResponse(ChatDTO chatDto) {
    	UpdateChatResponse chatResponse = new UpdateChatResponse();
    	return ResponseEntity.ok().body(chatResponse.generateResponse(chatDto));
    }
    
    public ResponseEntity<UpdateMessageResponse> generateUpdateResponse(MessageDTO messageDto) {
    	UpdateMessageResponse messageResponse = new UpdateMessageResponse();
    	return ResponseEntity.ok().body(messageResponse.generateResponse(messageDto));
    }
    /**
     * Generates a ResponseEntity for updating the 'likedBy' property of a Post.
     * @param postDTO The PostDTO containing the updated 'likedBy' information.
     * @return ResponseEntity with the updated 'likedBy' response.
     */
    public ResponseEntity<UpdateLikedByPostResponse> generateLikedByUpdateResponse(PostDTO postDTO) {
    	UpdateLikedByPostResponse postResponse = new UpdateLikedByPostResponse();
    	return ResponseEntity.ok().body(postResponse.generateResponse(postDTO));
    }
}
