
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for expandRecurRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="expandRecurRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tz" type="{urn:zimbraMail}calTZInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="comp" type="{urn:zimbraMail}expandedRecurrenceInvite"/>
 *           &lt;element name="except" type="{urn:zimbraMail}expandedRecurrenceException"/>
 *           &lt;element name="cancel" type="{urn:zimbraMail}expandedRecurrenceCancel"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="s" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="e" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expandRecurRequest", propOrder = {
    "tz",
    "compOrExceptOrCancel"
})
public class ExpandRecurRequest {

    protected List<CalTZInfo> tz;
    @XmlElements({
        @XmlElement(name = "comp", type = ExpandedRecurrenceInvite.class),
        @XmlElement(name = "except", type = ExpandedRecurrenceException.class),
        @XmlElement(name = "cancel", type = ExpandedRecurrenceCancel.class)
    })
    protected List<ExpandedRecurrenceComponent> compOrExceptOrCancel;
    @XmlAttribute(name = "s", required = true)
    protected long s;
    @XmlAttribute(name = "e", required = true)
    protected long e;

    /**
     * Gets the value of the tz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalTZInfo }
     * 
     * 
     */
    public List<CalTZInfo> getTz() {
        if (tz == null) {
            tz = new ArrayList<CalTZInfo>();
        }
        return this.tz;
    }

    /**
     * Gets the value of the compOrExceptOrCancel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compOrExceptOrCancel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompOrExceptOrCancel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExpandedRecurrenceInvite }
     * {@link ExpandedRecurrenceException }
     * {@link ExpandedRecurrenceCancel }
     * 
     * 
     */
    public List<ExpandedRecurrenceComponent> getCompOrExceptOrCancel() {
        if (compOrExceptOrCancel == null) {
            compOrExceptOrCancel = new ArrayList<ExpandedRecurrenceComponent>();
        }
        return this.compOrExceptOrCancel;
    }

    /**
     * Gets the value of the s property.
     * 
     */
    public long getS() {
        return s;
    }

    /**
     * Sets the value of the s property.
     * 
     */
    public void setS(long value) {
        this.s = value;
    }

    /**
     * Gets the value of the e property.
     * 
     */
    public long getE() {
        return e;
    }

    /**
     * Sets the value of the e property.
     * 
     */
    public void setE(long value) {
        this.e = value;
    }

}
