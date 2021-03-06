
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createZimletResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createZimletResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zimlet" type="{urn:zimbraAdmin}zimletInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createZimletResponse", propOrder = {
    "zimlet"
})
public class CreateZimletResponse {

    @XmlElement(required = true)
    protected ZimletInfo zimlet;

    /**
     * Gets the value of the zimlet property.
     * 
     * @return
     *     possible object is
     *     {@link ZimletInfo }
     *     
     */
    public ZimletInfo getZimlet() {
        return zimlet;
    }

    /**
     * Sets the value of the zimlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZimletInfo }
     *     
     */
    public void setZimlet(ZimletInfo value) {
        this.zimlet = value;
    }

}
