package com.simple.jvm.instructions.constants.consts;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * 将int型3推入操作数栈顶
 */
public class ICONST_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(3);
    }

}