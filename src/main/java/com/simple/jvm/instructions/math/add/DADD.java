package com.simple.jvm.instructions.math.add;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * double类型加法
 */
public class DADD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double v1 = operandStack.popDouble();
        double v2 = operandStack.popDouble();
        double res = v1 + v2;
        operandStack.pushDouble(res);
    }

}
