package com.simple.jvm.instructions.stack.dup;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;
import com.simple.jvm.rtda.jvmstack.Slot;

/**
 * 复制栈顶的两个变量
 * bottom -> top
 * [...][c][b][a]____
 *           \____   |
 *                |  |
 *                V  V
 * [...][c][b][a][b][a]
 */
public class DUP2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

}
