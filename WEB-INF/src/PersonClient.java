package onjava;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.soap.*;
import org.apache.soap.encoding.*;
import org.apache.soap.encoding.soapenc.*;
import org.apache.soap.util.xml.*;
import org.apache.soap.rpc.*;

public class PersonClient {
	public static void main(String[] args) throws Exception {
        URL url = new URL ("http://localhost:6666/soap/servlet/rpcrouter");
        String name = "Srikanth";
        //SoapClient client = new SoapClient(urlString);
        System.out.println("*** Assigned the url for the sopa router as: "+url);
        /** construct a Person object**/
        Person nameObject = new Person();
        nameObject.setName(name);
        nameObject.setAge(22);
		Call call = new Call();
		call.setTargetObjectURI("urn:greetingService");
		call.setMethodName("sayGreeting");
		call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
		//create the type mapping registry
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		//new instance of the serializer class
		BeanSerializer bsr = new BeanSerializer();
		//create a qname object
		QName qn = new QName("urn:greetingService", "onjava.Person");
		//map the type
		smr.mapTypes(Constants.NS_URI_SOAP_ENC, qn, onjava.Person.class, bsr, bsr);
		//telling the call object to use this mapping
		call.setSOAPMappingRegistry(smr);
		//creating the parameters
        Vector v = new Vector();
        Parameter param1 = new Parameter("name", onjava.Person.class, nameObject, null);
        v.addElement(param1);
        call.setParams(v);
		//invoke the soap method
		System.out.println("invoking....");
		Response res = call.invoke(url, "");
      
		//if there is no fault in the method invocation, get the result
		if( res.generatedFault() ==false) {
			Parameter retValue = res.getReturnValue();
			Object value = retValue.getValue();
			System.out.println(value);
		} else  {
			System.out.println("The fault is: "+res.getFault().getFaultString());
		}
	}/*main()*/
}