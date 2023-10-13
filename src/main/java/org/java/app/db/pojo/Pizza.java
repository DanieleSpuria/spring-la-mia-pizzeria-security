package org.java.app.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 128, nullable = false)
	@Length(min = 3)
	private String nome;
	
	@Length(min = 5)
	private String descrizione;
	
	@Column(columnDefinition = "TEXT")
	private String foto;
	
	@Column(nullable = false) 
	private float prezzo;
	
	@OneToMany(mappedBy = "pizza")
	private List<Offerta> offerte;
	
	@ManyToMany(mappedBy = "pizze")
	private List<Ingrediente> ingredienti;
	
	public Pizza() {}
	
	public Pizza(String nome, String descrizione, String foto, float prezzo, Ingrediente... ingredienti) {
		setNome(nome);
		setDescrizione(descrizione);
		setFoto(foto);
		setPrezzo(prezzo);
		setIngredienti(Arrays.asList(ingredienti));
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public List<Offerta> getOfferte() {
		return offerte;
	}

	public void setOfferte(List<Offerta> offerte) {
		this.offerte = offerte;
	}	
	
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	@Override
	public String toString() {
		return "[ " + getId() + " ] : " 
				+ getNome() + " : "
				+ getPrezzo() + " : "
				+ getFoto();
	}
}
