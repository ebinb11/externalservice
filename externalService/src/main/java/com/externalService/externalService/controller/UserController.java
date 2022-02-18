package com.externalService.externalService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.externalService.externalService.dto.StatusResponse;
import com.externalService.externalService.dto.UserListResponseDTO;
import com.externalService.externalService.dto.UserRequestDTO;
import com.externalService.externalService.dto.UserResponseDTO;
import com.externalService.externalService.exception.BadDataException;
import com.externalService.externalService.service.UserService;
import com.externalService.externalService.utils.AppResponse;


@RestController
@RequestMapping(value = "api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to esaf!";
	}

	@GetMapping(value = "getUserList")
	public AppResponse<UserListResponseDTO> getUserList(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
			@RequestParam(value = "search", required = false, defaultValue = "") String search,
			@RequestParam(value = "sort", required = false, defaultValue = "ASC") Sort.Direction sort,
			@RequestParam(value = "id", required = false, defaultValue = "") Long id) {
		UserListResponseDTO response = userService.getUserList(page, size, search, sort, id);
		if (response != null) {
			return AppResponse.<UserListResponseDTO>builder()
					.data(response)
					.message("User Listed Successfully!")
					.success(true)
					.build();
		} else {
			return AppResponse.<UserListResponseDTO>builder()
					.message("Oops Something went wrong! please check after some times")
					.success(false)
					.build();
		}
	}
	
	@PostMapping(value = "userEntry")
	public AppResponse<UserResponseDTO> userEntry(@RequestBody UserRequestDTO request) {
		UserResponseDTO response = userService.userEntry(request);
		if (response != null) {
			return AppResponse.<UserResponseDTO> builder()
					.data(response)
					.message("User Saved Successfully !")
					.success(true)
					.build();
		} else {
			return AppResponse.<UserResponseDTO> builder()
					.message("Oops something went wrong, please try again later!")
					.success(false)
					.build();
		}
	}
	
	@GetMapping(value = "/userGet/{id}", consumes = "application/json", produces = "application/json")
	public AppResponse<UserResponseDTO> userGet(@PathVariable Long id) throws BadDataException {
		UserResponseDTO response = userService.userGetById(id);
		if (response != null) {
			return AppResponse.<UserResponseDTO> builder()
					.data(response)
					.message("User fetched successfully")
					.success(true)
					.build();
		} else {
			return AppResponse.<UserResponseDTO> builder()
					.message("No data found !")
					.success(false)
					.build();
		}
 	}
	
	@DeleteMapping(value = "/user/{id}")
	public AppResponse<StatusResponse> userDelete(@PathVariable Long id) throws BadDataException {
		StatusResponse response = userService.deleteUser(id);
		if (response != null) {
			return AppResponse.<StatusResponse> builder()
					.data(response)
					.message("User deleted successfully !")
					.success(false)
					.build();
		} else {
			return AppResponse.<StatusResponse> builder()
					.message("Oops Something went wrong!")
					.success(false)
					.build();
		}
	}
	 
	@GetMapping("/feignService")
	public String feignService() {
		return "Hey Ebin, i'm from external service and feign client is working";
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
		UserResponseDTO response = userService.userGetById(id);
		return ResponseEntity.ok(response);

	}
}
