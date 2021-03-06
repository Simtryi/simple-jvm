package com.simple.jvm.instructions.stack.pop;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * double和long变量在操作数栈中占据两个位置，需要使用pop2指令弹出
 * bottom -> top
 * [...][c][b][a]
 *          |  |
 *          V  V
 * [...][c]
 */
public class POP2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popSlot();
        operandStack.popSlot();
    }

}
