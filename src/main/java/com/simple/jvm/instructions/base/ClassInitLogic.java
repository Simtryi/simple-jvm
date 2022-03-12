package com.simple.jvm.instructions.base;

import com.simple.jvm.rtda.Thread;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Method;
import com.simple.jvm.rtda.jvmstack.Frame;

/**
 * 类初始化
 */
public class ClassInitLogic {

    public static void initClass(Thread thread, Class clazz) {
        //  将类的initStarted状态设置成true
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Class clazz) {
        Method clinit = clazz.getClinitMethod();
        if (null == clinit) return;
        Frame newFrame = thread.newFrame(clinit);
        thread.pushFrame(newFrame);
    }

    private static void initSuperClass(Thread thread, Class clazz) {
        if (clazz.isInterface()) return;
        Class superClass = clazz.getSuperClass();
        if (null != superClass && !superClass.getInitStarted()) {
            initClass(thread, superClass);
        }
    }

}
