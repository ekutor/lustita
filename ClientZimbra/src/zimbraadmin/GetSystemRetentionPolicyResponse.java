
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import zimbramail.RetentionPolicy;


/**
 * <p>Java class for getSystemRetentionPolicyResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSystemRetentionPolicyResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:zimbraMail}retentionPolicy" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSystemRetentionPolicyResponse", propOrder = {
    "retentionPolicy"
})
public class GetSystemRetentionPolicyResponse {

    @XmlElement(namespace = "urn:zimbraMail")
    protected RetentionPolicy retentionPolicy;

    /**
     * Gets the value of the retentionPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link RetentionPolicy }
     *     
     */
    public RetentionPolicy getRetentionPolicy() {
        return retentionPolicy;
    }

    /**
     * Sets the value of the retentionPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetentionPolicy }
     *     
     */
    public void setRetentionPolicy(RetentionPolicy value) {
        this.retentionPolicy = value;
    }

}
