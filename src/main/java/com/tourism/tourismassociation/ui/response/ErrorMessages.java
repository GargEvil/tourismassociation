package com.tourism.tourismassociation.ui.response;


public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please re-do your submitting with all required fields filled"),
    LANDMARK_RECORD_ALREADY_EXISTS("Landmark with this name already exist"),
    MAIL_RECORD_ALREADY_EXISTS("User with this mail already exist"),
    LANDMARK_NO_RECORD_FOUND("Landmark with provided id is not found"),
    NO_RECORD_FOUND("Record not found");


    private String errorMessage;

    ErrorMessages(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }



}
