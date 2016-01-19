
package zimbraaccount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import zimbra.DistributionListSelector;


/**
 * <p>Java class for distributionListActionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="distributionListActionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:zimbraAccount}attrsImpl">
 *       &lt;sequence>
 *         &lt;element name="dl" type="{urn:zimbra}distributionListSelector"/>
 *         &lt;element name="action" type="{urn:zimbraAccount}distributionListAction"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "distributionListActionRequest", propOrder = {
    "dl",
    "action"
})
public class DistributionListActionRequest
    extends AttrsImpl
{

    @XmlElement(required = true)
    protected DistributionListSelector dl;
    @XmlElement(required = true)
    protected DistributionListAction action;

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
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionListAction }
     *     
     */
    public DistributionListAction getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionListAction }
     *     
     */
    public void setAction(DistributionListAction value) {
        this.action = value;
    }

}
