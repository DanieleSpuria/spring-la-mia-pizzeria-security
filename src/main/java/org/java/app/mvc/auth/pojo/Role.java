package org.java.app.mvc.auth.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotNull
	private String nome;
	
	
	public Role() {}
	
	public Role(String nome) {

		setNome(nome);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Role)) return false;
		
		Role objRole = (Role) obj;
		
		return getId() == objRole.getId();
	}
}
