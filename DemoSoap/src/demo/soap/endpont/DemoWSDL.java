package demo.soap.endpont;

import javax.xml.ws.Endpoint;

import demo.soap.mem.HelloWorldImpl;

public class DemoWSDL {

	public static void main(String[] args) {
		int port = 5555;
		String url = "http://localhost:"+port+"/hello/wsdl";
		Endpoint.publish(url, new HelloWorldImpl());
		System.out.println("done!");
		
	}
	
}
