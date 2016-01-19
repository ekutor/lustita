
package zimbra;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for galSearchType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="galSearchType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="all"/>
 *     &lt;enumeration value="account"/>
 *     &lt;enumeration value="resource"/>
 *     &lt;enumeration value="group"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "galSearchType")
@XmlEnum
public enum GalSearchType {

    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("account")
    ACCOUNT("account"),
    @XmlEnumValue("resource")
    RESOURCE("resource"),
    @XmlEnumValue("group")
    GROUP("group");
    private final String value;

    GalSearchType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GalSearchType fromValue(String v) {
        for (GalSearchType c: GalSearchType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
