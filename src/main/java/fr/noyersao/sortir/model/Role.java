package fr.noyersao.sortir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_role")
public class Role {

	// attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_role_id")
	private Long id;
	
	@Column(name = "role_name")
	private String role;
	
	@Column(name = "role_desc")
	private String desc;
	
	//constructors
	
	public Role(String role, String desc) {
		super();
		this.role = role;
		this.desc = desc;
	}
	
	//getters & setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
