
package zimbravoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for voiceMsgActionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="voiceMsgActionRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="storeprincipal" type="{urn:zimbraVoice}storePrincipalSpec" minOccurs="0"/>
 *         &lt;element name="action" type="{urn:zimbraVoice}voiceMsgActionSpec"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "voiceMsgActionRequest", propOrder = {
    "storeprincipal",
    "action"
})
public class VoiceMsgActionRequest {

    protected StorePrincipalSpec storeprincipal;
    @XmlElement(required = true)
    protected VoiceMsgActionSpec action;

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
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link VoiceMsgActionSpec }
     *     
     */
    public VoiceMsgActionSpec getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoiceMsgActionSpec }
     *     
     */
    public void setAction(VoiceMsgActionSpec value) {
        this.action = value;
    }

}
