package com.duke.db.beans;
// Generated 18-Jan-2017 5:19:36 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Business generated by hbm2java
 */
@Entity
@Table(name = "Business", catalog = "invoicedb", uniqueConstraints = @UniqueConstraint(columnNames = "TaxNo"))
public class Business implements java.io.Serializable {

	private Integer id;
	private Address address;
	private CountryMaster countryMaster;
	private IndustryMaster industryMaster;
	private Subscription subscription;
	private String name;
	private String taxNo;
	private String phone;
	private String altPhone;
	private String webSite;
	private byte[] companyLogo;
	private Set<EmailMessage> emailMessages = new HashSet<EmailMessage>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);
	private Set<Customer> customers = new HashSet<Customer>(0);
	private Set<BusinessUser> businessUsers = new HashSet<BusinessUser>(0);
	private Set<LineItem> lineItems = new HashSet<LineItem>(0);
	private Set<Document> documents = new HashSet<Document>(0);

	public Business() {
	}

	public Business(Address address, CountryMaster countryMaster, IndustryMaster industryMaster,
			Subscription subscription, String name, String taxNo, String phone, String altPhone, String webSite,
			byte[] companyLogo, Set<EmailMessage> emailMessages, Set<Invoice> invoices, Set<Customer> customers,
			Set<BusinessUser> businessUsers, Set<LineItem> lineItems, Set<Document> documents) {
		this.address = address;
		this.countryMaster = countryMaster;
		this.industryMaster = industryMaster;
		this.subscription = subscription;
		this.name = name;
		this.taxNo = taxNo;
		this.phone = phone;
		this.altPhone = altPhone;
		this.webSite = webSite;
		this.companyLogo = companyLogo;
		this.emailMessages = emailMessages;
		this.invoices = invoices;
		this.customers = customers;
		this.businessUsers = businessUsers;
		this.lineItems = lineItems;
		this.documents = documents;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AddressID")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CountryMasterID")
	public CountryMaster getCountryMaster() {
		return this.countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Industry")
	public IndustryMaster getIndustryMaster() {
		return this.industryMaster;
	}

	public void setIndustryMaster(IndustryMaster industryMaster) {
		this.industryMaster = industryMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBSCRIPTIONID")
	public Subscription getSubscription() {
		return this.subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "TaxNo", unique = true)
	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	@Column(name = "Phone", length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "AltPhone")
	public String getAltPhone() {
		return this.altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	@Column(name = "WebSite")
	public String getWebSite() {
		return this.webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	@Column(name = "CompanyLogo")
	@Lob
	public byte[] getCompanyLogo() {
		return this.companyLogo;
	}

	public void setCompanyLogo(byte[] companyLogo) {
		this.companyLogo = companyLogo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	public Set<EmailMessage> getEmailMessages() {
		return this.emailMessages;
	}

	public void setEmailMessages(Set<EmailMessage> emailMessages) {
		this.emailMessages = emailMessages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Customer_Business", catalog = "invoicedb", joinColumns = {
			@JoinColumn(name = "BusinessID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "CustomerID", nullable = false, updatable = false) })
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	public Set<BusinessUser> getBusinessUsers() {
		return this.businessUsers;
	}

	public void setBusinessUsers(Set<BusinessUser> businessUsers) {
		this.businessUsers = businessUsers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	public Set<LineItem> getLineItems() {
		return this.lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	public Set<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

}