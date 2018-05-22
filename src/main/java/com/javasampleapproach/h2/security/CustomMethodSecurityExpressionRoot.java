package com.javasampleapproach.h2.security;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.javasampleapproach.h2.model.Role;
import com.javasampleapproach.h2.model.RoleOperationFeature;
import com.javasampleapproach.h2.model.RoleResponsibility;
import com.javasampleapproach.h2.model.User;
import com.javasampleapproach.h2.repository.UserRepository;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
		implements MethodSecurityExpressionOperations {

	private static Logger logger = LoggerFactory.getLogger(CustomMethodSecurityExpressionRoot.class);

	private UserRepository repository;

	public CustomMethodSecurityExpressionRoot(Authentication authentication, UserRepository repository) {
		super(authentication);
		logger.info("entering constructor");
		this.repository = repository;
		logger.info("exit constructor");
	}

	public boolean adminCanView(Object id) {
		logger.info("method entry :: adminCanView with data :: " + id);
		boolean flag = false;
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		User user2 = repository.findByUserName(user.getUsername());
		Set<Role> roles = user2.getRoles();
		for (Role r : roles) {
			if (r.getRole() != null && r.getRole().equalsIgnoreCase("ADMIN")) {
				logger.info("enter into ADMIN");
				Set<RoleResponsibility> responsibilities = r.getResponsibilities();
				for (RoleResponsibility responsibility : responsibilities) {
					if (responsibility.getName().equalsIgnoreCase("USER_SIGNUP")) {
						logger.info("enter into USER_SIGNUP");
						List<RoleOperationFeature> features = responsibility.getOperations();
						for (RoleOperationFeature feature : features) {
							if (feature.getName().equalsIgnoreCase("VIEW")) {
								logger.info("enter into VIEW");
								flag = true;
							}
						}
					}
				}
			}
		}
		logger.info("methodExit :: " + flag);
		return flag;
	}

	@Override
	public void setFilterObject(Object filterObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getFilterObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getReturnObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return null;
	}

}
