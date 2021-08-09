package spending.management.app.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import spending.management.app.config.UserDetailsCustom;
import spending.management.app.dto.request.UserRoleDTO;
import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.dto.response.UserDTO;
import spending.management.app.model.Role;
import spending.management.app.model.User;
import spending.management.app.repository.RoleRepository;
import spending.management.app.repository.UserRepository;
import spending.management.app.security.JwtTokenProvider;
import spending.management.app.until.Constant;
import spending.management.app.until.CreateObject;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	
	public ResponseMessage<UserDTO> getAllUser(int page, int size){
		ResponseMessage<UserDTO> result ;
		PageRequest pageRequest = PageRequest.of(page, size);
		try {
			Page pageUser = userRepository.findAll(pageRequest);
			List<User> listUser = pageUser.getContent();
			listUser.forEach(e -> System.out.println(e.toString()));
			List<UserDTO> listUserDTO =  listUser.stream().map(e -> modelMapper.map(e, UserDTO.class)).collect(Collectors.toList());
			result = CreateObject.responseMessageUser(listUserDTO);
		}catch(Exception e) {
			result = CreateObject.responseMessageUser(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public UserDetailsCustom getPrincipal() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetailsCustom) {
            return (UserDetailsCustom) object;
        } else {
            return null;
        }
    }
	
	public ResponseMessageDetail<UserDTO> getUserInfo(){
		UserDetailsCustom userDetailsCustom = getPrincipal();
		User user = userDetailsCustom.getUser();
		return CreateObject.getResponseMessageDetailSuccess(modelMapper.map(user, UserDTO.class));
	}
	
	public ResponseMessageDetail<UserDTO> getDetailUser(Long id){
		ResponseMessageDetail<UserDTO> result ;
		try {
			Optional<User> optUser = userRepository.findById(id);
			if(optUser.isPresent()) {
				UserDTO  userDTO = modelMapper.map(optUser.get(), UserDTO.class);
				result = CreateObject.getResponseMessageDetailSuccess(userDTO);
			}else {
				result = CreateObject.getResponseMessageDetailError(500,Constant.MESSAGE_FAIL,Constant.MESSAGE_ERROR_NOT_EXIST);
			}
		}catch(Exception e) {
			result = CreateObject.getResponseMessageDetailError(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public Set<Role> getDefaultRole() {
		Role role = Role.builder()
					.code("ROLE_USER")
					.id(2)
					.build();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		return roles;
	}
	
	public ResponseMessageDetail<UserDTO> saveUser(User user){
		ResponseMessageDetail<UserDTO> result ;
		try {
			if(checkUserExist(user)) {
				result = CreateObject.getResponseMessageDetailError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_EXIST);
			}
			else {
				
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.setCreateDate(new Date());
				user.setRoles(getDefaultRole());
				User userRes = userRepository.save(user);
				UserDTO userDTO = modelMapper.map(userRes, UserDTO.class);
				result = CreateObject.getResponseMessageDetailSuccess(userDTO);
			}
		}catch(Exception e) {
			result = CreateObject.getResponseMessageDetailError(400, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<UserDTO> updatePassword(User user){
		ResponseMessageDetail<UserDTO> result ;
		try {
			if(checkUserExist(user) == false) {
				result = CreateObject.getResponseMessageDetailError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_NOT_EXIST);
			}
			else {
				User userRes = userRepository.findByUsername(user.getUsername());
				userRes.setPassword(passwordEncoder.encode(user.getPassword()));
				User userUpdated = userRepository.save(userRes);
				UserDTO userDTO = modelMapper.map(userUpdated, UserDTO.class);
				result = CreateObject.getResponseMessageDetailSuccess(userDTO);
			}
		}catch(Exception e) {
			result = CreateObject.getResponseMessageDetailError(400, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<UserDTO> updateRole(UserRoleDTO userRoleDTO){
		ResponseMessageDetail<UserDTO> result ;
		try {
			if(checkUserExist(userRoleDTO.getUserId()) == false) {
				result = CreateObject.getResponseMessageDetailError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_NOT_EXIST);
			}
			else {
				Optional<User> optUser = userRepository.findById(userRoleDTO.getUserId());
				Set<Role> setRole =  roleRepository.findByIdIn(userRoleDTO.getRoleId());
				
				User user = optUser.get();
				user.setRoles(setRole);
				userRepository.save(user);
				UserDTO userDTO = modelMapper.map(optUser.get(), UserDTO.class);
				result = CreateObject.getResponseMessageDetailSuccess(userDTO);
			}
		}catch(Exception e) {
			result = CreateObject.getResponseMessageDetailError(400, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public boolean checkUserExist(User user) {
		return userRepository.existsUserByUsername(user.getUsername());
	}
	
	public boolean checkUserExist(Long id) {
		return userRepository.existsById(id);
	}
}
