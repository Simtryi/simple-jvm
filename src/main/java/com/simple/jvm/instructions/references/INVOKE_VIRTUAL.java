package com.simple.jvm.instructions.references;

import com.simple.jvm.instructions.base.MethodInvokeLogic;
import com.simple.jvm.instructions.base.impl.Index16Instruction;
import com.simple.jvm.rtda.heap.constantpool.MethodRef;
import com.simple.jvm.rtda.heap.constantpool.RunTimeConstantPool;
import com.simple.jvm.rtda.heap.methodarea.Class;
import com.simple.jvm.rtda.heap.methodarea.Method;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.heap.methodarea.StringPool;
import com.simple.jvm.rtda.heap.util.MethodLookup;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

/**
 * 调用对象实例方法
 */
public class INVOKE_VIRTUAL extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        Class currentClass = frame.getMethod().getClazz();
        RunTimeConstantPool runTimeConstantPool = currentClass.getConstantPool();
        MethodRef methodRef = (MethodRef) runTimeConstantPool.getConstants(idx);

        Method resolvedMethod = methodRef.resolvedMethodRef();
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (null == ref) {
            if ("println".equals(methodRef.getName())) {
                _println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected() &&
                resolvedMethod.getClazz().isSubClassOf(currentClass) &&
                !resolvedMethod.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                ref.getClazz() != currentClass &&
                !ref.getClazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        //  从对象的类中查找真正要调用的方法
        Method methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(), methodRef.getName(), methodRef.getDescriptor());
        //  如果找不到方法，或者找到的是抽象方法，则需要抛出AbstractMethodError异常
        if (null == methodToBeInvoked || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        //  一切正常，调用方法
        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    //  hack
    private void _println(OperandStack stack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.out.println(stack.popInt() != 0);
                break;
            case "(C)V":
                System.out.println(stack.popInt());
                break;
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.println(stack.popInt());
                break;
            case "(F)V":
                System.out.println(stack.popFloat());
                break;
            case "(J)V":
                System.out.println(stack.popLong());
                break;
            case "(D)V":
                System.out.println(stack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                Object jStr = stack.popRef();
                String goStr = StringPool.goString(jStr);
                System.out.println(goStr);
                break;
            default:
                System.out.println(descriptor);
                break;
        }
        stack.popRef();
    }

}


