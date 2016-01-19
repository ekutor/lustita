
package zimbrarepl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for replicationStatusResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="replicationStatusResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="masterStatus" type="{urn:zimbraRepl}replicationMasterStatus" minOccurs="0"/>
 *         &lt;element name="slaveStatus" type="{urn:zimbraRepl}replicationSlaveStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="replicationEnabled" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="currentRole" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="originalRole" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "replicationStatusResponse", propOrder = {
    "masterStatus",
    "slaveStatus"
})
public class ReplicationStatusResponse {

    protected ReplicationMasterStatus masterStatus;
    protected ReplicationSlaveStatus slaveStatus;
    @XmlAttribute(name = "replicationEnabled", required = true)
    protected boolean replicationEnabled;
    @XmlAttribute(name = "currentRole")
    protected String currentRole;
    @XmlAttribute(name = "originalRole")
    protected String originalRole;

    /**
     * Gets the value of the masterStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ReplicationMasterStatus }
     *     
     */
    public ReplicationMasterStatus getMasterStatus() {
        return masterStatus;
    }

    /**
     * Sets the value of the masterStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReplicationMasterStatus }
     *     
     */
    public void setMasterStatus(ReplicationMasterStatus value) {
        this.masterStatus = value;
    }

    /**
     * Gets the value of the slaveStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ReplicationSlaveStatus }
     *     
     */
    public ReplicationSlaveStatus getSlaveStatus() {
        return slaveStatus;
    }

    /**
     * Sets the value of the slaveStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReplicationSlaveStatus }
     *     
     */
    public void setSlaveStatus(ReplicationSlaveStatus value) {
        this.slaveStatus = value;
    }

    /**
     * Gets the value of the replicationEnabled property.
     * 
     */
    public boolean isReplicationEnabled() {
        return replicationEnabled;
    }

    /**
     * Sets the value of the replicationEnabled property.
     * 
     */
    public void setReplicationEnabled(boolean value) {
        this.replicationEnabled = value;
    }

    /**
     * Gets the value of the currentRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentRole() {
        return currentRole;
    }

    /**
     * Sets the value of the currentRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentRole(String value) {
        this.currentRole = value;
    }

    /**
     * Gets the value of the originalRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalRole() {
        return originalRole;
    }

    /**
     * Sets the value of the originalRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalRole(String value) {
        this.originalRole = value;
    }

}
