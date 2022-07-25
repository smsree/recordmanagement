package com.axisbank.project2.controlller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('AUDIT') or hasRole('ADMIN') or hasRole('OPERATIONAL')")
	public String userAccess() {
		return "User Content.";
	}
	@GetMapping("/operational")
	@PreAuthorize("hasRole('OPERATIONAL')")
	public String moderatorAccess() {
		return "OPERATIONAL Board.";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	@GetMapping("/audit")
	@PreAuthorize("hasRole('AUDIT')")
	public String auditAccess() {
		return "Audit board";
	}
}