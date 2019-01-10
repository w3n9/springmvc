package spittr.entity;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Spitter {
	private int id;
	@NotNull
	@Size(min=5,max=16,message="{username.tip}")
	private String username;
	@NotNull
	@Size(min=5,max=25,message="{password.tip}")
	private String password;

	@NotNull
	@Size(min=2,max=30,message="{firstName.tip}")
	private String firstName;

	@NotNull
	@Size(min=2,max=30,message="{lastName.tip}")
	private String lastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Spitter{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
