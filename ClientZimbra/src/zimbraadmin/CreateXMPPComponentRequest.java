
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createXMPPComponentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createXMPPComponentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xmppcomponent" type="{urn:zimbraAdmin}xmppComponentSpec"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createXMPPComponentRequest", propOrder = {
    "xmppcomponent"
})
public class CreateXMPPComponentRequest {

    @XmlElement(required = true)
    protected XmppComponentSpec xmppcomponent;

    /**
     * Gets the value of the xmppcomponent property.
     * 
     * @return
     *     possible object is
     *     {@link XmppComponentSpec }
     *     
     */
    public XmppComponentSpec getXmppcomponent() {
        return xmppcomponent;
    }

    /**
     * Sets the value of the xmppcomponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmppComponentSpec }
     *     
     */
    public void setXmppcomponent(XmppComponentSpec value) {
        this.xmppcomponent = value;
    }

}
