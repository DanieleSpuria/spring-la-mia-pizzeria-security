package org.java.app.db.pojo;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDate inizio;

	@Column(nullable = false)
	private LocalDate fine;

	@Column(nullable = false)
	@Length(min = 3)
	private String titolo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	public Offerta() {}
	
	public Offerta(LocalDate inizio, LocalDate fine, String titolo, Pizza pizza) {
		
		setInizio(inizio);
		setFine(fine);
		setTitolo(titolo);
		setPizza(pizza);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getInizio() {
		return inizio;
	}
	public void setInizio(LocalDate inizio) {
		this.inizio = inizio;
	}
	public LocalDate getFine() {
		return fine;
	}
	public void setFine(LocalDate fine) {
		this.fine = fine;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		return getTitolo() + " = \n" 
				+ "inizio: "    + getInizio() + "\n"
				+ "fine: "      + getFine()   + "\n";
	}
}
