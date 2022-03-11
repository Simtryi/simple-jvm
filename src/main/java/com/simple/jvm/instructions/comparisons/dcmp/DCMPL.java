package com.simple.jvm.instructions.comparisons.dcmp;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * 比较double变量
 */
public class DCMPL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popDouble();
        double v1 = operandStack.popDouble();

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

        operandStack.pushInt(-1);
    }

}