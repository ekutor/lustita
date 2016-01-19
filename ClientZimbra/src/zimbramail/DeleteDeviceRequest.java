
package zimbramail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import zimbra.Id;


/**
 * <p>Java class for deleteDeviceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteDeviceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="device" type="{urn:zimbra}id"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteDeviceRequest", propOrder = {
    "device"
})
public class DeleteDeviceRequest {

    @XmlElement(required = true)
    protected Id device;

    /**
     * Gets the value of the device property.
     * 
     * @return
     *     possible object is
     *     {@link Id }
     *     
     */
    public Id getDevice() {
        return device;
    }

    /**
     * Sets the value of the device property.
     * 
     * @param value
     *     allowed object is
     *     {@link Id }
     *     
     */
    public void setDevice(Id value) {
        this.device = value;
    }

}
