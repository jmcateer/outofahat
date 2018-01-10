package com.jkg.www.outofahat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api")
public class ContactController {
    private ResponseEntityMapper responseEntityMapper;

    @Autowired
    public ContactController(ResponseEntityMapper responseEntityMapper) {
        this.responseEntityMapper = responseEntityMapper;
    }


}
