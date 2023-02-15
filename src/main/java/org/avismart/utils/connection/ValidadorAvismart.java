package org.avismart.utils.connection;


import org.avismart.modelo.entity.Lote;
import org.avismart.modelo.entity.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ValidadorAvismart implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;

	
	}

	@Override
	public void validate(Object target, Errors errors) {
	Lote lote = (Lote)target;
//  ---------->Validar campo código<-----------	
		if (lote.getCodigo().length() >10 ) {
			
			errors.rejectValue("codigo", "codMax");
		}
		if (lote.getCodigo().isEmpty()) {
			
			errors.rejectValue("codigo", "NotBlanck.lote.codigo");
		}
		if (lote.getCodigo().isEmpty()) {
			
			errors.rejectValue("codigo", "Empty");
		}
		if (lote.getCodigo() == null) {
			
			errors.rejectValue("codigo", "Null");
		}
//       ---------->Validar campo descripción<-----------		
		if (lote.getDescripcion().length() < 3 ) {
			
			errors.rejectValue("descripcion", "DesMin");
		}
		if (lote.getDescripcion().length() >50 ) {
			
			errors.rejectValue("descripcion", "DesMax");
		}
		if (lote.getDescripcion().isEmpty()) {
			
			errors.rejectValue("codigo", "Blanck");
		}
		if (lote.getDescripcion().isEmpty()) {
			
			errors.rejectValue("codigo", "Empty");
		}
		if (lote.getDescripcion() == null) {
			
			errors.rejectValue("codigo", "Null");
		}
		
		
		
//		}
//////		if (perfil.getId() < 4 ) {
////			errors.rejectValue("descripcion", "minimo");
////		}
////		if (perfil.getId() > 12 ) {
////			errors.rejectValue("descripcion", "maximo");
////		}
//	
//
//		if (perfil.getDescripcion().isEmpty()) {
//			
//			errors.rejectValue("descripcion", "NotEmpty.perfil.descripcion");
//		}
//		if (perfil.getDescripcion().isBlank()) {
//			errors.rejectValue("descripcion", "NotBlank.perfil.descripcion");
//		}
//		if (perfil.getDescripcion().length() < 4 ) {
//			errors.rejectValue("descripcion", "minimo");
//		}
//		if (perfil.getDescripcion().length() > 50 ) {
//			errors.rejectValue("descripcion", "maximo");
//		}
	
	}}
	

	

