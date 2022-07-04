package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class VoteCount implements Serializable {

    private Integer voteNum;

    private Integer userId;

    private String nickName;

    private static final long serialVersionUID = 1L;
}
