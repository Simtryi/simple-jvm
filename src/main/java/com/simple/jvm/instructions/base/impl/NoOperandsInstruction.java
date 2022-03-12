package com.simple.jvm.instructions.base.impl;

import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 没有操作数的指令
 */
public class NoOperandsInstruction implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
        //  empty
    }

    @Override
    public void execute(Frame frame) {
        //  nothing to do
    }

    /**
     * 判断数组是否为空
     */
    protected void checkNotNull(Object ref) {
        if (null == ref) {
            throw new NullPointerException();
        }
    }

    /**
     * 判断数组索引是否小于0，或者大于等于数组长度
     */
    protected void checkIndex(int arrLen, int idx) {
        if (idx < 0 || idx >= arrLen) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

}
