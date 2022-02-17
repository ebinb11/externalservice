package com.externalService.externalService.service;

import org.springframework.data.domain.Sort.Direction;

import com.externalService.externalService.dto.StatusResponse;
import com.externalService.externalService.dto.UserListResponseDTO;
import com.externalService.externalService.dto.UserRequestDTO;
import com.externalService.externalService.dto.UserResponseDTO;
import com.externalService.externalService.entity.User;

public interface UserService {

	User findUserByEmailAddress(final String userName); 
	
	UserListResponseDTO getUserList(Integer page, Integer size, String search, Direction sort, Long id);

	UserResponseDTO userEntry(UserRequestDTO request);

	UserResponseDTO userGetById(Long id);

	StatusResponse deleteUser(Long id);

}
