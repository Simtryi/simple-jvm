package com.simple.jvm.instructions.conversions.l2x;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * long类型转换为double类型
 */
public class L2D extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long l = operandStack.popLong();
        float f = l;
        operandStack.pushFloat(f);
    }

}
