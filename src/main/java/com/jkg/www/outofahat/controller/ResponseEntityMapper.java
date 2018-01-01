package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityMapper {

    public <T extends IResponseMessage> ResponseEntity<T> mapWithReqeustId(T message) {
        if(message.isSuccessful()) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}
