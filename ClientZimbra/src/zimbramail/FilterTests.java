
package zimbramail;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for filterTests complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filterTests">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="addressBookTest" type="{urn:zimbraMail}addressBookTest"/>
 *           &lt;element name="addressTest" type="{urn:zimbraMail}addressTest"/>
 *           &lt;element name="attachmentTest" type="{urn:zimbraMail}attachmentTest"/>
 *           &lt;element name="bodyTest" type="{urn:zimbraMail}bodyTest"/>
 *           &lt;element name="bulkTest" type="{urn:zimbraMail}bulkTest"/>
 *           &lt;element name="contactRankingTest" type="{urn:zimbraMail}contactRankingTest"/>
 *           &lt;element name="conversationTest" type="{urn:zimbraMail}conversationTest"/>
 *           &lt;element name="currentDayOfWeekTest" type="{urn:zimbraMail}currentDayOfWeekTest"/>
 *           &lt;element name="currentTimeTest" type="{urn:zimbraMail}currentTimeTest"/>
 *           &lt;element name="dateTest" type="{urn:zimbraMail}dateTest"/>
 *           &lt;element name="facebookTest" type="{urn:zimbraMail}facebookTest"/>
 *           &lt;element name="flaggedTest" type="{urn:zimbraMail}flaggedTest"/>
 *           &lt;element name="headerExistsTest" type="{urn:zimbraMail}headerExistsTest"/>
 *           &lt;element name="headerTest" type="{urn:zimbraMail}headerTest"/>
 *           &lt;element name="importanceTest" type="{urn:zimbraMail}importanceTest"/>
 *           &lt;element name="inviteTest" type="{urn:zimbraMail}inviteTest"/>
 *           &lt;element name="linkedinTest" type="{urn:zimbraMail}linkedInTest"/>
 *           &lt;element name="listTest" type="{urn:zimbraMail}listTest"/>
 *           &lt;element name="meTest" type="{urn:zimbraMail}meTest"/>
 *           &lt;element name="mimeHeaderTest" type="{urn:zimbraMail}mimeHeaderTest"/>
 *           &lt;element name="sizeTest" type="{urn:zimbraMail}sizeTest"/>
 *           &lt;element name="socialcastTest" type="{urn:zimbraMail}socialcastTest"/>
 *           &lt;element name="trueTest" type="{urn:zimbraMail}trueTest"/>
 *           &lt;element name="twitterTest" type="{urn:zimbraMail}twitterTest"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="condition" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filterTests", propOrder = {
    "addressBookTestOrAddressTestOrAttachmentTest"
})
public class FilterTests {

    @XmlElements({
        @XmlElement(name = "addressBookTest", type = AddressBookTest.class),
        @XmlElement(name = "addressTest", type = AddressTest.class),
        @XmlElement(name = "attachmentTest", type = AttachmentTest.class),
        @XmlElement(name = "bodyTest", type = BodyTest.class),
        @XmlElement(name = "bulkTest", type = BulkTest.class),
        @XmlElement(name = "contactRankingTest", type = ContactRankingTest.class),
        @XmlElement(name = "conversationTest", type = ConversationTest.class),
        @XmlElement(name = "currentDayOfWeekTest", type = CurrentDayOfWeekTest.class),
        @XmlElement(name = "currentTimeTest", type = CurrentTimeTest.class),
        @XmlElement(name = "dateTest", type = DateTest.class),
        @XmlElement(name = "facebookTest", type = FacebookTest.class),
        @XmlElement(name = "flaggedTest", type = FlaggedTest.class),
        @XmlElement(name = "headerExistsTest", type = HeaderExistsTest.class),
        @XmlElement(name = "headerTest", type = HeaderTest.class),
        @XmlElement(name = "importanceTest", type = ImportanceTest.class),
        @XmlElement(name = "inviteTest", type = InviteTest.class),
        @XmlElement(name = "linkedinTest", type = LinkedInTest.class),
        @XmlElement(name = "listTest", type = ListTest.class),
        @XmlElement(name = "meTest", type = MeTest.class),
        @XmlElement(name = "mimeHeaderTest", type = MimeHeaderTest.class),
        @XmlElement(name = "sizeTest", type = SizeTest.class),
        @XmlElement(name = "socialcastTest", type = SocialcastTest.class),
        @XmlElement(name = "trueTest", type = TrueTest.class),
        @XmlElement(name = "twitterTest", type = TwitterTest.class)
    })
    protected List<FilterTest> addressBookTestOrAddressTestOrAttachmentTest;
    @XmlAttribute(name = "condition", required = true)
    protected String condition;

    /**
     * Gets the value of the addressBookTestOrAddressTestOrAttachmentTest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressBookTestOrAddressTestOrAttachmentTest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressBookTestOrAddressTestOrAttachmentTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressBookTest }
     * {@link AddressTest }
     * {@link AttachmentTest }
     * {@link BodyTest }
     * {@link BulkTest }
     * {@link ContactRankingTest }
     * {@link ConversationTest }
     * {@link CurrentDayOfWeekTest }
     * {@link CurrentTimeTest }
     * {@link DateTest }
     * {@link FacebookTest }
     * {@link FlaggedTest }
     * {@link HeaderExistsTest }
     * {@link HeaderTest }
     * {@link ImportanceTest }
     * {@link InviteTest }
     * {@link LinkedInTest }
     * {@link ListTest }
     * {@link MeTest }
     * {@link MimeHeaderTest }
     * {@link SizeTest }
     * {@link SocialcastTest }
     * {@link TrueTest }
     * {@link TwitterTest }
     * 
     * 
     */
    public List<FilterTest> getAddressBookTestOrAddressTestOrAttachmentTest() {
        if (addressBookTestOrAddressTestOrAttachmentTest == null) {
            addressBookTestOrAddressTestOrAttachmentTest = new ArrayList<FilterTest>();
        }
        return this.addressBookTestOrAddressTestOrAttachmentTest;
    }

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondition(String value) {
        this.condition = value;
    }

}
