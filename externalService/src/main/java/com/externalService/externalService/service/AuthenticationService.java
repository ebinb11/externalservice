package com.externalService.externalService.service;

import com.externalService.externalService.dto.AuthRequestDTO;
import com.externalService.externalService.dto.AuthResponseDTO;

public interface AuthenticationService {

	AuthResponseDTO loginCheck(AuthRequestDTO authRequestDTO);
}
