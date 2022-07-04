package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class VoterProject implements Serializable {

    private Integer voterId;

    private String projectId;

    private static final long serialVersionUID = 1L;
}
