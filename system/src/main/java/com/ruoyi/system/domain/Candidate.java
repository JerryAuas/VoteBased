package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Candidate implements Serializable {
    private Integer userId;

    private String nickName;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private static final long serialVersionUID = 1L;
}
