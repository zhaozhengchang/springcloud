package com.ceiec.twmp.tmp.utils.response;

/**
 * CreateDate：2018/6/28 <br/>
 * Author：wenliang <br/>
 * Description: enums for module code
 **/
public enum EModuleCode {

    /** code for common module */
    MODULE_COMMON("0", "code for common module"),
    /** code for login module */
    MODULE_LOGIN("1", "code for login module"),
    /** code for email module */
    MODULE_EMAIL("2", "code for email module"),
    /** code for web guide module */
    MODULE_GUIDE("3", "code for tmp module"),
    /** code for web insight module */
    MODULE_INSIGHT("4", "code for trunk module");

    /** module prefix code */
    private String moduleCode;

    /** description for module prefix code */
    private String codeDesc;

    /**
     * private construction function
     *
     * @param moduleCode module prefix code
     * @param codeDesc description for module prefix code
     */
    EModuleCode(String moduleCode, String codeDesc) {
        this.moduleCode = moduleCode;
        this.codeDesc = codeDesc;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getCodeDesc() {
        return codeDesc;
    }
}
