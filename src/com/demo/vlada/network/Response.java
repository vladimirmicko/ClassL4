package com.demo.vlada.network;

public class Response {
	private String result;
	private String imageUrl;
	
	public Response(Object result) {
		this.result = result.toString();
	}

	public Response(Object result, String imageUrl) {
		this.result = result.toString();
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
