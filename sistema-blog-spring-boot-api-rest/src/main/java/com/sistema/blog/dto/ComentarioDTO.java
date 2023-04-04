package com.sistema.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDTO {

	private Long id;
	
	@NotEmpty(message = "El nombre no debe estar vacio")	
	private String nombre;
	
	@NotEmpty(message = "El nombre no debe estar vacio")
	@Email
	private String email;
	
	@NotEmpty(message = "El nombre no debe estar vacio")
	@Size(min = 10, message = "El comentario debe tener minimo 10 caracteres")
	private String cuerpo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public ComentarioDTO() {
		super();
	}

}
