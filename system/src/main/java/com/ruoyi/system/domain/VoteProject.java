package com.ruoyi.system.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * vote_project
 * @author 
 */
@Data
public class VoteProject implements Serializable {
    private String vid;

    private String vname;

    private String vdesc;

    private Integer flag1;

    private static final long serialVersionUID = 1L;
}