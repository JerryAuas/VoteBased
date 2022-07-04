package com.ruoyi.system.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class CandidateProject implements Serializable {

    private Integer candidateId;

    private String projectId;

    private static final long serialVersionUID = 1L;
}
