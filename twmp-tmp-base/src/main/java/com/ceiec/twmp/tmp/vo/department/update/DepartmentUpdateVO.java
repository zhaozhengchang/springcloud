package com.ceiec.twmp.tmp.vo.department.update;

import com.ceiec.twmp.tmp.vo.department.add.DepartmentAddVO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: The type departmen update vo.
 **/
public class DepartmentUpdateVO extends DepartmentAddVO {
    /**
     * serializable ID
     */
    private static final long serialVersionUID = -1735338389214671637L;

    /** departmentId */
    @NotNull(message = "message.common.message.notnull")
    private Long departmentId;
}







