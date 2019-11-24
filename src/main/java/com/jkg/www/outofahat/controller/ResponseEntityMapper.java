package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseEntityMapper {

    /**
     * Map message object to a new ResonseEntity.  Will check the for success. If the message is not successful, a
     * status code of INTERNAL_SERVER_ERROR is returned.
     *
     * @param message message object extending IResponseMessage.
     * @return a ResponseEntity.
     */
    public <T extends IResponseMessage> ResponseEntity<T> map(T message) {
        if (message.isSuccessful()) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    /**
     * Map message object to a new ResonseEntity.  Will check the for success the supplied success status is set in the
     * new ResonseEntity. If the it is not successful, a status code of INTERNAL_SERVER_ERROR is returned.
     *
     * @param message message object extending IResponseMessage.
     * @return a ResponseEntity.
     */
    public <T extends IResponseMessage> ResponseEntity<T> mapWithHeaders(
            T message,
            HttpStatus successStatus,
            HttpHeaders httpHeaders) {
        if (message.isSuccessful()) {
            return ResponseEntity.status(successStatus).headers(httpHeaders).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }
}
