
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllDistributionListsRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllDistributionListsRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="domain" type="{urn:zimbraAdmin}domainSelector" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllDistributionListsRequest", propOrder = {
    "domain"
})
public class GetAllDistributionListsRequest {

    protected DomainSelector domain;

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link DomainSelector }
     *     
     */
    public DomainSelector getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainSelector }
     *     
     */
    public void setDomain(DomainSelector value) {
        this.domain = value;
    }

}
