package com.simple.jvm.instructions.constants.consts;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * 将float型0推入操作数栈顶
 */
public class FCONST_0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat(0);
    }

}