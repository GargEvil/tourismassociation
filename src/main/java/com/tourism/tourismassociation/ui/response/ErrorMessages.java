package com.tourism.tourismassociation.ui.response;


public enum ErrorMessages {

    MISSING_REQUIRED_FIELD("Missing required field. Please re-do your submitting with all required fields filled"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    LANDMARK_RECORD_ALREADY_EXISTS("Landmark with this name already exist"),
    MAIL_RECORD_ALREADY_EXISTS("User with this mail already exist"),
    INTERNAL_SERVER_ERROR("Internal server error, please contact our technical support"),
    USER_NO_RECORD_FOUND("User with provided id is not found"),
    LANDMARK_NO_RECORD_FOUND("Landmark with provided id is not found"),
    NO_RECORD_FOUND("Record not found"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record");

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
