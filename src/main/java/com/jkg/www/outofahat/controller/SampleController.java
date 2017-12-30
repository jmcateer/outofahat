package com.jkg.www.outofahat.controller;

import com.jkg.www.outofahat.valueobject.IResponseMessage;
import com.jkg.www.outofahat.valueobject.GenericSingleResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("api")
public class SampleController {
    private ResponseEntityMapper responseEntityMapper;

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
        GenericSingleResponse response = GenericSingleResponse.success("woohooo");
        return responseEntityMapper.mapWithReqeustId(response);
    }
}
