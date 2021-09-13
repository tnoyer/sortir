package fr.noyersao.sortir.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "auth_user")
public class User {

	//attributes
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_user_id")
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    @Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
    private String email;
     
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "first_name", nullable = false, length = 20)
    @NotEmpty(message = "Please provide your first name")
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 20)
    @NotEmpty(message = "Please provide your last name")
    private String lastName;
    
    @Column(name="phone_number", nullable = true, length = 15)
    private String phoneNumber;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
    private Set<Role> roles;

	//constructors
    
	public User() {
		super();
	}
	
	public User(String email, String password, String firstName, String lastName, String phoneNumber) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	public User(Long id, String email, String password, String firstName, String lastName, String phoneNumber) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	//getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
