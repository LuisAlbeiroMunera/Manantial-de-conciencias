package org.oasisDeVida.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LoginMgrImpl implements ILoginMgr {

	@Autowired
	private UserDetailsMgrImpl userDetails;

}
