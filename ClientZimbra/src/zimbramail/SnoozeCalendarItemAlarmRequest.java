
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for snoozeCalendarItemAlarmRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="snoozeCalendarItemAlarmRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="appt" type="{urn:zimbraMail}snoozeAppointmentAlarm"/>
 *           &lt;element name="task" type="{urn:zimbraMail}snoozeTaskAlarm"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "snoozeCalendarItemAlarmRequest", propOrder = {
    "apptOrTask"
})
public class SnoozeCalendarItemAlarmRequest {

    @XmlElements({
        @XmlElement(name = "appt", type = SnoozeAppointmentAlarm.class),
        @XmlElement(name = "task", type = SnoozeTaskAlarm.class)
    })
    protected List<SnoozeAlarm> apptOrTask;

    /**
     * Gets the value of the apptOrTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apptOrTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApptOrTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SnoozeAppointmentAlarm }
     * {@link SnoozeTaskAlarm }
     * 
     * 
     */
    public List<SnoozeAlarm> getApptOrTask() {
        if (apptOrTask == null) {
            apptOrTask = new ArrayList<SnoozeAlarm>();
        }
        return this.apptOrTask;
    }

}
