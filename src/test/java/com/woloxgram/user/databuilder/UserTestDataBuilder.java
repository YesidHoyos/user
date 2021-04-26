package com.woloxgram.user.databuilder;

import com.woloxgram.user.model.Address;
import com.woloxgram.user.model.Company;
import com.woloxgram.user.model.Geo;
import com.woloxgram.user.model.User;

public class UserTestDataBuilder {

	private static final Long ID = 1L;
	private static final String NAME = "Leanne Graham";
	private static final String USERNAME = "Bret";
	private static final String EMAIL = "Sincere@april.biz";
	private static final Address ADDRESS = new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496"));
	private static final String PHONE = "1-770-736-8031 x56442";
	private static final String WEBSITE = "hildegard.org";
	private static final Company COMPANY =  new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets");
	
	private Long id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;
	private Company company;
	
	public UserTestDataBuilder() {
		this.id = ID;
		this.name = NAME;
		this.username = USERNAME;
		this.email = EMAIL;
		this.address = ADDRESS;
		this.phone = PHONE;
		this.website = WEBSITE;
		this.company = COMPANY;
	}
	
	public User build() {
		return new User(this.id, this.name, this.username, this.email, this.address, this.phone, this.website, this.company);
	}
	
	public UserTestDataBuilder withId(Long id) {
		this.id = id;
		return this;
	}
	public UserTestDataBuilder withName(String name) {
		this.name = name;
		return this;
	}
	public UserTestDataBuilder withUsername(String username) {
		this.username = username;
		return this;
	}
	public UserTestDataBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	public UserTestDataBuilder withAddres(Address address) {
		this.address = address;
		return this;
	}
	public UserTestDataBuilder withPhone(String phone) {
		this.phone = phone;
		return this;
	}
	public UserTestDataBuilder withWebsite(String website) {
		this.website = website;
		return this;
	}
	public UserTestDataBuilder withCompany(Company company) {
		this.company = company;
		return this;
	}
}
