package com.kanavi.system.vo;

import lombok.Data;

/**
 * @author tanghailan
 * @version 1.0
 * @className UserVo
 * @description TODO
 * @date 2020-09-09 16:37
 */
@Data
public class UserVo {

    private String username;

    private String nickname;

    private String email;

    private Integer sex;

    private Long departmentId;
}
