
package zimbraaccount;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for distributionListSubscribeStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="distributionListSubscribeStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="subscribed"/>
 *     &lt;enumeration value="unsubscribed"/>
 *     &lt;enumeration value="awaiting_approval"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "distributionListSubscribeStatus")
@XmlEnum
public enum DistributionListSubscribeStatus {

    @XmlEnumValue("subscribed")
    SUBSCRIBED("subscribed"),
    @XmlEnumValue("unsubscribed")
    UNSUBSCRIBED("unsubscribed"),
    @XmlEnumValue("awaiting_approval")
    AWAITING_APPROVAL("awaiting_approval");
    private final String value;

    DistributionListSubscribeStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DistributionListSubscribeStatus fromValue(String v) {
        for (DistributionListSubscribeStatus c: DistributionListSubscribeStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
