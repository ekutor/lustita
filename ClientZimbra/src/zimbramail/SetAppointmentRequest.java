
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setAppointmentRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setAppointmentRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="default" type="{urn:zimbraMail}setCalendarItemInfo" minOccurs="0"/>
 *         &lt;element name="except" type="{urn:zimbraMail}setCalendarItemInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cancel" type="{urn:zimbraMail}setCalendarItemInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="replies" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reply" type="{urn:zimbraMail}calReply" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="unusedCodeGenHelper" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="f" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="t" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tn" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="l" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="noNextAlarm" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="nextAlarm" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setAppointmentRequest", propOrder = {
    "_default",
    "except",
    "cancel",
    "replies"
})
@XmlSeeAlso({
    SetTaskRequest.class
})
public class SetAppointmentRequest {

    @XmlElement(name = "default")
    protected SetCalendarItemInfo _default;
    protected List<SetCalendarItemInfo> except;
    protected List<SetCalendarItemInfo> cancel;
    protected SetAppointmentRequest.Replies replies;
    @XmlAttribute(name = "f")
    protected String f;
    @XmlAttribute(name = "t")
    protected String t;
    @XmlAttribute(name = "tn")
    protected String tn;
    @XmlAttribute(name = "l")
    protected String l;
    @XmlAttribute(name = "noNextAlarm")
    protected Boolean noNextAlarm;
    @XmlAttribute(name = "nextAlarm")
    protected Long nextAlarm;

    /**
     * Gets the value of the default property.
     * 
     * @return
     *     possible object is
     *     {@link SetCalendarItemInfo }
     *     
     */
    public SetCalendarItemInfo getDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetCalendarItemInfo }
     *     
     */
    public void setDefault(SetCalendarItemInfo value) {
        this._default = value;
    }

    /**
     * Gets the value of the except property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the except property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExcept().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SetCalendarItemInfo }
     * 
     * 
     */
    public List<SetCalendarItemInfo> getExcept() {
        if (except == null) {
            except = new ArrayList<SetCalendarItemInfo>();
        }
        return this.except;
    }

    /**
     * Gets the value of the cancel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cancel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCancel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SetCalendarItemInfo }
     * 
     * 
     */
    public List<SetCalendarItemInfo> getCancel() {
        if (cancel == null) {
            cancel = new ArrayList<SetCalendarItemInfo>();
        }
        return this.cancel;
    }

    /**
     * Gets the value of the replies property.
     * 
     * @return
     *     possible object is
     *     {@link SetAppointmentRequest.Replies }
     *     
     */
    public SetAppointmentRequest.Replies getReplies() {
        return replies;
    }

    /**
     * Sets the value of the replies property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetAppointmentRequest.Replies }
     *     
     */
    public void setReplies(SetAppointmentRequest.Replies value) {
        this.replies = value;
    }

    /**
     * Gets the value of the f property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getF() {
        return f;
    }

    /**
     * Sets the value of the f property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setF(String value) {
        this.f = value;
    }

    /**
     * Gets the value of the t property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getT() {
        return t;
    }

    /**
     * Sets the value of the t property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setT(String value) {
        this.t = value;
    }

    /**
     * Gets the value of the tn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTn() {
        return tn;
    }

    /**
     * Sets the value of the tn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTn(String value) {
        this.tn = value;
    }

    /**
     * Gets the value of the l property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getL() {
        return l;
    }

    /**
     * Sets the value of the l property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setL(String value) {
        this.l = value;
    }

    /**
     * Gets the value of the noNextAlarm property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoNextAlarm() {
        return noNextAlarm;
    }

    /**
     * Sets the value of the noNextAlarm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoNextAlarm(Boolean value) {
        this.noNextAlarm = value;
    }

    /**
     * Gets the value of the nextAlarm property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNextAlarm() {
        return nextAlarm;
    }

    /**
     * Sets the value of the nextAlarm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNextAlarm(Long value) {
        this.nextAlarm = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="reply" type="{urn:zimbraMail}calReply" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *       &lt;attribute name="unusedCodeGenHelper" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "reply"
    })
    public static class Replies {

        protected List<CalReply> reply;
        @XmlAttribute(name = "unusedCodeGenHelper")
        protected String unusedCodeGenHelper;

        /**
         * Gets the value of the reply property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reply property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReply().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CalReply }
         * 
         * 
         */
        public List<CalReply> getReply() {
            if (reply == null) {
                reply = new ArrayList<CalReply>();
            }
            return this.reply;
        }

        /**
         * Gets the value of the unusedCodeGenHelper property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUnusedCodeGenHelper() {
            return unusedCodeGenHelper;
        }

        /**
         * Sets the value of the unusedCodeGenHelper property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUnusedCodeGenHelper(String value) {
            this.unusedCodeGenHelper = value;
        }

    }

}
