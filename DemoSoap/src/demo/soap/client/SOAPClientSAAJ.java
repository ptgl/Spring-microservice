package demo.soap.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

public class SOAPClientSAAJ {

	public static void main(String[] args) {
		try {

			// wsdl:
			// http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx?wsdl

			// Create SOAP Connection
			// call from a bean/class
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			// Send SOAP Message to SOAP Server
			// String url =
			// "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
			String url = "http://localhost:8088/demo/soap";

			// SOAPMessage soapResponse =
			// soapConnection.call(createSOAPRequest(), url);

			Map<String, String> data = new HashMap<>();
			data.put("email", "csc@gmail.com");
			data.put("LicenseKey", "112546");
			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest2("VerifyEmail", data), url);

			// get the value in soapResponse

			SOAPBody soapBody = soapResponse.getSOAPBody();

			// Process the SOAP Response
			// printSOAPResponse(soapResponse);

			// convert SOAPMessage to String
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			soapResponse.writeTo(out);
			String strMsg = new String(out.toByteArray());
			System.out.print("\nResponse SOAP Message = \n");
			System.out.println(strMsg);

			String fileXml = "<a:CurrentWeather><Location>Katunayake, Sri Lanka (VCBI) 07-10N 079-53E 8M</Location></a:CurrentWeather>";

			

			// GET VALUE FORM SOAPMESSAGE RESPONSE

			VTDGen vg = new VTDGen();
			vg.setDoc(strMsg.getBytes());
			vg.parse(true);

			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot(vn);
			ap.declareXPathNameSpace("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
			ap.declareXPathNameSpace("ws", "http://ws.cdyne.com/");

			String[] tags = { "ResponseText", "ResponseCode", "LastMailServer", "GoodEmail" };
			String rootXpath = "/soapenv:Envelope/soapenv:Body/ws:VerifyEmailResponse/ws:VerifyEmailResult/ws:";

			for (String s : tags) {
				ap.selectXPath(rootXpath + s);
				int i = -1;
				while ((i = ap.evalXPath()) != -1) {
					long l = vn.getContentFragment();
					String value = vn.toString((int) l, (int) (l >> 32));
					System.out.println(" -==> " + vn.toString((int) l, (int) (l >> 32)));
				}
			}

			// Convert to Json
			final int PRETTY_PRINT_INDENT_FACTOR = 4;
			JSONObject xmlJSONObj = XML.toJSONObject(strMsg);
			String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
			System.out.println("\nResponse SOAP Message Json = \n");
			System.out.println(jsonPrettyPrintString);

			soapConnection.close();
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}

	}

	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://ws.cdyne.com/";
		String prefix = "example";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail", prefix);
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email", prefix);
		soapBodyElem1.addTextNode("mutantninja@gmail.com");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey", prefix);
		soapBodyElem2.addTextNode("123");

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + "VerifyEmail");

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nResponse SOAP Message = \n");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static SOAPMessage createSOAPRequest2(String function, Map<String, String> values) throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://ws.cdyne.com/";
		String prefix = "ws";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(prefix, serverURI);

		/*<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.cdyne.com/">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <ws:VerifyEmail>
		         <ws:email>?</ws:email>	       
		         <ws:LicenseKey>?</ws:LicenseKey>
		      </ws:VerifyEmail>
		   </soapenv:Body>
		</soapenv:Envelope>*/

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement(function, prefix);

		for (Map.Entry<String, String> entry : values.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
			SOAPElement soapBodyChildElem = soapBodyElem.addChildElement(entry.getKey(), prefix);
			soapBodyChildElem.addTextNode(entry.getValue());
		}

		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + function);

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("\nRequest SOAP Message = ");
		soapMessage.writeTo(System.out);
		System.out.println();

		return soapMessage;
	}

}

// DRAFT

/*
 * Iterator itr=soapResponse.getSOAPBody().getChildElements(); while
 * (itr.hasNext()) { Node node=(Node)itr.next(); if
 * (node.getNodeType()==Node.ELEMENT_NODE) { Element ele= (Element)node;
 * System.out.println(ele.get.getNodeName() + " = " + ele.getTextContent()); }
 * else if (node.getNodeType()==Node.TEXT_NODE) { //do nothing here most likely,
 * as the response nearly never has mixed content type //this is just for your
 * reference } }
 */

// List<SOAPElement> elements =
// (List<SOAPElement>)soapBody.getAllAttributes();
// soapBody.getAttribute("ResponseText");

// SOAPElement soapElement = (SOAPElement)
// soapBody.getChildNodes().item(1).getFirstChild().getNodeName()getChildElements()..next();
/*
 * soapElement = (SOAPElement) soapElement.getChildElements().next(); String
 * value = soapElement.getValue();
 */




//String aa = "<soapenv:Envelope
			// xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"
			// xmlns:ws=\"http://ws.cdyne.com/\"><soapenv:Body><ws:VerifyEmailResponse><ws:ResponseText>csc@gmail.com</ws:ResponseText></ws:VerifyEmailResponse></soapenv:Body></soapenv:Envelope>";

			/*
			 * DocumentBuilderFactory factory =
			 * DocumentBuilderFactory.newInstance(); DocumentBuilder builder =
			 * null; try { builder = factory.newDocumentBuilder(); } catch
			 * (ParserConfigurationException e) { e.printStackTrace(); }
			 * Document xmlDocument = builder.parse(new
			 * ByteArrayInputStream(fileXml.getBytes())); XPath xPath =
			 * XPathFactory.newInstance().newXPath(); //String xpath =
			 * "/soapenv:Envelope/soapenv:Body/ws:VerifyEmailResponse/ws:ResponseText";
			 * String xpath = "/a:CurrentWeather/Location"; String location =
			 * xPath.compile(xpath).evaluate(xmlDocument);
			 */



