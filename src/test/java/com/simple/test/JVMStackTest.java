package com.simple.test;

import com.simple.jvm.rtda.Frame;
import com.simple.jvm.rtda.LocalVars;
import com.simple.jvm.rtda.OperandStack;
import com.simple.jvm.rtda.Thread;

public class JVMStackTest {

    public static void main(String[] args) {
        Frame frame = new Frame(new Thread(),100, 100);
        testLocalVars(frame.getLocalVars());
        testOperandStack(frame.getOperandStack());
    }

    private static void testLocalVars(LocalVars localVars) {
        localVars.setInt(0, 100);
        localVars.setInt(1, -100);
        System.out.println(localVars.getInt(0));
        System.out.println(localVars.getInt(1));
    }

    private static void testOperandStack(OperandStack operandStack) {
        operandStack.pushInt(100);
        operandStack.pushInt(-100);
        operandStack.pushRef(null);
        System.out.println(operandStack.popRef());
        System.out.println(operandStack.popInt());
    }

}
