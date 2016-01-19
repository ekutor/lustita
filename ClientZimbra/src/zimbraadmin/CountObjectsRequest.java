
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for countObjectsRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="countObjectsRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="domain" type="{urn:zimbraAdmin}domainSelector" minOccurs="0"/>
 *         &lt;element name="ucservice" type="{urn:zimbraAdmin}ucServiceSelector" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{urn:zimbraAdmin}countObjectsType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countObjectsRequest", propOrder = {
    "domain",
    "ucservice"
})
public class CountObjectsRequest {

    protected DomainSelector domain;
    protected UcServiceSelector ucservice;
    @XmlAttribute(name = "type", required = true)
    protected CountObjectsType type;

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

    /**
     * Gets the value of the ucservice property.
     * 
     * @return
     *     possible object is
     *     {@link UcServiceSelector }
     *     
     */
    public UcServiceSelector getUcservice() {
        return ucservice;
    }

    /**
     * Sets the value of the ucservice property.
     * 
     * @param value
     *     allowed object is
     *     {@link UcServiceSelector }
     *     
     */
    public void setUcservice(UcServiceSelector value) {
        this.ucservice = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CountObjectsType }
     *     
     */
    public CountObjectsType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountObjectsType }
     *     
     */
    public void setType(CountObjectsType value) {
        this.type = value;
    }

}
