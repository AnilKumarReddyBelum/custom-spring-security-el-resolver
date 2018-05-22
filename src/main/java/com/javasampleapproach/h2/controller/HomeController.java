package com.javasampleapproach.h2.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javasampleapproach.h2.model.Role;
import com.javasampleapproach.h2.model.RoleOperationFeature;
import com.javasampleapproach.h2.model.RoleResponsibility;
import com.javasampleapproach.h2.model.User;
import com.javasampleapproach.h2.repository.RoleRepository;
import com.javasampleapproach.h2.repository.RoleResponsibilityRepository;
import com.javasampleapproach.h2.repository.UserRepository;

@RestController
public class HomeController {

	@Autowired
	private RoleResponsibilityRepository responsibilityRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("hasRoleOperationFeature('APPROVE')")
	@GetMapping("/getAllUsersInfo")
	public List<User> getAllUsersInfo() {
		return userRepository.findAll();
	}

	@GetMapping("/save/saveRoleOperationFeature")
	public RoleResponsibility saveRoleOperationFeature() {
		List<RoleOperationFeature> features = new ArrayList<>();
		features.add(new RoleOperationFeature(null, "VIEW"));
		features.add(new RoleOperationFeature(null, "APPROVE"));

		RoleResponsibility responsibility = new RoleResponsibility();
		responsibility.setName("USER_SIGNUP");
		responsibility.setOperations(features);
		responsibilityRepository.save(responsibility);
		return responsibility;
	}

	@GetMapping("/save/saveRole")
	public Role saveRole() {
		RoleResponsibility roleResponsibility = responsibilityRepository.findOne((long) 1);
		Set<RoleResponsibility> set = new HashSet<RoleResponsibility>();
		set.add(roleResponsibility);
		Role role = new Role();
		role.setRole("ADMIN");
		role.setResponsibilities(set);
		roleRepository.save(role);
		return role;
	}

	@GetMapping("/save/saveUser")
	public User saveUser() {
		Role role = roleRepository.findOne(1l);
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		User user = new User("anil", "anil", true);
		user.setRoles(roles);
		userRepository.save(user);
		return user;
	}

	@GetMapping("/save/saveAll")
	public String saveAll() {
		final String uri1 = "http://localhost:8080/save/saveRoleOperationFeature";
		final String uri2 = "http://localhost:8080/save/saveRole";
		final String uri3 = "http://localhost:8080/save/saveUser";

		RestTemplate restTemplate = new RestTemplate();
		String result1 = restTemplate.getForObject(uri1, String.class);
		String result2 = restTemplate.getForObject(uri2, String.class);
		String result3 = restTemplate.getForObject(uri3, String.class);

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);

		return new StringBuilder().append(result1).append(result2).append(result3).toString();
	}

	@PreAuthorize("adminCanView(#id)")
	@RequestMapping(method = RequestMethod.GET, value = "/isAdminAndViewApprove/{id}")
	public User isAdminAndViewApprove(@PathVariable long id) {
		return userRepository.findOne(id);
	}

}
