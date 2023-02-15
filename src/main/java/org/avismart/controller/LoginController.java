package org.avismart.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.avismart.model.service.IUsuarioRepository;
import org.avismart.model.service.LoginMgrImpl;
import org.avismart.model.service.UserDetailsMgrImpl;
import org.avismart.modelo.entity.Usuario;
import org.avismart.utils.connection.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
public class LoginController {

    @Autowired
    UserDetailsMgrImpl  userDetailsMgrImpl;
    
    @Autowired
    LoginMgrImpl loginMgrImpl;
       
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	private String pantallaInicio = "usuario/landing"; 

	@GetMapping("/login")
	public String login() {
		return "/index";
	}
	

	@GetMapping("/inicio")
	public String inicio(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Usuario usuario =  usuarioRepository.findByUserName(userDetailsMgrImpl.userLogged());
		System.out.println("INICIO CON USUARIO "+usuario);
		if(usuario != null && usuario.getClaveTemporal().equals("S")) {
			return changePassword();	
		}else if(usuario != null) {				
				HttpSession session = req.getSession();
				session.setAttribute(Constantes.NOMBRE_USUARIO, userDetailsMgrImpl.userLogged());
				session.setAttribute("menu", userDetailsMgrImpl.loggedUserMenu(session.getAttribute(Constantes.NOMBRE_USUARIO).toString()));
				session.setAttribute(Constantes.USUARIO, usuario.getId());
				//publicidadMgrImpl.guardarPublicidad();	
				resp.sendRedirect("/avismart/principal");
				return pantallaInicio; 
		}else {
			return "index";
		}
	}
	
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
		    
		  HttpSession session = request.getSession(false);
		  SecurityContextHolder.clearContext();
		
		  session = request.getSession(false);
		  if(session != null) {
		      session.invalidate();
		  }
		
		  for(Cookie cookie : request.getCookies()) {
		      cookie.setMaxAge(0);
		  }
		
		  return "redirect:/login?logout";
   }

	
	
	@GetMapping("/changePassword")
	public String changePassword() {
		return "changePassword";
	}

}