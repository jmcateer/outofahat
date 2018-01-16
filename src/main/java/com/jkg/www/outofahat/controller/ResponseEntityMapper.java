package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityMapper {

    public <T extends IResponseMessage> ResponseEntity<T> map(T message) {
        if (message.isSuccessful()) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    public <T extends IResponseMessage> ResponseEntity<T> mapWithHeaders(T message, HttpStatus successStatus, HttpHeaders httpHeaders) {
        if (message.isSuccessful()) {
            return ResponseEntity.status(successStatus).headers(httpHeaders).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}
