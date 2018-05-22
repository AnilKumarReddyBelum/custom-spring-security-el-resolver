package com.javasampleapproach.h2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RoleResponsibility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RoleOperationFeature> operations = new ArrayList<>();

	public RoleResponsibility(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoleResponsibility() {

	}

	public List<RoleOperationFeature> getOperations() {
		return operations;
	}

	public void setOperations(List<RoleOperationFeature> operations) {
		this.operations = operations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
