
package zimbraadmin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createCalendarResourceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createCalendarResourceResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calresource" type="{urn:zimbraAdmin}calendarResourceInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createCalendarResourceResponse", propOrder = {
    "calresource"
})
public class CreateCalendarResourceResponse {

    @XmlElement(required = true)
    protected CalendarResourceInfo calresource;

    /**
     * Gets the value of the calresource property.
     * 
     * @return
     *     possible object is
     *     {@link CalendarResourceInfo }
     *     
     */
    public CalendarResourceInfo getCalresource() {
        return calresource;
    }

    /**
     * Sets the value of the calresource property.
     * 
     * @param value
     *     allowed object is
     *     {@link CalendarResourceInfo }
     *     
     */
    public void setCalresource(CalendarResourceInfo value) {
        this.calresource = value;
    }

}
