package com.simple.jvm.instructions.stack.dup;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;
import com.simple.jvm.rtda.Slot;

/**
 * bottom -> top
 * [...][c][b][a]
 *           __/
 *          |
 *          V
 * [...][c][a][b][a]
 */
public class DUP_X1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

}
