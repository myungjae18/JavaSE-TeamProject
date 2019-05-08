package messenger.chatlist;

public class test {
	
	public test() {
		String path= "C:\\java_developer\\javaSE\\Messenger\\res\\dog\\0.jpg";
		
		int a=path.indexOf("dog");
		System.out.println(path.indexOf("dog"));
		path=path.substring(a+4);
		System.out.println(path);
		
	}
	
	public static void main(String[] args) {
		new test();
	}
}
