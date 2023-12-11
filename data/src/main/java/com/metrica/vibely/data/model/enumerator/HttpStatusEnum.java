package com.metrica.vibely.data.model.enumerator;

public enum HttpStatusEnum {
	OK(200), INVALID_CREDENTIALS(401), BAD_REQUEST(400) ;
	
	private int statusCode;
	
	HttpStatusEnum(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatus(){
		return this.statusCode;
	}
}
