package com.simple.jvm.instructions.loads.xaload;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 加载short类型数组
 */
public class SALOAD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int idx = stack.popInt();
        Object arrRef = stack.popRef();

        checkNotNull(arrRef);
        short[] shorts = arrRef.getShorts();
        checkIndex(shorts.length, idx);
        stack.pushInt(shorts[idx]);
    }

}