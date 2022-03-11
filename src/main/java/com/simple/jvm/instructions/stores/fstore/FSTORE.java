package com.simple.jvm.instructions.stores.fstore;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;

/**
 * 将float型变量从操作数栈顶弹出，并存入局部变量表
 */
public class FSTORE extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float val = operandStack.popFloat();

        LocalVars localVars = frame.getLocalVars();
        localVars.setFloat(idx, val);
    }

}
