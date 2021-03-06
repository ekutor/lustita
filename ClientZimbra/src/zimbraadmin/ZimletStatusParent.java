
package zimbraadmin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zimletStatusParent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zimletStatusParent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zimlet" type="{urn:zimbraAdmin}zimletStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="unusedCodeGenHelper" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zimletStatusParent", propOrder = {
    "zimlet"
})
public class ZimletStatusParent {

    protected List<ZimletStatus> zimlet;
    @XmlAttribute(name = "unusedCodeGenHelper")
    protected String unusedCodeGenHelper;

    /**
     * Gets the value of the zimlet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zimlet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZimlet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZimletStatus }
     * 
     * 
     */
    public List<ZimletStatus> getZimlet() {
        if (zimlet == null) {
            zimlet = new ArrayList<ZimletStatus>();
        }
        return this.zimlet;
    }

    /**
     * Gets the value of the unusedCodeGenHelper property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnusedCodeGenHelper() {
        return unusedCodeGenHelper;
    }

    /**
     * Sets the value of the unusedCodeGenHelper property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnusedCodeGenHelper(String value) {
        this.unusedCodeGenHelper = value;
    }

}
