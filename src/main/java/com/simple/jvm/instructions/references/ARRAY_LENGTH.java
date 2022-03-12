package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 获取数组长度，arraylength指令只需要一个操作数，即从操作数栈顶弹出的数组引用
 */
public class ARRAY_LENGTH extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        //  获取数组引用
        OperandStack operandStack = frame.getOperandStack();
        Object arrRef = operandStack.popRef();
        //  如果数组引用时null，则需要抛出NullPointerException异常
        if (null == arrRef){
            throw new NullPointerException();
        }

        //  获取数组长度，并将数组长度推入操作数栈顶
        int arrLen = arrRef.getArrayLength();
        operandStack.pushInt(arrLen);
    }

}
