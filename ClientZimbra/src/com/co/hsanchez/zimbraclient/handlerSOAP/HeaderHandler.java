package com.co.hsanchez.zimbraclient.handlerSOAP;

import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;


public class HeaderHandler implements SOAPHandler<SOAPMessageContext> {
	
public static String TOKEN = null;

public boolean handleMessage(SOAPMessageContext smc) {

    Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

    if (outboundProperty.booleanValue()) {

        SOAPMessage message = smc.getMessage();

        try {
        	smc.getMessage().setProperty(SOAPMessage.CHARACTER_SET_ENCODING,
                    "UTF-8");
            if(TOKEN != null){
            	SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
            	SOAPHeader header = envelope.getHeader();
            	if(header == null){
            		 header = envelope.addHeader();
            	}
                
	            SOAPElement security =
	                    header.addChildElement("context", "urn", "urn:zimbra");
	
	
	            SOAPElement authToken =
	                    security.addChildElement("authToken", "urn");
	            authToken.addTextNode(TOKEN);
            }
            //Print out the outbound SOAP message to System.out
//            message.writeTo(System.out);
//            System.out.println("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    } else {
        try {

            //This handler does nothing with the response from the Web Service so
            //we just print out the SOAP message.
            SOAPMessage message = smc.getMessage();
//            message.writeTo(System.out);
//            System.out.println("");

        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }


    return outboundProperty;

}

public Set getHeaders() {
    // The code below is added on order to invoke Spring secured WS.
    // Otherwise,
    // http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd
    // won't be recognised 
    final QName securityHeader = new QName(
            "urn:zimbra",
            "context", "urn");

    final HashSet headers = new HashSet();
    headers.add(securityHeader);

    return headers;
}

public boolean handleFault(SOAPMessageContext context) {
    //throw new UnsupportedOperationException("Not supported yet.");
    return true;
}

public void close(MessageContext context) {
//throw new UnsupportedOperationException("Not supported yet.");
}
}