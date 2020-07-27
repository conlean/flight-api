package com.conlean.challenge.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(com.conlean.challenge.exception.ResourceNotFoundException.class);

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        logger.error(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }


    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
