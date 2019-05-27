package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: file type
 * @create 2019-03-01 15:22
 **/
public enum  FileType {
    /**照片类**/
    photo((byte)1, "照片类", "dict.fileType.photo"),
    /**运维管理类**/
    operation((byte)2, "运维管理类", "dict.fileType.operation"),
    /**文书类**/
    document((byte)3, "文书类", "dict.fileType.document"),
    /**其他**/
    temp((byte)4, "其他", "dict.fileType.temp");

    /** type of dict **/
    public String type = "fileType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    FileType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    FileType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static FileType get(byte value){
        for(FileType dic:FileType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
