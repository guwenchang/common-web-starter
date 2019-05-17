package com.smart.example.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class TestParam {

    @ApiModelProperty("名称")
    private String name;

}
