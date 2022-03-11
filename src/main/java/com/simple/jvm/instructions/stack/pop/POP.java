package com.simple.jvm.instructions.stack.pop;


import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.OperandStack;

/**
 * 将栈顶变量弹出，只能用于弹出int、float等占一个操作数栈位置的变量
 * bottom -> top
 * [...][c][b][a]
 *             |
 *             V
 * [...][c][b]
 */
public class POP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popSlot();
    }

}
