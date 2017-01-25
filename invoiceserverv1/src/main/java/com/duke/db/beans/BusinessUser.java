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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BusinessUser generated by hbm2java
 */
@Entity
@Table(name = "BusinessUser", catalog = "invoicedb")
public class BusinessUser implements java.io.Serializable {

	private Integer id;
	private Business business;
	private String firstName;
	private String lastName;
	private byte[] signature;
	private Set<Login> logins = new HashSet<Login>(0);
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

	public BusinessUser() {
	}

	public BusinessUser(Business business, String firstName, String lastName, byte[] signature, Set<Login> logins,
			Set<Invoice> invoices) {
		this.business = business;
		this.firstName = firstName;
		this.lastName = lastName;
		this.signature = signature;
		this.logins = logins;
		this.invoices = invoices;
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
	@JoinColumn(name = "BusinessID")
	public Business getBusiness() {
		return this.business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	@Column(name = "FirstName")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Signature")
	@Lob
	public byte[] getSignature() {
		return this.signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessUser")
	public Set<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(Set<Login> logins) {
		this.logins = logins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessUser")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
