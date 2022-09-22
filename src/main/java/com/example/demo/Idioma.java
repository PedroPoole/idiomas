package com.example.demo;

import javax.persistence.*;




@Entity
@Table(name = "Idioma")
public class Idioma {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigoIdioma;
	
	//Cuidado con los nombres de los atributos, no deben tener _
	@Column(name = "nombre")
	private String nombreIdioma;
	
	@Column(name = "traduccion")
	private String traduccionIdioma;
	
	public Idioma() {
		
	}
	
	public Idioma(String nombre_idioma, String traduccion_idioma) {
		this.nombreIdioma=nombre_idioma;
		this.traduccionIdioma=traduccion_idioma;
	}

	public long getCodigoIdioma() {
		return codigoIdioma;
	}

	public void setCodigoIdioma(long codigo_idioma) {
		this.codigoIdioma = codigo_idioma;
	}

	public String getNombreIdioma() {
		return nombreIdioma;
	}

	public void setNombreIdioma(String nombre_idioma) {
		this.nombreIdioma = nombre_idioma;
	}

	public String getTraduccionIdioma() {
		return traduccionIdioma;
	}

	public void setTraduccionIdioma(String traduccion_idioma) {
		this.traduccionIdioma = traduccion_idioma;
	}

	@Override
	public String toString() {
		return "Idioma [codigo_idioma=" + codigoIdioma + ", nombre_idioma=" + nombreIdioma + ", traduccion_idioma="
				+ traduccionIdioma + "]";
	}
	
	
}
