
package zimbraaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import zimbra.DistributionListSelector;


/**
 * <p>Java class for subscribeDistributionListRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subscribeDistributionListRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dl" type="{urn:zimbra}distributionListSelector"/>
 *       &lt;/sequence>
 *       &lt;attribute name="op" use="required" type="{urn:zimbraAccount}distributionListSubscribeOp" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subscribeDistributionListRequest", propOrder = {
    "dl"
})
public class SubscribeDistributionListRequest {

    @XmlElement(required = true)
    protected DistributionListSelector dl;
    @XmlAttribute(name = "op", required = true)
    protected DistributionListSubscribeOp op;

    /**
     * Gets the value of the dl property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionListSelector }
     *     
     */
    public DistributionListSelector getDl() {
        return dl;
    }

    /**
     * Sets the value of the dl property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionListSelector }
     *     
     */
    public void setDl(DistributionListSelector value) {
        this.dl = value;
    }

    /**
     * Gets the value of the op property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionListSubscribeOp }
     *     
     */
    public DistributionListSubscribeOp getOp() {
        return op;
    }

    /**
     * Sets the value of the op property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionListSubscribeOp }
     *     
     */
    public void setOp(DistributionListSubscribeOp value) {
        this.op = value;
    }

}
