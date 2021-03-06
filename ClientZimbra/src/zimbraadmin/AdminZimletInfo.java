
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for adminZimletInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="adminZimletInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zimletContext" type="{urn:zimbraAdmin}adminZimletContext" minOccurs="0"/>
 *         &lt;element name="zimlet" type="{urn:zimbraAdmin}adminZimletDesc" minOccurs="0"/>
 *         &lt;element name="zimletConfig" type="{urn:zimbraAdmin}adminZimletConfigInfo" minOccurs="0"/>
 *         &lt;any processContents='skip' namespace='##other'/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "adminZimletInfo", propOrder = {
    "zimletContext",
    "zimlet",
    "zimletConfig",
    "any"
})
public class AdminZimletInfo {

    protected AdminZimletContext zimletContext;
    protected AdminZimletDesc zimlet;
    protected AdminZimletConfigInfo zimletConfig;
    @XmlAnyElement
    protected Element any;

    /**
     * Gets the value of the zimletContext property.
     * 
     * @return
     *     possible object is
     *     {@link AdminZimletContext }
     *     
     */
    public AdminZimletContext getZimletContext() {
        return zimletContext;
    }

    /**
     * Sets the value of the zimletContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdminZimletContext }
     *     
     */
    public void setZimletContext(AdminZimletContext value) {
        this.zimletContext = value;
    }

    /**
     * Gets the value of the zimlet property.
     * 
     * @return
     *     possible object is
     *     {@link AdminZimletDesc }
     *     
     */
    public AdminZimletDesc getZimlet() {
        return zimlet;
    }

    /**
     * Sets the value of the zimlet property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdminZimletDesc }
     *     
     */
    public void setZimlet(AdminZimletDesc value) {
        this.zimlet = value;
    }

    /**
     * Gets the value of the zimletConfig property.
     * 
     * @return
     *     possible object is
     *     {@link AdminZimletConfigInfo }
     *     
     */
    public AdminZimletConfigInfo getZimletConfig() {
        return zimletConfig;
    }

    /**
     * Sets the value of the zimletConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdminZimletConfigInfo }
     *     
     */
    public void setZimletConfig(AdminZimletConfigInfo value) {
        this.zimletConfig = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     
     */
    public Element getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     
     */
    public void setAny(Element value) {
        this.any = value;
    }

}
