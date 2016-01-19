
package zimbravoice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getVoiceInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getVoiceInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="audioType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="storeprincipal" type="{urn:zimbraVoice}storePrincipalSpec"/>
 *         &lt;element name="phone" type="{urn:zimbraVoice}voiceInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getVoiceInfoResponse", propOrder = {
    "audioType",
    "storeprincipal",
    "phone"
})
public class GetVoiceInfoResponse {

    @XmlElement(required = true)
    protected String audioType;
    @XmlElement(required = true)
    protected StorePrincipalSpec storeprincipal;
    protected List<VoiceInfo> phone;

    /**
     * Gets the value of the audioType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudioType() {
        return audioType;
    }

    /**
     * Sets the value of the audioType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudioType(String value) {
        this.audioType = value;
    }

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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VoiceInfo }
     * 
     * 
     */
    public List<VoiceInfo> getPhone() {
        if (phone == null) {
            phone = new ArrayList<VoiceInfo>();
        }
        return this.phone;
    }

}
