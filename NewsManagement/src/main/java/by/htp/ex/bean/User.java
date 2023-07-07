package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String role;
	private String name;
	private String surname;
	private String email;
	private Date registerDate;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	


	@Override
	public int hashCode() {
		return Objects.hash(email, id, name, registerDate, role, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(registerDate, other.registerDate) && Objects.equals(role, other.role)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [id=" + id + ", role=" + role + ", name=" + name + ", surname=" + surname + ", email=" + email
				+ ", registerDate=" + registerDate + "]";
	}

}
