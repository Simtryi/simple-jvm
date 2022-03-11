# simple-jvm

## 1 Java命令
-Xbootclasspath：修改启动类路径。  
-classpath/-cp：指定用户类路径。  
-Xms：调整堆的初始大小。  
-Xmx：调增堆的最大大小。  

## 2 搜索class文件
Oracle的虚拟机实现根据类路径(classpath)来搜索类，按照搜索的先后顺序，类路径可以分为：
+ **启动类路径(bootstrap classpath)**：默认对应jre/lib目录，Java标准库位于该路径。
+ **扩展类路径(extension classpath)**：默认对应jre/lib/ext目录，使用Java扩展机制的类位于这个路径。
+ **用户类路径(user classpath)**：默认对应当前目录，用户实现的类以及第三方类库位于该路径。

## 3 解析class文件
> 构成class文件的基本数据单位是字节，可以把整个class文件当成一个字节流来处理。  
> Java虚拟机规范定义了u1、u2、u4三种数据类型来表示1、2和4字节无符号整数。  
> 相同类型的多条数据一般按表(table)的形式存储在class文件中。表由表头和表项(item)构成，表头是u2或u4整数。假设表头是n，后面就紧跟着n个表项数据。

## 3.1 class文件
1. 魔数  
很多文件格式都会规定满足该格式的文件必须以某几个固定字节开头，这几个字节主要起标识作用，叫做魔数(magic number)。
class文件的魔数是"0xCAFEBABE"。Java虚拟机规范规定，如果加载的class文件不符合要求的格式，Java虚拟机实现就抛出java.lang.ClassFormatError异常。

2. 版本号  
魔数之后是class文件的次版本号和主版本号，都是u2类型。
特定的Java虚拟机实现只能支持版本号在某个范围内的class文件，Oracle的实现是完全向后兼容的，比如Java SE 8支持版本号为45.0~52.0的class文件。
如果版本号不在支持的范围内，Java虚拟机实现就抛出java.lang.UnsupportedClassVersionError异常。

3. 常量池  
版本号之后是常量池，里面存放着各式各样的常量信息，包括数字和字符串常量、类和接口名、字段和方法名等等。

4. 类访问标志  
常量池之后是类访问标志，这是一个16位的"bitmask"，指出class文件定义的是类还是接口，访问级别是public还是private，等等。

5. 类索引、超类索引  
类访问标志之后是两个u2类型的常量池索引，分别给出类名和超类名。
class文件存储的类名类似完全限定名，但是把点换成了斜线，Java语言规范把这种名字叫做二进制名(binary names)。
因为每个类都有名字，所以thisClass必须是有效的常量池索引。
除java.lang.Object之外，其他类都有超类，所以supperClass只在Object.class中是0，在其他class文件中必须是有效的常量池索引。

6. 接口索引表  
类和超类索引后面是接口索引表，表中存放的也是常量池索引，给出该类实现的所有接口的名字。

7. 字段表、方法表  
接口索引表之后是字段表和方法表，分别存储字段和方法信息。字段和方法的基本结构大致相同，差别仅在于属性表。
和类一样，字段和方法也有自己的访问标志。访问标志之后是一个常量池索引，给出字段名或方法名。然后又是一个常量池索引，给出字段或方法的描述符，最后是属性表。

## 3.2 常量池
> 常量池实际上是一个表，但是表头给出的常量池大小比实际大1，假设表头给出的值是n，那么常量池实际大小是n-1。   
> 有效的常量池索引是1~n-1，0是无效索引，表示不指向任何常量。  
> CONSTANT_Long_info和CONSTANT_Double_info各占两个位置。  

1. CONSTANT_Integer_info  
CONSTANT_Integer_info使用4字节存储整数常量。
CONSTANT_Integer_info正好可以容纳一个Java的int型常量，但实际上比int更小的boolean、byte、short和char类型的常量也放在CONSTANT_Integer_info中。

2. CONSTANT_Float_info  
CONSTANT_Float_info使用8字节存储IEEE754单精度浮点数常量。

3. CONSTANT_Long_info  
CONSTANT_Long_info使用8字节存储整数常量。

4. CONSTANT_Double_info  
CONSTANT_Double_info使用8字节存储IEEE754双精度浮点数。

5. CONSTANT_Utf8_info   
CONSTANT_Utf8_info常量里放的是MUTF-8编码的字符串。

6. CONSTANT_String_info  
CONSTANT_String_info常量表示java.lang.String字面量。
CONSTANT_String_info本身并不存放字符串数据，只存了常量池索引，这个索引指向一个CONSTANT_Utf8_info常量。

7. CONSTANT_Class_info  
CONSTANT_Class_info常量表示类或者接口的符号引用。
类和超类索引，以及接口表中的接口索引指向的都是CONSTANT_Class_info常量。

8. CONSTANT_NameAndType_info  
CONSTANT_NameAndType_info给出字段或方法的名称和描述符。
CONSTANT_Class_info和CONSTANT_NameAndType_info加在一起可以唯一确定一个字段或者方法。
Java虚拟机规范定义了一种简单的语法来描述字段和方法，可以根据下面的规则生成描述符：  
1）类型描述符。   
①基本类型byte、short、char、int、long、float和double的描述符是单个字母，分别对应B、S、C、I、J、F和D。  
②引用类型的描述符是L＋类的完全限定名＋分号。   
③数组类型的描述符是[＋数组元素类型描述符。  
2）字段描述符就是字段类型的描述符。   
3）方法描述符是（分号分隔的参数类型描述符）+返回值类型描述符，其中void返回值由单个字母V表示。  

9. CONSTANT_Fieldref_info、CONSTANT_Methodref_info和CONSTANT_InterfaceMethodref_info  
CONSTANT_Fieldref_info表示字段符号引用，CONSTANT_Methodref_info表示普通（非接口）方法符号引用，CONSTANT_InterfaceMethodref_info表示接口方法符号引用。

10. CONSTANT_MethodType_info、CONSTANT_MethodHandle_info和CONSTANT_InvokeDynamic_info  
CONSTANT_MethodType_info、CONSTANT_MethodHandle_info和CONSTANT_InvokeDynamic_info是Java SE 7才添加到class文件中的，目的是支持新增的invokedynamic指令。

## 3.3 属性表
> 属性表中存放的属性名实际上并不是编码后的字符串，而是常量池索引，指向常量池中的CONSTANT_Utf8_info常量。

1. Deprecated和Synthetic属性  
Deprecated和Synthetic是最简单的两种属性，仅起标记作用，不包含任何数据。
由于不包含任何数据，所以attribute_length的值必须是0。
Deprecated属性用于指出类、接口、字段或方法已经不建议使用，编译器等工具可以根据Deprecated属性输出警告信息。
Synthetic属性用来标记源文件中不存在、由编译器生成的类成员，引入Synthetic属性主要是为了支持嵌套类和嵌套接口。

2. SourceFile属性  
SourceFile是可选定长属性，只会出现在ClassFile结构中，用于指出源文件名。

3. ConstantValue属性  
ConstantValue是定长属性，只会出现在field_info结构中，用于表示常量表达式的值。

4. Code属性  
Code是变长属性，只存在于method_info结构中。Code属性中存放字节码等方法相关信息。

5. Exceptions属性  
Exceptions是变长属性，记录方法抛出的异常表。

6. LineNumberTable和LocalVariableTable属性  
LineNumberTable属性表存放方法的行号信息，LocalVariableTable属性表中存放方法的局部变量信息。
这两种属性和SourceFile属性都属于调试信息，都不是运行时必需的。
在使用javac编译器编译Java程序时，默认会在class文件中生成这些信息。可以使用javac提供的-g：none选项来关闭这些信息的生成。

# 4 运行时数据区
> Java虚拟机规范将内存区域叫做运行时数据区，运行时数据区可以分为两类：一类是多线程共享的，另一类是线程私有的。  
> 多线程共享的运行时数据区在Java虚拟机启动时创建，退出时销毁。线程私有的运行时数据区在线程创建时创建，退出时销毁。  

## 4.1 Java虚拟机栈
1. 定义  
Java虚拟机栈是描述Java方法运行过程的内存模型。   
Java虚拟机栈是线程私有，随着线程创建而创建，随着线程的结束而销毁。  
Java虚拟机栈会为每个即将运行的Java方法创建一块叫做“栈帧”的区域，用于存放该方法运行过程中的一些信息，如：
+ 局部变量表
+ 操作数栈
+ 动态链接
+ 方法出口信息
+ ...

2. 局部变量表  
局部变量表随着栈帧的创建而创建，它的大小在编译时确定，创建时只需分配事先规定的大小即可。   
在方法运行过程中，局部变量表的大小不会发生改变。  
局部变量表按索引访问，可以把它想象成一个数组，根据Java虚拟机规范，这个数组每个元素至少可以容纳一个int值或引用值，两个连续的元素可以容纳一个long或double值。

3. 操作数栈  
操作数栈的大小是编译器已经确定的。

# 5 指令集和解释器
## 5.1 字节码
每一个类或者接口会被Java编译器编译成一个class文件，类或接口的方法信息存放在class文件的method_info结构中。  
如果方法不是抽象的，也不是本地方法，方法的Java代码就会被编译器编译成字节码(即使方法是空的，编译器也会生成一条return语句)，存放在method_info结构的Code属性中。  
字节码中存放编码后的Java虚拟机指令，每条指令都已一个单字节的操作码(opcode)开头

操作数栈和局部变量表只存放数据的值，并不记录数据类型。操作码的助记符反映操作数据的类型：
+ **a**：reference，例如：aload、astore、areturn。
+ **b**：byte/boolean，例如：bipush、baload。
+ **c**：char，例如：caload、castore。
+ **d**：double，例如：dload、dstore、dadd。
+ **f**：float，例如：fload、fstore、fadd。
+ **i**：int，例如：iload、istore、iadd。
+ **l**：long，例如：load、lstore、ladd。
+ **s**：short，例如：sipush、sastore。

## 5.2 指令
1. 常量指令  
常量指令把常量推入操作数栈顶，常量可以来自三个地方：隐含在操作码里、操作数和运行时常量池。常量指令共有21条，

2. 加载指令  
加载指令从局部变量表获取变量，然后推入操作数栈顶。  
加载指令共33条，按照所操作变量的类型可以分为6类：
+ aload系列指令：操作引用类型变量。
+ dload系列指令：操作double类型变量。
+ fload系列指令：操作float类型变量。
+ iload系列指令：操作int类型变量。
+ lload系列指令：操作long类型变量。
+ xaload指令：操作数组。

3. 存储指令
存储指令把变量从操作数栈顶弹出，然后存入局部变量表。

4. 栈指令  
栈指令直接对操作数栈进行操作，共9条：pop和pop2指令将栈顶变量弹出，dup系列指令复制栈顶变量，swap指令交换栈顶的两个变量。   
和其他类型的指令不同，栈指令并不关心变量类型。

5. 数学指令   
数学指令大致对应Java语言中的加、减、乘、除等数学运算符。数学指令包括算术指令、位移指令和布尔运算指令等。  
算术指令可以分为加法(add)指令、减法(sub)指令、乘法(mul)指令、除法(div)指令、求余(rem)指令和取反(neg)指令。  
位移指令可以分为左移和右移两种，右移指令又可以分为算术右移(有符号右移)和逻辑右移(无符号右移)两种。
布尔运算指令只能操作int和long变量，分为按位与(and)、按位或(or)、按位异或(xor)3种。

6. 类型转换指令  
类型转换指令大致对应Java语言中的基本类型强制转换操作。  
按照被转换变量的类型，类型转换指令可以分为4种： 
+ i2x系列：指令把int变量强制转换成其他类型。
+ 12x系列：指令把long变量强制转换成其他类型。
+ f2x系列：指令把float变量强制转换成其他类型。
+ d2x系列：指令把double变量强制转换成其他类型。

7. 比较指令  
比较指令可以分为两类：一类将比较结果推入操作数栈顶，一类根据比较结果跳转。  
比较指令是编译器实现if-else、for、while等语句的基石，共有19条。

8. 控制指令  
控制指令共有11条。

9. 扩展指令  
扩展指令共有6条。

## 5.3 解释器
Java虚拟机解释器的大致逻辑如下：
```
do {
    atomically calculate pc and fetch opcode at pc;
    if (operands) fetch operands;
    execute the action for the opcode;
} while (there is more to do);
```

## 参考文献
- [用Java实现JVM](https://bugstack.cn/md/java/develop-jvm/2019-05-01-%E7%94%A8Java%E5%AE%9E%E7%8E%B0JVM%E7%AC%AC%E4%B8%80%E7%AB%A0%E3%80%8A%E5%91%BD%E4%BB%A4%E8%A1%8C%E5%B7%A5%E5%85%B7%E3%80%8B.html)  
- 《自己动手写Java虚拟机》  
- [jcommander](https://jcommander.org/#_overview)
