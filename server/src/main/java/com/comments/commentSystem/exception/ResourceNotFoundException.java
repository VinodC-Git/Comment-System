package com.comments.commentSystem.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldContent;

    /*
    When comment id is not found, it will return 404 Error
     */
    public ResourceNotFoundException(Object fieldValue) {
        super(String.format("%d not found with %d : '%d'",  fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldContent = fieldValue;
    }

}
