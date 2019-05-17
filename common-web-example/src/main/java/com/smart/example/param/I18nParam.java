package com.smart.example.param;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;


@Data
public class I18nParam {

    @NotBlank(message = "{vo.myerror.name}")
    private String name;
    @Max(200)
    private int age;
}
