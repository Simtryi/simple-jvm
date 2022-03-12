package com.simple.jvm.instructions.control.rtn;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 返回引用类型的值
 */
public class ARETURN extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Thread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame = thread.topFrame();
        Object ref = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(ref);
    }

}