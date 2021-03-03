
/*
java中的包相当于aardio中的库,
java中的package语句类似aardio中用namespace语句定义名字空间。

而且java与aardio一样,都要求包(或库)的名字空间与文件名保持一致。
aardio到lib目录下查找库,而java则到类搜录路径(classpath)中去查找包。
jre文件相当于一个虚拟的包目录。默认的aardio将"/java/"目录以及其目录下的所有jar添加到类搜录路径中。

例如对于aardio.sample.HelloworldApp 这个类,
我们就需要把他编译到 "/java/aardio/sample/HelloworldApp.class" 这个位置。
*/
package aardio.sample; //定义java包   
import java.io.*; //引入Java包   

public class HelloworldApp{   
	static native String aardio(String code); 
	 
	public static String main(String[] args) {   
		System.out.println("Hello this is a simply test"); //输出字符串,字符串用引号括起来 
		System.out.println( args[0]  );  
		System.out.println( args[1]  ); 
		return "aardio,你好,这是给你的返回值"; 
	}  
	public String name = "HelloworldApp2";
	public static int static_num = 123;
	public int test(int a) {   
		return a + 123;
	} 
	public String test_aardio() {  
		return aardio("import win;win.msgbox('我是Java,我正在执行aardio代码');return true;") ; 
	}      
}  