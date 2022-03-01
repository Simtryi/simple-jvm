# simple-jvm

## 1 Java命令
## 1.1 -classpath/-cp
-classpath/-cp选项既可以指定目录，也可以指定JAR文件或者ZIP文件：
```shell
java -cp path\to\classes ...
java -cp path\to\lib1.jar ...
java -cp path\to\lib2.zip ...
```

还可以同时指定多个目录或文件，用分隔符分开即可。
```shell
java -cp path\to\classes;lib\a.jar;lib\b.jar;lib\c.zip ...
```

## 2 搜索class文件
## 2.1 类路径
Oracle的虚拟机实现根据类路径(classpath)来搜索类，按照搜索的先后顺序，类路径可以分为：
+ **启动类路径(bootstrap classpath)**：默认对应jre\lib目录，Java标准库位于该路径。
+ **扩展类路径(extension classpath)**：默认对应jre\lib\ext目录，使用Java扩展机制的类位于这个路径。
+ **用户类路径(user classpath)**：用户实现的类，以及第三方类库位于该路径。




## 参考文献
- [jcommander](https://jcommander.org/#_overview)