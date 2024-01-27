package com.beizhi.common.eunm;

import lombok.Getter;

/**
 * @author 14669
 * @date 2024/1/27 17:43
 * @describe
 */
public enum IsRealEunm {
    Audited(1), // 已审核
    Unaudited(-1); // 未审核

    @Getter
    private Integer isReal;
    IsRealEunm(Integer isReal){
        this.isReal = isReal;
    }
}
