package com.simple.jvm.instructions.constants.ipush;


import com.simple.jvm.instructions.base.BytecodeReader;
import com.simple.jvm.instructions.base.Instruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 从操作数中获取一个short型整数，扩展成int型，然后推入栈顶
 */
public class SIPUSH implements Instruction {

    private short val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(val);
    }

}
