package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.service.valueobject.IResponseMessage;
import com.jkg.www.outofahat.service.valueobject.GenericSingleResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RestController
@RequestMapping("api")
public class SampleController {
    Logger logger = LoggerFactory.getLogger(SampleController.class);
    private ResponseEntityMapper responseEntityMapper;

    @Value("${test}")
    private String test;

    @Autowired
    public SampleController(ResponseEntityMapper responseEntityMapper) {
        this.responseEntityMapper = responseEntityMapper;
    }

    @RequestMapping(path = "/v1/sample/info", produces = "application/json", method = RequestMethod.GET)
    @ApiOperation(value = "getSample", nickname = "getSample")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = GenericSingleResponse.class),
            @ApiResponse(code = 500, message = "Error", response = GenericSingleResponse.class)})
    @ResponseBody
    public ResponseEntity<? extends IResponseMessage> getInfo() {
        GenericSingleResponse response = GenericSingleResponse.success(test);
        try {
            Assert.isTrue(false, "doh");
        } catch (Exception ex) {
            logger.info("retrieving test:" + test, ex);
            logger.warn("retrieving test:" + test, ex);
            logger.error("retrieving test:" + test, ex);
        }
        return responseEntityMapper.mapWithReqeustId(response);
    }
}
