package com.websystique.springmvc.model;
// Generated 23-ott-2018 21.38.35 by Hibernate Tools 5.2.11.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Mercato generated by hbm2java
 */
@Entity
@Table(name = "MERCATO", schema = "PUBLIC", catalog = "PUBLIC")
public class Mercato implements java.io.Serializable {

	private Integer mercatoId;
	private String codice;
	private String nome;
	private Set titolos = new HashSet(0);

	public Mercato() {
	}

	public Mercato(String codice, String nome, Set titolos) {
		this.codice = codice;
		this.nome = nome;
		this.titolos = titolos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "MERCATO_ID", unique = true, nullable = false)
	public Integer getMercatoId() {
		return this.mercatoId;
	}

	public void setMercatoId(Integer mercatoId) {
		this.mercatoId = mercatoId;
	}

	@Column(name = "CODICE", length = 10)
	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	@Column(name = "NOME", length = 20)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "mercato")
	public Set getTitolos() {
		return this.titolos;
	}

	public void setTitolos(Set titolos) {
		this.titolos = titolos;
	}

}