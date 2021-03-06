
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for conflictRecurrenceInstance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conflictRecurrenceInstance">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:zimbraMail}expandedRecurrenceInstance">
 *       &lt;sequence>
 *         &lt;element name="usr" type="{urn:zimbraMail}freeBusyUserStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="unusedCodeGenHelper" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conflictRecurrenceInstance", propOrder = {
    "usr"
})
public class ConflictRecurrenceInstance
    extends ExpandedRecurrenceInstance
{

    protected List<FreeBusyUserStatus> usr;
    @XmlAttribute(name = "unusedCodeGenHelper")
    protected String unusedCodeGenHelper;

    /**
     * Gets the value of the usr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FreeBusyUserStatus }
     * 
     * 
     */
    public List<FreeBusyUserStatus> getUsr() {
        if (usr == null) {
            usr = new ArrayList<FreeBusyUserStatus>();
        }
        return this.usr;
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
