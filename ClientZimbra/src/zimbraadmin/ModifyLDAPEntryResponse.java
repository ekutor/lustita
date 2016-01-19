
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for modifyLDAPEntryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="modifyLDAPEntryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LDAPEntry" type="{urn:zimbraAdmin}ldapEntryInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modifyLDAPEntryResponse", propOrder = {
    "ldapEntry"
})
public class ModifyLDAPEntryResponse {

    @XmlElement(name = "LDAPEntry", required = true)
    protected LdapEntryInfo ldapEntry;

    /**
     * Gets the value of the ldapEntry property.
     * 
     * @return
     *     possible object is
     *     {@link LdapEntryInfo }
     *     
     */
    public LdapEntryInfo getLDAPEntry() {
        return ldapEntry;
    }

    /**
     * Sets the value of the ldapEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link LdapEntryInfo }
     *     
     */
    public void setLDAPEntry(LdapEntryInfo value) {
        this.ldapEntry = value;
    }

}
