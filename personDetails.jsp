<%@page import="java.io.*, java.net.*, java.util.*, org.apache.soap.*, org.apache.soap.encoding.*, org.apache.soap.encoding.soapenc.*, org.apache.soap.util.xml.*, org.apache.soap.rpc.*" %>
<%
//out.println("Hello World!");
	try {
		URL url = new URL ("http://localhost:8080/soap1/servlet/rpcrouter");
        String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		onjava.Address addr = new onjava.Address();
		addr.setAddress(address);
        //SoapClient client = new SoapClient(urlString);
        //System.out.println("*** Assigned the url for the sopa router as: "+url);
        /** construct a Person object**/
        onjava.Person nameObject = new onjava.Person();
        nameObject.setName(name);
        nameObject.setAge(age);
		nameObject.setAddress(addr);
		Call call = new Call();
		call.setTargetObjectURI("urn:onjavaserver");
		call.setMethodName("getSalary");
		call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
		//create the type mapping registry
		SOAPMappingRegistry smr = new SOAPMappingRegistry();
		//new instance of the serializer class
		BeanSerializer bsr = new BeanSerializer();
		//create a qname object
		QName qn = new QName("urn:onjavaserver", "onjava.Person");
		QName qn1 = new QName("urn:onjavaserver", "onjava.Address");
		//map the type
		smr.mapTypes(Constants.NS_URI_SOAP_ENC, qn, onjava.Person.class, bsr, bsr);
		smr.mapTypes(Constants.NS_URI_SOAP_ENC, qn1, onjava.Address.class, bsr, bsr);
		//telling the call object to use this mapping
		call.setSOAPMappingRegistry(smr);
		//creating the parameters
        Vector v = new Vector();
        Parameter param1 = new Parameter("name", onjava.Person.class, nameObject, null);
        //Parameter param2 = new Parameter("address", onjava.Address.class, nameObject, null);
        v.addElement(param1);
        //v.addElement(param2);
        call.setParams(v);
		//invoke the soap method
		//System.out.println("invoking....");
		Response res = call.invoke(url, "");
      
		//if there is no fault in the method invocation, get the result
		if( res.generatedFault() == false) {
			Parameter retValue = res.getReturnValue();
			Object value = retValue.getValue();
			out.println("Salary: " + value);
			
			//Step 2: Second request
			call = new Call();
			call.setTargetObjectURI("urn:onjavaserver");
			call.setMethodName("getTaxAmount");
			call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
			//create the type mapping registry
			smr = new SOAPMappingRegistry();
			//new instance of the serializer class
			bsr = new BeanSerializer();
			
			v = new Vector();
	        param1 = new Parameter("salary", Integer.class, (Integer)value, null);
	        //Parameter param2 = new Parameter("address", onjava.Address.class, nameObject, null);
	        v.addElement(param1);
	        //v.addElement(param2);
	        call.setParams(v);
			//invoke the soap method
			//System.out.println("invoking....");
			res = call.invoke(url, "");
			
			if( res.generatedFault() == false) {
				retValue = res.getReturnValue();
				value = retValue.getValue();
				out.println("Tax Amount: " + value);
			} else {
				out.println("The fault for tax amount is: " + res.getFault().getFaultString());
			}
		} else  {
			out.println("The fault for salary is: " + res.getFault().getFaultString());
		}
	} catch(Exception e) {
		e.printStackTrace();
		out.println(e);
	}
%>
