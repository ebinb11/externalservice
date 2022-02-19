package com.externalService.externalService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.externalService.externalService.dto.AuthRequestDTO;
import com.externalService.externalService.dto.AuthResponseDTO;
import com.externalService.externalService.service.AuthenticationService;
import com.externalService.externalService.utils.AppResponse;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(value = "/authenticate")
	public AppResponse<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO authRequestDTO) {

		AuthResponseDTO authResponseDTO = authenticationService.loginCheck(authRequestDTO);
		if (authResponseDTO != null) {
			return AppResponse.<AuthResponseDTO>builder()
					.data(authResponseDTO)
					.message("Successfully authenticated user.")
					.success(true)
					.build();
		}
		return AppResponse.<AuthResponseDTO>builder()
				.success(false)
				.message("Authentication error, please check provided email or password!")
				.build();
	}
	
	@PostMapping(value = "/authenticateFeign")
	public ResponseEntity<AuthResponseDTO> authenticateFeign(@RequestBody AuthRequestDTO authRequestDTO) {
		AuthResponseDTO authResponse = authenticationService.loginCheck(authRequestDTO);
		if (authResponse != null) {
			return ResponseEntity.ok(authResponse);
		} 
		return ResponseEntity.badRequest().build();
	}
}
