package com.prgs.PublicRaiseGovernamentSolve.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userId")
	private int userId;
	@Basic
	@Column(name = "firstName", length = 50, nullable = false)
	private String firstName;

	@Basic
	@Column(name = "lastName", length = 50, nullable = false)
	private String lastName;
	@Basic
	@Size(min = 2, max = 30, message = "wrong username")
	@Column(name = "userName", length = 50, nullable = false)
	private String userName;
	@Basic
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	@Basic
	@Column(name = "password", length = 50, nullable = false)
	private String password;
	@Basic
	@Column(name = "dateOfBirth", length = 50, nullable = false)
	private String dateOfBirth;
	@Basic
	@Column(name = "gender")
	private String gender;
	@Basic
	@Column(name = "roleId")
	private int roleId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private List<TicketDetails> listOfticket;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roleId", referencedColumnName = "roleId",insertable = false, updatable = false)
	private Role role;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int userId, String firstName, String lastName,
			@Size(min = 2, max = 30, message = "wrong username") String userName, String email, String password,
			String dateOfBirth, String gender, int roleId, List<TicketDetails> listOfticket, Role role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.roleId = roleId;
		this.listOfticket = listOfticket;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public int getRoleId() {
		return roleId;
	}

	public List<TicketDetails> getListOfticket() {
		return listOfticket;
	}

	public Role getRole() {
		return role;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setListOfticket(List<TicketDetails> listOfticket) {
		this.listOfticket = listOfticket;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}