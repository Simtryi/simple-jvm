package com.simple.jvm.instructions.constants.consts;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * 将long型0推入操作数栈顶
 */
public class LCONST_0  extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong(0);
    }

}
