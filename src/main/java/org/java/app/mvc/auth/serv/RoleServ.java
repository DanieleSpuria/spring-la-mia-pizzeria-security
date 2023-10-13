package org.java.app.mvc.auth.serv;

import java.util.List;

import org.java.app.mvc.auth.pojo.Role;
import org.java.app.mvc.auth.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServ {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
	
	public Role findById(int id) {
		
		return roleRepo.findById(id).get();
	}
	
	public Role save(Role role) {
		
		return roleRepo.save(role);
	}
}
