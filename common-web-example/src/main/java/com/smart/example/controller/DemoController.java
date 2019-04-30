package com.smart.example.controller;


import com.smart.example.ApiResult;
import com.smart.example.param.TestParam;
import com.smart.starter.core.security.annotation.Action;
import com.smart.starter.core.security.annotation.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author guwenchang
 * @date 2019-04-22 17:50
 */
@RequestMapping("demo")
@RestController
@Login(action = Action.SKIP)
@Api("demo文档")
public class DemoController {

    @ApiOperation(value = "put测试")
    @PutMapping(value = "/puttest")
    @ApiImplicitParam(value = "code",name = "code",dataType = "string",paramType = "query",defaultValue = "abc")
    public ApiResult puttest(String code){
        return ApiResult.success(code);
    }

    @ApiOperation(value = "delete测试")
    @DeleteMapping(value = "/deletetest")
    @ApiImplicitParam(value = "code",name = "code",dataType = "string",paramType = "query",example = "abcefg")
    public ApiResult deletetest(@RequestParam(value = "code") String code){
        return ApiResult.success(code);
    }

    @PostMapping("/reqbody")
    @ApiOperation(value = "RequestBody接口类型")
    public ApiResult reqbody(@RequestBody TestParam param){
        return ApiResult.success(param);
    }
    @PostMapping("/reqbody2")
    @ApiOperation(value = "RequestBody接口类型2",notes = "RequestBody测试接口2-string类型")
    public ApiResult reqbody2(@RequestBody String param){
        return ApiResult.success(param);
    }



}