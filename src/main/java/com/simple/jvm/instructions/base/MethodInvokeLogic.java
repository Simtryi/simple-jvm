package com.simple.jvm.instructions.base;

import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.heap.methodarea.Method;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.Slot;

/**
 * 在定位到需要调用的方法之后，Java虚拟机要给这个方法创建一个新的帧并把它推入Java虚拟机栈顶，然后传递参数
 */
public class MethodInvokeLogic {

    /**
     * 调用方法
     */
    public static void invokeMethod(Frame invokerFrame, Method method) {
        //  创建新的帧并推入Java虚拟机栈
        Thread thread = invokerFrame.getThread();
        Frame newFrame = thread.newFrame(method);
        thread.pushFrame(newFrame);

        //  确定方法的参数在局部变量表中占用多少位置，
        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            //  依次将参数从调用者的操作数栈中弹出，放进被调用方法的局部变量表中
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }

        //  hack
        if (method.isNative()) {
            if ("registerNatives".equals(method.getName())) {
                thread.popFrame();
            } else {
                throw new RuntimeException("native method " + method.getName());
            }
        }
    }

}
