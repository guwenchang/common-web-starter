package com.smart.example.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Levin
 * @since 2018/12/17 0017
 */
@Data
@ApiModel
public class TestParam {

    @ApiModelProperty("名称")
    private String name;

}
