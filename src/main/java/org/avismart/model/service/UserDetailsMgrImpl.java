package org.avismart.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.avismart.modelo.entity.RecursosRol;
import org.avismart.modelo.entity.Rol;
import org.avismart.modelo.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






@Service
@Transactional
public class UserDetailsMgrImpl implements UserDetailsService {

	
	@Autowired
	IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<RecursosRol> listaRecursosRol = new ArrayList<>();
		Usuario appUser = (usuarioRepository.findByUserName(username));
		if(appUser != null && appUser.getListaRoles() != null) {
		for (Rol role : appUser.getListaRoles()) {
			listaRecursosRol.addAll(role.getListaPrivilegiosRol());
			authorities.add(new SimpleGrantedAuthority(role.getNombreRol()));
		}
		}
		
		for (RecursosRol item : listaRecursosRol) {
			authorities.add(new SimpleGrantedAuthority(item.getRecurso()));
		}

	  return new org.springframework.security.core.userdetails.User(appUser.getUserName(), appUser.getPassword(),  authorities);
	       	
	}
	

	/** 
	 * Metodo que devuelve el userName del usuario logeado
	 * @return
	 */
	public String userLogged() {
		try {
			if (SecurityContextHolder.getContext() != null
					&& SecurityContextHolder.getContext().getAuthentication() != null) {
				UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();
				return userDetail.getUsername().toString();
			}
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * Metodo que devbulve de los recursos solo los que son tipo menu
	 * @param username
	 * @return
	 */
	
	public List<RecursosRol> loggedUserMenu(String username) {
		Usuario usuario = (usuarioRepository.findByUserName(username));
		List<RecursosRol> listaRecursosRol = new ArrayList<RecursosRol>();
		List<RecursosRol> listaMenu = new ArrayList<RecursosRol>();
		for (Rol rol : usuario.getListaRoles()) {
			listaRecursosRol.addAll(rol.getListaPrivilegiosRol());
		}
		for (RecursosRol recurso : listaRecursosRol) {
			if (recurso.getMenu().equals("S")) {
				listaMenu.add(recurso);
			}
			   
		}
		//Se eliminan lo repetidos, para que o se duplique el menu
		listaMenu = listaMenu.stream().distinct().collect(Collectors.toList());
		
		return listaMenu;
	}
		
	
	public List<String> getRoles() {
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
			UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null && principal.getAuthorities() != null) {
				List<String> authorities = new ArrayList<String>();
				for (GrantedAuthority roles : principal.getAuthorities()) {
					if (roles.getAuthority().startsWith("ROLE_")) {
						authorities.add(roles.getAuthority());
					}
				}
				return authorities;
			}
		}
		return null;
	}
	
}