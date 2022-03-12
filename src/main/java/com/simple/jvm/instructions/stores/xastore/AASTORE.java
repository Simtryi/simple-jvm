package com.simple.jvm.instructions.stores.xastore;

import com.simple.jvm.instructions.base.impl.NoOperandsInstruction;
import com.simple.jvm.rtda.heap.methodarea.Object;
import com.simple.jvm.rtda.jvmstack.Frame;
import com.simple.jvm.rtda.jvmstack.OperandStack;

public class AASTORE extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        //  要赋给数组元素的值
        Object ref = stack.popRef();
        //  数组索引
        int idx = stack.popInt();
        //  数组引用
        Object arrRef = stack.popRef();

        checkNotNull(arrRef);
        Object[] refs = arrRef.getRefs();
        checkIndex(refs.length, idx);

        //  按索引给数组元素赋值
        refs[idx] = ref;
    }

}
