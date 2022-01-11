package pe.cine.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pe.cine.dto.security.JwtDto;
import pe.cine.dto.security.LoginUsuario;
import pe.cine.dto.security.Mensaje;
import pe.cine.dto.security.NuevoUsuario;
import pe.cine.enums.RolNombre;
import pe.cine.jwt.JwtProvider;
import pe.cine.model.Rol;
import pe.cine.model.Usuario;
import pe.cine.service.RolService;
import pe.cine.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	@GetMapping("/lista")
	@ApiOperation(value = "Realiza un Listado de Todos los Usuarios Administradores", httpMethod = "GET", nickname = "Listar Usuarios Administradores")
	public ResponseEntity<List<Usuario>> list() {
		List<Usuario> list = usuarioService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/nuevo")
	@ApiOperation(value = "Realiza el Registro del Nuevo Usuario Administrador", httpMethod = "POST", nickname = "Nuevo Usuario Administrador")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (StringUtils.isBlank(nuevoUsuario.getNombre())) {
			return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity<>(new Mensaje("El Usuario No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(nuevoUsuario.getPassword())) {
			return new ResponseEntity<>(new Mensaje("La Contraseña No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
		}
		if (nuevoUsuario.getNombre().length() > 40) {
			return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
		}
		if (nuevoUsuario.getNombreUsuario().length() > 40) {
			return new ResponseEntity<>(new Mensaje("El Usuario Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
		}
		if (nuevoUsuario.getEmail().length() > 70) {
			return new ResponseEntity<>(new Mensaje("El Email Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
		}
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos Mal Puesto o Correo Inválido"), HttpStatus.BAD_REQUEST);
		}
		if (usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity<>(new Mensaje("Ese Usuario Ya Existe"), HttpStatus.BAD_REQUEST);
		}
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity<>(new Mensaje("Ese Email Ya Existe"), HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()), true);
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		if (nuevoUsuario.getRoles().contains("supadmin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_SUPADMIN).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity<>(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);
	}

	@PostMapping("/nuevousuario")
	@ApiOperation(value = "Realiza el Registro del Nuevo Usuario", httpMethod = "POST", nickname = "Nuevo Usuario")
	public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (StringUtils.isBlank(nuevoUsuario.getNombre())) {
			return new ResponseEntity<>(new Mensaje("El Nombre No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity<>(new Mensaje("El Usuario No Puede Estar Vacío"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(nuevoUsuario.getPassword())) {
			return new ResponseEntity<>(new Mensaje("La Contraseña No Puede Estar Vacía"), HttpStatus.BAD_REQUEST);
		}
		if (nuevoUsuario.getNombre().length() > 40) {
			return new ResponseEntity<>(new Mensaje("El Nombre Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
		}
		if (nuevoUsuario.getNombreUsuario().length() > 40) {
			return new ResponseEntity<>(new Mensaje("El Usuario Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
		}
		if (nuevoUsuario.getEmail().length() > 70) {
			return new ResponseEntity<>(new Mensaje("El Email Es Demasiado Largo"), HttpStatus.BAD_REQUEST);
		}
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Mensaje("Campos Mal Puesto o Correo Inválido"), HttpStatus.BAD_REQUEST);
		}
		if (usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity<>(new Mensaje("Ese Usuario Ya Existe"), HttpStatus.BAD_REQUEST);
		}
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity<>(new Mensaje("Ese Email Ya Existe"), HttpStatus.BAD_REQUEST);
		}

		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()), true);
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity<>(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);
	}

	@PutMapping("/delete/{id}")
	@ApiOperation(value = "Inhabilita un Usuario", httpMethod = "PUT", nickname = "Inhabilitar Usuario")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		Usuario usuario = usuarioService.getOne(id).get();
		if (usuario.isEstado() == true) {
			usuario.setEstado(false);
		} else {
			usuario.setEstado(true);
		}
		usuarioService.save(usuario);
		return new ResponseEntity<>(new Mensaje("Estado Cambiado"), HttpStatus.OK);
	}

	@PostMapping("/login")
	@ApiOperation(value = "Realiza la Validación del Usuario y Genera el Token", httpMethod = "POST", nickname = "Login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if(usuarioService.existsByUsuario(loginUsuario.getNombreUsuario())) {
			Usuario usuario = usuarioService.getByUsuario(loginUsuario.getNombreUsuario()).get();
			if (usuario.isEstado() == false) {
				return new ResponseEntity<>(new Mensaje("Usuario Inhabilitado"), HttpStatus.BAD_REQUEST);
			}
			Set<Rol> rol= new HashSet<>();
			rol.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
			if(usuario.getRoles().equals(rol)) {
				return new ResponseEntity<>(new Mensaje("Usuario No Autorizado"), HttpStatus.BAD_REQUEST);
			}
		}
		if(!usuarioService.existsByUsuario(loginUsuario.getNombreUsuario())) {
			return new ResponseEntity<>(new Mensaje("Usuario No Existe"), HttpStatus.BAD_REQUEST);
		}
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Mensaje("Campos Mal Puestos"), HttpStatus.BAD_REQUEST);

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	@PostMapping("/refresh")
	@ApiOperation(value = "Realiza el Refresh del Token Expirado", httpMethod = "POST", nickname = "Refresh Token")
	public ResponseEntity<?> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
		String token = jwtProvider.refreshToken(jwtDto);
		JwtDto jwt = new JwtDto(token);
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}

}
