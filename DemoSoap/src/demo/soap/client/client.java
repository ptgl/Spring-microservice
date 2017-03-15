package demo.soap.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import demo.soap.mem.IHelloWorld;

public class client {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:4444/hello/wsdl?wsdl");

		// 1st argument service URI, refer to wsdl document above,
		// targetNamespace [revert of package interface]
		// 2nd argument is service name, refer to wsdl document above, name,
		// impl class + Service
		QName qname = new QName("http://mem.soap.demo/", "HelloWorldImplService");
		Service service = Service.create(url, qname);
		
		IHelloWorld hello = service.getPort(IHelloWorld.class);
		
		hello.sayHello("lan", "tran");
		hello.getAge(11);
		
		System.out.println("done impl");

	}

}
