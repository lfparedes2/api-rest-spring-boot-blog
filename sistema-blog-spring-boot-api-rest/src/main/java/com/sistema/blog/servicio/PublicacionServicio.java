package com.sistema.blog.servicio;


import com.sistema.blog.dto.PublicacionDTO;
import com.sistema.blog.dto.PublicacionRespuesta;



public interface PublicacionServicio {
	
	public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);
	
	public PublicacionRespuesta  obtenerTodosLasPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sorDir);
	
	public PublicacionDTO obtenerPublicacionPorId(Long id);
	
	public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, Long id);
	
	public void  eliminarPublicacion(Long id);
	
}
