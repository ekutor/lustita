
package zimbraadmin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inviteInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inviteInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tz" type="{urn:zimbraAdmin}calTZInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comp" type="{urn:zimbraAdmin}inviteComponent" minOccurs="0"/>
 *         &lt;element name="replies" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="reply" type="{urn:zimbraAdmin}calendarReply" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="unusedCodeGenHelper" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inviteInfo", propOrder = {
    "tz",
    "comp",
    "replies"
})
public class InviteInfo {

    protected List<CalTZInfo> tz;
    protected InviteComponent comp;
    protected InviteInfo.Replies replies;
    @XmlAttribute(name = "type", required = true)
    protected String type;

    /**
     * Gets the value of the tz property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tz property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTz().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalTZInfo }
     * 
     * 
     */
    public List<CalTZInfo> getTz() {
        if (tz == null) {
            tz = new ArrayList<CalTZInfo>();
        }
        return this.tz;
    }

    /**
     * Gets the value of the comp property.
     * 
     * @return
     *     possible object is
     *     {@link InviteComponent }
     *     
     */
    public InviteComponent getComp() {
        return comp;
    }

    /**
     * Sets the value of the comp property.
     * 
     * @param value
     *     allowed object is
     *     {@link InviteComponent }
     *     
     */
    public void setComp(InviteComponent value) {
        this.comp = value;
    }

    /**
     * Gets the value of the replies property.
     * 
     * @return
     *     possible object is
     *     {@link InviteInfo.Replies }
     *     
     */
    public InviteInfo.Replies getReplies() {
        return replies;
    }

    /**
     * Sets the value of the replies property.
     * 
     * @param value
     *     allowed object is
     *     {@link InviteInfo.Replies }
     *     
     */
    public void setReplies(InviteInfo.Replies value) {
        this.replies = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
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
     *         &lt;element name="reply" type="{urn:zimbraAdmin}calendarReply" maxOccurs="unbounded" minOccurs="0"/>
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

        protected List<CalendarReply> reply;
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
         * {@link CalendarReply }
         * 
         * 
         */
        public List<CalendarReply> getReply() {
            if (reply == null) {
                reply = new ArrayList<CalendarReply>();
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
