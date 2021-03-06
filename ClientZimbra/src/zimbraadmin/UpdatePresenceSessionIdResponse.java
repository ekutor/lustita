
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import zimbra.KeyValuePair;


/**
 * <p>Java class for updatePresenceSessionIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePresenceSessionIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="a" type="{urn:zimbra}keyValuePair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePresenceSessionIdResponse", propOrder = {
    "a"
})
public class UpdatePresenceSessionIdResponse {

    protected KeyValuePair a;

    /**
     * Gets the value of the a property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValuePair }
     *     
     */
    public KeyValuePair getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValuePair }
     *     
     */
    public void setA(KeyValuePair value) {
        this.a = value;
    }

}
