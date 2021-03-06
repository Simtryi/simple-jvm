package com.simple.jvm.instructions.comparisons.fcmp;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 比较float变量
 */
public class FCMPG extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popFloat();
        double v1 = operandStack.popFloat();

        if (v1 > v2) {
            operandStack.pushInt(1);
            return;
        }

        if (v1 == v2) {
            operandStack.pushInt(0);
            return;
        }

        if (v1 < v2) {
            operandStack.pushInt(-1);
            return;
        }

        //  当两个float变量中至少有一个是NaN时，比较结果是1
        operandStack.pushInt(1);
    }

}
