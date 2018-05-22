package com.javasampleapproach.h2.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "role_name")
	private String role;

	@RestResource(exported = false)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "role_res", joinColumns = @JoinColumn(name = "roleId"), inverseJoinColumns = @JoinColumn(name = "resId"))
	private Set<RoleResponsibility> responsibilities;

	public Role() {

	}

	public Set<RoleResponsibility> getResponsibilities() {
		return responsibilities;
	}

	public void setResponsibilities(Set<RoleResponsibility> responsibilities) {
		this.responsibilities = responsibilities;
	}

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

}
