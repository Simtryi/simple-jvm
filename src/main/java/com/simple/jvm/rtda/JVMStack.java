package com.simple.jvm.rtda;

/**
 * Java虚拟机栈
 * <p>
 *  Java虚拟机栈是描述Java方法运行过程的内存模型。
 *  Java虚拟机栈会为每个即将运行的Java方法创建一块叫做“栈帧”的区域，用于存放该方法运行过程中的一些信息，如：局部变量表、操作数栈、动态链接、方法出口信息等。
 * </p>
 */
public class JVMStack {

    private int maxSize;    //  栈的容量
    private int size;       //  栈的当前大小
    private Frame _top;     //  栈顶

    public JVMStack(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 栈帧入栈
     */
    public void push(Frame frame) {
        if (size > maxSize) {
            //  如果栈已经满了，按照Java虚拟机规范，抛出StackOverflowError异常
            throw new StackOverflowError();
        }

        if (_top != null) {
            frame.lower = _top;
        }

        _top = frame;
        size++;
    }

    /**
     * 栈帧出栈
     */
    public Frame pop() {
        if (_top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }

        Frame top = _top;
        _top = top.lower;
        top.lower = null;
        size--;

        return top;
    }

    /**
     * 返回栈顶栈帧
     */
    public Frame top() {
        if (_top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }
        return _top;
    }

}
