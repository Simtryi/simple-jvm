package com.simple.jvm.instructions.constants.ldc;

import com.simple.jvm.instructions.base.impl.Index8Instruction;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.heap.methodarea.StringPool;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 用于加载int、float和字符串常量，java.lang.Class实例或者MethodType和MethodHandle实例
 */
public class LDC extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        //  从当前类的运行时常量池中取出常量
        OperandStack stack = frame.getOperandStack();
        Class clazz = frame.getMethod().getClazz();
        RunTimeConstantPool runTimeConstantPool = frame.getMethod().getClazz().getConstantPool();
        java.lang.Object c = runTimeConstantPool.getConstants(idx);

        if (c instanceof Integer) {
            stack.pushInt((Integer) c);
            return;
        }

        if (c instanceof Float) {
            stack.pushFloat((Float) c);
            return;
        }

        if (c instanceof String) {
            Object internedStr = StringPool.jString(clazz.getLoader(), (String) c);
            stack.pushRef(internedStr);
        }

        throw new RuntimeException("todo ldc " + c.getClass().getName());
    }

}
