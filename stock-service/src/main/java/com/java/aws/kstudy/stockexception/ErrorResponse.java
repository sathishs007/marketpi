package com.java.aws.kstudy.stockexception;

import java.util.List;

public class ErrorResponse {
	
	    public ErrorResponse(String message, List<String> details) {
	        super();
	        this.setMessage(message);
	        this.setDetails(details);
	    }
	    public List<String> getDetails() {
			return details;
		}

		public void setDetails(List<String> details) {
			this.details = details;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		//General error message about nature of error
	    private String message;
	 
	    //Specific errors in API request processing
	    private List<String> details;
	 
	    //Getter and setters
	   
	}

