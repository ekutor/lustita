
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for msg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="msg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="header" type="{urn:zimbraMail}header" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mp" type="{urn:zimbraMail}mimePartInfo" minOccurs="0"/>
 *         &lt;element name="attach" type="{urn:zimbraMail}attachmentsInfo" minOccurs="0"/>
 *         &lt;element name="inv" type="{urn:zimbraMail}invitationInfo" minOccurs="0"/>
 *         &lt;element name="e" type="{urn:zimbraMail}emailAddrInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tz" type="{urn:zimbraMail}calTZInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;any processContents='skip' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="aid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="origid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idnt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="su" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="irt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="l" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="f" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "msg", propOrder = {
    "content",
    "header",
    "mp",
    "attach",
    "inv",
    "e",
    "tz",
    "fr",
    "any"
})
@XmlSeeAlso({
    MsgToSend.class,
    SaveDraftMsg.class
})
public class Msg {

    protected String content;
    protected List<Header> header;
    protected MimePartInfo mp;
    protected AttachmentsInfo attach;
    protected InvitationInfo inv;
    protected List<EmailAddrInfo> e;
    protected List<CalTZInfo> tz;
    protected String fr;
    @XmlAnyElement
    protected List<Element> any;
    @XmlAttribute(name = "aid")
    protected String aid;
    @XmlAttribute(name = "origid")
    protected String origid;
    @XmlAttribute(name = "rt")
    protected String rt;
    @XmlAttribute(name = "idnt")
    protected String idnt;
    @XmlAttribute(name = "su")
    protected String su;
    @XmlAttribute(name = "irt")
    protected String irt;
    @XmlAttribute(name = "l")
    protected String l;
    @XmlAttribute(name = "f")
    protected String f;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the header property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the header property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Header }
     * 
     * 
     */
    public List<Header> getHeader() {
        if (header == null) {
            header = new ArrayList<Header>();
        }
        return this.header;
    }

    /**
     * Gets the value of the mp property.
     * 
     * @return
     *     possible object is
     *     {@link MimePartInfo }
     *     
     */
    public MimePartInfo getMp() {
        return mp;
    }

    /**
     * Sets the value of the mp property.
     * 
     * @param value
     *     allowed object is
     *     {@link MimePartInfo }
     *     
     */
    public void setMp(MimePartInfo value) {
        this.mp = value;
    }

    /**
     * Gets the value of the attach property.
     * 
     * @return
     *     possible object is
     *     {@link AttachmentsInfo }
     *     
     */
    public AttachmentsInfo getAttach() {
        return attach;
    }

    /**
     * Sets the value of the attach property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttachmentsInfo }
     *     
     */
    public void setAttach(AttachmentsInfo value) {
        this.attach = value;
    }

    /**
     * Gets the value of the inv property.
     * 
     * @return
     *     possible object is
     *     {@link InvitationInfo }
     *     
     */
    public InvitationInfo getInv() {
        return inv;
    }

    /**
     * Sets the value of the inv property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvitationInfo }
     *     
     */
    public void setInv(InvitationInfo value) {
        this.inv = value;
    }

    /**
     * Gets the value of the e property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the e property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailAddrInfo }
     * 
     * 
     */
    public List<EmailAddrInfo> getE() {
        if (e == null) {
            e = new ArrayList<EmailAddrInfo>();
        }
        return this.e;
    }

    public void setHeader(List<Header> header) {
		this.header = header;
	}

	public void setE(List<EmailAddrInfo> e) {
		this.e = e;
	}

	public void setTz(List<CalTZInfo> tz) {
		this.tz = tz;
	}

	public void setAny(List<Element> any) {
		this.any = any;
	}

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
     * Gets the value of the fr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFr() {
        return fr;
    }

    /**
     * Sets the value of the fr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFr(String value) {
        this.fr = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * 
     * 
     */
    public List<Element> getAny() {
        if (any == null) {
            any = new ArrayList<Element>();
        }
        return this.any;
    }

    /**
     * Gets the value of the aid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAid() {
        return aid;
    }

    /**
     * Sets the value of the aid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAid(String value) {
        this.aid = value;
    }

    /**
     * Gets the value of the origid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigid() {
        return origid;
    }

    /**
     * Sets the value of the origid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigid(String value) {
        this.origid = value;
    }

    /**
     * Gets the value of the rt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRt() {
        return rt;
    }

    /**
     * Sets the value of the rt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRt(String value) {
        this.rt = value;
    }

    /**
     * Gets the value of the idnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdnt() {
        return idnt;
    }

    /**
     * Sets the value of the idnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdnt(String value) {
        this.idnt = value;
    }

    /**
     * Gets the value of the su property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSu() {
        return su;
    }

    /**
     * Sets the value of the su property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSu(String value) {
        this.su = value;
    }

    /**
     * Gets the value of the irt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIrt() {
        return irt;
    }

    /**
     * Sets the value of the irt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIrt(String value) {
        this.irt = value;
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

}
