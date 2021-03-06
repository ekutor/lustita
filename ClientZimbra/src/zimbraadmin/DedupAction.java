
package zimbraadmin;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dedupAction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="dedupAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="start"/>
 *     &lt;enumeration value="status"/>
 *     &lt;enumeration value="stop"/>
 *     &lt;enumeration value="reset"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dedupAction")
@XmlEnum
public enum DedupAction {

    @XmlEnumValue("start")
    START("start"),
    @XmlEnumValue("status")
    STATUS("status"),
    @XmlEnumValue("stop")
    STOP("stop"),
    @XmlEnumValue("reset")
    RESET("reset");
    private final String value;

    DedupAction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DedupAction fromValue(String v) {
        for (DedupAction c: DedupAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
