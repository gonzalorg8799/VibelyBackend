package com.metrica.vibely.model.response.update;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;

public class UpdateSavedByPostResponse {

		// <<-FIELDS->>
		private UUID 	postId;
	    private Integer timesSaved;
	    
	    // <<-CONSTRUCTOR->>
	    public UpdateSavedByPostResponse() {
	    }
	    
	    // <<-METHODS->>
	    public UpdateSavedByPostResponse generateResponse(PostDTO postDTO) {
	    	UpdateSavedByPostResponse savedByPostResponse = new UpdateSavedByPostResponse();

	    	savedByPostResponse.setPostId    (postDTO.getPostId());
	    	savedByPostResponse.setTimesSaved(postDTO.getTimesSaved());
	       

	        return savedByPostResponse;
	    }
	    
	    // <<-GETTERS & SETTERS->>
	    public UUID getPostId() {
	        return this.postId;
	    }

	    public void setPostId(final UUID id) {
	        this.postId = id;
	    }

		public Integer getTimesSaved() {
			return timesSaved;
		}

		public void setTimesSaved(Integer timesSaved) {
			this.timesSaved = timesSaved;
		}

		
	    
}
