package com.ceiec.twmp.tmp.vo.parameter.edit;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-15 16:05
 * Description:
 **/

public class SysParameterEditVO  implements Serializable {

    private static final long serialVersionUID = -3244807277331243853L;

    private List<SysParameterEditParaVO> parameters;

    public List<SysParameterEditParaVO> getParameters() {
        return parameters;
    }

    public void setParameters(List<SysParameterEditParaVO> parameters) {
        this.parameters = parameters;
    }
}


