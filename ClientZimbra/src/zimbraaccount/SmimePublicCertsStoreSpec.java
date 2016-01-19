
package zimbraaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import zimbra.SourceLookupOpt;
import zimbra.StoreLookupOpt;


/**
 * <p>Java class for smimePublicCertsStoreSpec complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="smimePublicCertsStoreSpec">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="storeLookupOpt" type="{urn:zimbra}storeLookupOpt" />
 *       &lt;attribute name="sourceLookupOpt" type="{urn:zimbra}sourceLookupOpt" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smimePublicCertsStoreSpec", propOrder = {
    "value"
})
public class SmimePublicCertsStoreSpec {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "storeLookupOpt")
    protected StoreLookupOpt storeLookupOpt;
    @XmlAttribute(name = "sourceLookupOpt")
    protected SourceLookupOpt sourceLookupOpt;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the storeLookupOpt property.
     * 
     * @return
     *     possible object is
     *     {@link StoreLookupOpt }
     *     
     */
    public StoreLookupOpt getStoreLookupOpt() {
        return storeLookupOpt;
    }

    /**
     * Sets the value of the storeLookupOpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreLookupOpt }
     *     
     */
    public void setStoreLookupOpt(StoreLookupOpt value) {
        this.storeLookupOpt = value;
    }

    /**
     * Gets the value of the sourceLookupOpt property.
     * 
     * @return
     *     possible object is
     *     {@link SourceLookupOpt }
     *     
     */
    public SourceLookupOpt getSourceLookupOpt() {
        return sourceLookupOpt;
    }

    /**
     * Sets the value of the sourceLookupOpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceLookupOpt }
     *     
     */
    public void setSourceLookupOpt(SourceLookupOpt value) {
        this.sourceLookupOpt = value;
    }

}
