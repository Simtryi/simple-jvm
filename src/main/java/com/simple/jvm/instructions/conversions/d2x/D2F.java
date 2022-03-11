package com.simple.jvm.instructions.conversions.d2x;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * double类型转换为float类型
 */
public class D2F extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double d = operandStack.popDouble();
        float f = (float) d;
        operandStack.pushFloat(f);
    }

}
