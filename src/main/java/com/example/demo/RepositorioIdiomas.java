package com.example.demo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface RepositorioIdiomas extends JpaRepository<Idioma, Long> {
	long deleteByNombreIdioma(String idioma);
	List<Idioma> findByNombreIdioma(String idioma);//findBy es semántica de Spring y tiene que ser así (https://www.baeldung.com/spring-data-derived-queries)
	//Queries mas complejas: https://www.baeldung.com/spring-data-jpa-query
	//Para meter variables en el @query, usar ?1, ?2, etc


}