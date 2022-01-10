package pe.cine.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	
	private String nombre;
	private String usuario;
	private String email;
	private String password;
	private boolean estado;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public UsuarioPrincipal(String nombre, String usuario, String email, String password, boolean estado,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.email = email;
		this.password = password;
		this.estado = estado;
		this.authorities = authorities;
	}

	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities=
				usuario.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
				.collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(),usuario.getPassword(), usuario.isEstado(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return usuario;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}

	public boolean isEstado() {
		return estado;
	}

}
