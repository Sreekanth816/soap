package onjava;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

public class CalcClient {

  public static void main(String[] args) throws Exception {

    URL url = new URL ("http://localhost:6666/soap/servlet/rpcrouter");

    Integer p1 = new Integer(args[0]);
    Integer p2 = new Integer(args[1]);

    // Build the call.
    Call call = new Call();
    call.setTargetObjectURI("urn:onjavaserver");
    call.setMethodName("add");
    call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
    Vector params = new Vector();
    params.addElement(new Parameter("p1", Integer.class, p1, null));
    params.addElement(new Parameter("p2", Integer.class, p2, null));
    call.setParams (params);

    // make the call: note that the action URI is empty because the
    // XML-SOAP rpc router does not need this. This may change in the
    // future.
    Response resp = call.invoke(url, "" );

    // Check the response.
    if ( resp.generatedFault() ) {

      Fault fault = resp.getFault ();
      System.out.println("The call failed: ");
      System.out.println("Fault Code   = " + fault.getFaultCode());
      System.out.println("Fault String = " + fault.getFaultString());
    }
    else {

      Parameter result = resp.getReturnValue();
      System.out.println(result.getValue());
    }
	call.setMethodName("subtract");
    call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
	params = new Vector();
    params.addElement(new Parameter("p1", Integer.class, p1, null));
    params.addElement(new Parameter("p2", Integer.class, p2, null));
    call.setParams (params);

    // make the call: note that the action URI is empty because the
    // XML-SOAP rpc router does not need this. This may change in the
    // future.
    resp = call.invoke(url, "" );

    // Check the response.
    if ( resp.generatedFault() ) {

      Fault fault = resp.getFault ();
      System.out.println("The call failed: ");
      System.out.println("Fault Code   = " + fault.getFaultCode());
      System.out.println("Fault String = " + fault.getFaultString());
    }
    else {

      Parameter result = resp.getReturnValue();
      System.out.println(result.getValue());
    }
	String sriName = args[2];
	call.setTargetObjectURI("urn:onjavaserver1");
	call.setMethodName("hello");
    call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
	params = new Vector();
    params.addElement(new Parameter("name", String.class, sriName, null));
    //params.addElement(new Parameter("p2", Integer.class, p2, null));
    call.setParams (params);

    // make the call: note that the action URI is empty because the
    // XML-SOAP rpc router does not need this. This may change in the
    // future.
    resp = call.invoke(url, "" );

    // Check the response.
    if ( resp.generatedFault() ) {

      Fault fault = resp.getFault ();
      System.out.println("The call failed: ");
      System.out.println("Fault Code   = " + fault.getFaultCode());
      System.out.println("Fault String = " + fault.getFaultString());
    }
    else {

      Parameter result = resp.getReturnValue();
      System.out.println(result.getValue());
    }
  }
}
