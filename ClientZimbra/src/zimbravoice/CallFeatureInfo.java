
package zimbravoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import zimbra.TrueOrFalse;


/**
 * <p>Java class for callFeatureInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="callFeatureInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="s" use="required" type="{urn:zimbra}trueOrFalse" />
 *       &lt;attribute name="a" use="required" type="{urn:zimbra}trueOrFalse" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callFeatureInfo")
@XmlSeeAlso({
    AnonCallRejectionFeature.class,
    CallerIdBlockingFeature.class,
    FeatureWithCallerList.class,
    VoiceMailPrefsFeature.class,
    CallForwardBusyLineFeature.class,
    CallWaitingFeature.class,
    CallForwardNoAnswerFeature.class,
    CallForwardFeature.class
})
public abstract class CallFeatureInfo {

    @XmlAttribute(name = "s", required = true)
    protected TrueOrFalse s;
    @XmlAttribute(name = "a", required = true)
    protected TrueOrFalse a;

    /**
     * Gets the value of the s property.
     * 
     * @return
     *     possible object is
     *     {@link TrueOrFalse }
     *     
     */
    public TrueOrFalse getS() {
        return s;
    }

    /**
     * Sets the value of the s property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueOrFalse }
     *     
     */
    public void setS(TrueOrFalse value) {
        this.s = value;
    }

    /**
     * Gets the value of the a property.
     * 
     * @return
     *     possible object is
     *     {@link TrueOrFalse }
     *     
     */
    public TrueOrFalse getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueOrFalse }
     *     
     */
    public void setA(TrueOrFalse value) {
        this.a = value;
    }

}
