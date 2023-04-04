package com.sistema.blog.controlador;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.blog.dto.LoginDTO;
import com.sistema.blog.dto.RegistroDTO;
import com.sistema.blog.entidades.Rol;
import com.sistema.blog.entidades.Usuario;
import com.sistema.blog.servicio.RolRepositorio;
import com.sistema.blog.servicio.UsuarioRepositorio;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private RolRepositorio rolRolRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@PostMapping("/iniciarSesion")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(null);
		return new ResponseEntity<>("Inicio de sesion exitosa!",HttpStatus.OK);
		
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){		
		if (usuarioRepositorio.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<> ("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioRepositorio.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<> ("El email de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();		
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		
		Rol roles = rolRolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		usuarioRepositorio.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitoso.", HttpStatus.OK);		
	}
	

}
