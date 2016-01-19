
package zimbra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import zimbraadmin.GetAccountRequest;
import zimbraadmin.GetCalendarResourceRequest;
import zimbraadmin.GetCosRequest;
import zimbraadmin.GetDomainRequest;
import zimbraadmin.GetServerRequest;
import zimbraadmin.GetUCServiceRequest;
import zimbraadmin.GetXMPPComponentRequest;
import zimbraadmin.GetZimletRequest;
import zimbraadmin.SearchAutoProvDirectoryRequest;
import zimbraadmin.SearchDirectoryRequest;


/**
 * <p>Java class for attributeSelectorImpl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="attributeSelectorImpl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="attrs" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attributeSelectorImpl")
@XmlSeeAlso({
    zimbraaccount.SearchCalendarResourcesRequest.class,
    GetCosRequest.class,
    SearchDirectoryRequest.class,
    GetServerRequest.class,
    GetUCServiceRequest.class,
    GetXMPPComponentRequest.class,
    GetCalendarResourceRequest.class,
    GetAccountRequest.class,
    SearchAutoProvDirectoryRequest.class,
    GetDomainRequest.class,
    GetZimletRequest.class,
    zimbraadmin.SearchCalendarResourcesRequest.class
})
public abstract class AttributeSelectorImpl {

    @XmlAttribute(name = "attrs")
    protected String attrs;

    /**
     * Gets the value of the attrs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttrs() {
        return attrs;
    }

    /**
     * Sets the value of the attrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttrs(String value) {
        this.attrs = value;
    }

}
