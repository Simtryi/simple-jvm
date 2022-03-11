package com.simple.jvm.instructions.stores.lstore;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

/**
 * 将long型变量从操作数栈顶弹出，并存入局部变量表
 */
public class LSTORE extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long val = operandStack.popLong();

        LocalVars localVars = frame.getLocalVars();
        localVars.setLong(idx, val);
    }

}
