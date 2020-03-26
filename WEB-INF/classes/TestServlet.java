import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.util.Vector;
import org.apache.soap.*;
import org.apache.soap.rpc.*;

public class TestServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
		PrintWriter out = response.getWriter();
		Integer p1 = new Integer(request.getParameter("p1").trim());
		Integer p2 = new Integer(request.getParameter("p2").trim());
		URL url = new URL ("http://localhost:6666/soap/servlet/rpcrouter");
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
		Response resp = null;
		try {
			resp = call.invoke(url, "" );
		} catch(Exception e) {
			e.printStackTrace();
		}

	    // Check the response.
		if ( resp.generatedFault() ) {
			Fault fault = resp.getFault ();
			out.println("The call failed: ");
			out.println("Fault Code   = " + fault.getFaultCode());
			out.println("Fault String = " + fault.getFaultString());
		} else {
			Parameter result = resp.getReturnValue();
			out.println("Sum of : " + p1.intValue() + " and " + p2.intValue() + " is = " + result.getValue());
		}
	}
}