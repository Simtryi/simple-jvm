package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.ClassRef;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * checkcast指令和instanceof指令很像，区别在于：
 * instanceof指令会改变操作数栈（弹出对象引用，推入判断结果），
 * checkcast则不改变操作数栈（如果判断失败，直接抛出ClassCastException异常）
 */
public class CHECK_CAST extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        //  从操作数栈中弹出对象引用，再推回去
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        stack.pushRef(ref);

        //  如果引用是null，则指令执行结束，null引用可以转换成任何类型
        if (null == ref) {
            return;
        }
        //  否则解析类符号引用，判断对象是否是类的实例，
        //  如果是的话，指令执行结束
        RunTimeConstantPool cp = frame.getMethod().getClazz().getConstantPool();
        ClassRef clazzRef = (ClassRef) cp.getConstants(idx);
        Class clazz = clazzRef.resolvedClass();
    }

}
