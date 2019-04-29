package com.smart.starter.core.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户信息
 * @author guwenchang
 * @date 2019-04-22 15:49
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer userId;
    private String username;
    private List<String> perms;
}