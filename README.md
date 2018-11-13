###Idea生成Jar
参考链接：https://www.cnblogs.com/qifengshi/p/6036870.html
### bug
[root@Master /]# hadoop jar wordscount.jar WordCount
Exception in thread "main" java.lang.ClassNotFoundException: WordCount
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at org.apache.hadoop.util.RunJar.run(RunJar.java:214)
	at org.apache.hadoop.util.RunJar.main(RunJar.java:136)
### solve:
#### 找不到主方法，应该修改为WordCount的全类名