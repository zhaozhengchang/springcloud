package com.ceiec.twmp.tmp.vo.alarm;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-18 10:14
 **/
public class AlarmNumberVO {

    private Long toDisposeNum;

    private Long toAllocateNum;

    private Long disposedNum;

    public Long getToDisposeNum() {
        return toDisposeNum;
    }

    public void setToDisposeNum(Long toDisposeNum) {
        this.toDisposeNum = toDisposeNum;
    }

    public Long getToAllocateNum() {
        return toAllocateNum;
    }

    public void setToAllocateNum(Long toAllocateNum) {
        this.toAllocateNum = toAllocateNum;
    }

    public Long getDisposedNum() {
        return disposedNum;
    }

    public void setDisposedNum(Long disposedNum) {
        this.disposedNum = disposedNum;
    }
}
