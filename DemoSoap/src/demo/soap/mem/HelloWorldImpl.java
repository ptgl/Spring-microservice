package demo.soap.mem;

import javax.jws.WebService;

@WebService(endpointInterface = "demo.soap.mem.IHelloWorld")
public class HelloWorldImpl implements IHelloWorld{

	//@Override
	public String sayHello(String name, String name2) {
		System.out.println("Hello "+name+" "+name2);
		return "Hello "+name+" "+name2;
	}

	//@Override
	public int getAge(int age) {
		System.out.println("age: "+age);
		return age;
	}

	//@Override
	public String getGender() {
	
		return "{\"gender\":[\"M\",\"F\"]}";
	}

}
