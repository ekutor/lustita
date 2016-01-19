
package zimbraaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import zimbra.AccountSelector;
import zimbra.GranteeChooser;


/**
 * <p>Java class for getShareInfoRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getShareInfoRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="grantee" type="{urn:zimbra}granteeChooser" minOccurs="0"/>
 *         &lt;element name="owner" type="{urn:zimbra}accountSelector" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="internal" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="includeSelf" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getShareInfoRequest", propOrder = {
    "grantee",
    "owner"
})
public class GetShareInfoRequest {

    protected GranteeChooser grantee;
    protected AccountSelector owner;
    @XmlAttribute(name = "internal")
    protected Boolean internal;
    @XmlAttribute(name = "includeSelf")
    protected Boolean includeSelf;

    /**
     * Gets the value of the grantee property.
     * 
     * @return
     *     possible object is
     *     {@link GranteeChooser }
     *     
     */
    public GranteeChooser getGrantee() {
        return grantee;
    }

    /**
     * Sets the value of the grantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link GranteeChooser }
     *     
     */
    public void setGrantee(GranteeChooser value) {
        this.grantee = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link AccountSelector }
     *     
     */
    public AccountSelector getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountSelector }
     *     
     */
    public void setOwner(AccountSelector value) {
        this.owner = value;
    }

    /**
     * Gets the value of the internal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInternal() {
        return internal;
    }

    /**
     * Sets the value of the internal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInternal(Boolean value) {
        this.internal = value;
    }

    /**
     * Gets the value of the includeSelf property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeSelf() {
        return includeSelf;
    }

    /**
     * Sets the value of the includeSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeSelf(Boolean value) {
        this.includeSelf = value;
    }

}
