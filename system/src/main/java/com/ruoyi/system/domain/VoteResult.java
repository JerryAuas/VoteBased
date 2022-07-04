package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class VoteResult implements Serializable {
    private Integer vrid;

    private String vid;

    private String cid;

    private static final long serialVersionUID = 1L;
}
