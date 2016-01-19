
package zimbravoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getVoiceFeaturesRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getVoiceFeaturesRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="storeprincipal" type="{urn:zimbraVoice}storePrincipalSpec" minOccurs="0"/>
 *         &lt;element name="phone" type="{urn:zimbraVoice}phoneVoiceFeaturesSpec" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getVoiceFeaturesRequest", propOrder = {
    "storeprincipal",
    "phone"
})
public class GetVoiceFeaturesRequest {

    protected StorePrincipalSpec storeprincipal;
    protected PhoneVoiceFeaturesSpec phone;

    /**
     * Gets the value of the storeprincipal property.
     * 
     * @return
     *     possible object is
     *     {@link StorePrincipalSpec }
     *     
     */
    public StorePrincipalSpec getStoreprincipal() {
        return storeprincipal;
    }

    /**
     * Sets the value of the storeprincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link StorePrincipalSpec }
     *     
     */
    public void setStoreprincipal(StorePrincipalSpec value) {
        this.storeprincipal = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneVoiceFeaturesSpec }
     *     
     */
    public PhoneVoiceFeaturesSpec getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneVoiceFeaturesSpec }
     *     
     */
    public void setPhone(PhoneVoiceFeaturesSpec value) {
        this.phone = value;
    }

}
