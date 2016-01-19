
package zimbravoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import zimbra.TrueOrFalse;


/**
 * <p>Java class for callerListEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="callerListEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="pn" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="a" use="required" type="{urn:zimbra}trueOrFalse" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callerListEntry")
public class CallerListEntry {

    @XmlAttribute(name = "pn", required = true)
    protected String pn;
    @XmlAttribute(name = "a", required = true)
    protected TrueOrFalse a;

    /**
     * Gets the value of the pn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPn() {
        return pn;
    }

    /**
     * Sets the value of the pn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPn(String value) {
        this.pn = value;
    }

    /**
     * Gets the value of the a property.
     * 
     * @return
     *     possible object is
     *     {@link TrueOrFalse }
     *     
     */
    public TrueOrFalse getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueOrFalse }
     *     
     */
    public void setA(TrueOrFalse value) {
        this.a = value;
    }

}
