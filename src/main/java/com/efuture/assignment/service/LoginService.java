package com.efuture.assignment.service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efuture.assignment.auth.LoginCredentials;
import com.efuture.assignment.dto.UserDTO;
import com.efuture.assignment.dto.UserResponse;
import com.efuture.assignment.enums.ActiveStatusEnum;
import com.efuture.assignment.enums.ResponseEnum;
import com.efuture.assignment.model.User;
import com.efuture.assignment.repository.UserRepository;


@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtService jwtService;

	//login service for application
	public UserResponse userLogin(ServletRequest request, LoginCredentials loginCredentials, HttpServletResponse resp) {
		UserResponse response = new UserResponse();
		User user = new User();
		try {			
			
			user = userRepository.findByEmployeeCode(loginCredentials.getUsername());
			if(user != null) {
				
				if(user.getStatus().equals(ActiveStatusEnum.ACTIVE.getCode())) {
					
					String accessToken = jwtService.tokenFor(user.getEmployeeCode(), "user");
					resp.setHeader("token", accessToken);
				
					UserDTO userDto = new UserDTO();
					userDto.setUserId(user.getUserId());
					userDto.setFullName(user.getFullName());
					userDto.setDesignation(user.getDesignation());
					userDto.setEmail(user.getEmail());
					userDto.setEmployeeCode(user.getEmployeeCode());
					
					response.setToken(accessToken);
					response.setUserDto(userDto);
					response.setCode(ResponseEnum.SUCCESS.getCode());
					response.setMessage(ResponseEnum.SUCCESS.getMessage());
					
				}else {
					response.setCode(ResponseEnum.INACTIVE_USER.getCode());
					response.setMessage(ResponseEnum.INACTIVE_USER.getMessage());
				}
				
			}else {
				response.setCode(ResponseEnum.USER_NOT_FOUND.getCode());
				response.setMessage(ResponseEnum.USER_NOT_FOUND.getMessage());
			}
		} catch (Exception e) {
			response.setCode(ResponseEnum.ERROR.getCode());
			response.setMessage(ResponseEnum.ERROR.getMessage());
		}
		return response;
	}

}

