
package com.zimbra.wsdl.zimbraservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "zcsService", targetNamespace = "http://www.zimbra.com/wsdl/ZimbraService.wsdl", wsdlLocation = "https://laumail.laumayer.com/service/wsdl/ZimbraService.wsdl")
public class ZcsService
    extends Service
{

    private final static URL ZCSSERVICE_WSDL_LOCATION;
    private final static WebServiceException ZCSSERVICE_EXCEPTION;
    private final static QName ZCSSERVICE_QNAME = new QName("http://www.zimbra.com/wsdl/ZimbraService.wsdl", "zcsService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://laumail.laumayer.com/service/wsdl/ZimbraService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ZCSSERVICE_WSDL_LOCATION = url;
        ZCSSERVICE_EXCEPTION = e;
    }

    public ZcsService() {
        super(__getWsdlLocation(), ZCSSERVICE_QNAME);
    }

    public ZcsService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ZCSSERVICE_QNAME, features);
    }

    public ZcsService(URL wsdlLocation) {
        super(wsdlLocation, ZCSSERVICE_QNAME);
    }

    public ZcsService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ZCSSERVICE_QNAME, features);
    }

    public ZcsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ZcsService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ZcsPortType
     */
    @WebEndpoint(name = "zcsServicePort")
    public ZcsPortType getZcsServicePort() {
        return super.getPort(new QName("http://www.zimbra.com/wsdl/ZimbraService.wsdl", "zcsServicePort"), ZcsPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ZcsPortType
     */
    @WebEndpoint(name = "zcsServicePort")
    public ZcsPortType getZcsServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.zimbra.com/wsdl/ZimbraService.wsdl", "zcsServicePort"), ZcsPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ZCSSERVICE_EXCEPTION!= null) {
            throw ZCSSERVICE_EXCEPTION;
        }
        return ZCSSERVICE_WSDL_LOCATION;
    }

}
