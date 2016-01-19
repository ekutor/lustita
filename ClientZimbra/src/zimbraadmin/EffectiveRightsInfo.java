
package zimbraadmin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for effectiveRightsInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="effectiveRightsInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="right" type="{urn:zimbraAdmin}rightWithName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="setAttrs" type="{urn:zimbraAdmin}effectiveAttrsInfo"/>
 *         &lt;element name="getAttrs" type="{urn:zimbraAdmin}effectiveAttrsInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "effectiveRightsInfo", propOrder = {
    "right",
    "setAttrs",
    "getAttrs"
})
@XmlSeeAlso({
    EffectiveRightsTargetInfo.class
})
public class EffectiveRightsInfo {

    protected List<RightWithName> right;
    @XmlElement(required = true)
    protected EffectiveAttrsInfo setAttrs;
    @XmlElement(required = true)
    protected EffectiveAttrsInfo getAttrs;

    /**
     * Gets the value of the right property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the right property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRight().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RightWithName }
     * 
     * 
     */
    public List<RightWithName> getRight() {
        if (right == null) {
            right = new ArrayList<RightWithName>();
        }
        return this.right;
    }

    /**
     * Gets the value of the setAttrs property.
     * 
     * @return
     *     possible object is
     *     {@link EffectiveAttrsInfo }
     *     
     */
    public EffectiveAttrsInfo getSetAttrs() {
        return setAttrs;
    }

    /**
     * Sets the value of the setAttrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link EffectiveAttrsInfo }
     *     
     */
    public void setSetAttrs(EffectiveAttrsInfo value) {
        this.setAttrs = value;
    }

    /**
     * Gets the value of the getAttrs property.
     * 
     * @return
     *     possible object is
     *     {@link EffectiveAttrsInfo }
     *     
     */
    public EffectiveAttrsInfo getGetAttrs() {
        return getAttrs;
    }

    /**
     * Sets the value of the getAttrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link EffectiveAttrsInfo }
     *     
     */
    public void setGetAttrs(EffectiveAttrsInfo value) {
        this.getAttrs = value;
    }

}
