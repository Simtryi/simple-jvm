package com.simple.jvm.instructions.math.sh;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * int类型左移
 */
public class ISHL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        //  v2指出要移位多少比特
        int v2 = operandStack.popInt();
        //  v1要进行位移操作的变量
        int v1 = operandStack.popInt();
        int s = v2 & 0x1f;
        int res = v1 << s;
        operandStack.pushInt(res);
    }

}
