package spending.management.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.model.Role;
import spending.management.app.repository.RoleRepository;
import spending.management.app.until.Constant;
import spending.management.app.until.CreateObject;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public ResponseMessage<Role> getAllRole(int page, int size){
		ResponseMessage<Role> result ;
		PageRequest pageRequest = PageRequest.of(page, size);
		try {
			Page pageRole = roleRepository.findAll(pageRequest);
			List<Role> listRole = pageRole.getContent();
			result = CreateObject.responseMessageRole(listRole);
		}catch(Exception e) {
			result = CreateObject.responseMessageRole(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Role> getDetailRole(int id){
		ResponseMessageDetail<Role> result ;
		try {
			Optional<Role> optRole = roleRepository.findById(id);
			if(optRole.isPresent()) {
				result = CreateObject.responseMessageDetailRoleSuccess(optRole.get());
			}
			else {
				result = CreateObject.responseMessageDetailRoleError(400,Constant.MESSAGE_FAIL,Constant.MESSAGE_ERROR_NOT_EXIST);
			}
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailRoleError(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Role> saveRole(Role role){
		System.out.println("=================");
		ResponseMessageDetail<Role> result ;
		System.out.println("role: "+role.toString());
		try {
			role.setCreateDate(new Date());
			Role roleRes = roleRepository.save(role);
			result = CreateObject.responseMessageDetailRoleSuccess(roleRes);
			System.out.println("result: "+result.toString());
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailRoleError(500, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Role> updateRole(Role role){
		ResponseMessageDetail<Role> result ;
		try {
			Optional<Role> optRole = roleRepository.findById(role.getId());
			if(optRole.isPresent()) {
				Role roleRes = roleRepository.save(role);
				result = CreateObject.responseMessageDetailRoleSuccess(roleRes);
			}
			else {
				result = CreateObject.responseMessageDetailRoleError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_NOT_EXIST);
			}
			
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailRoleError(500, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Role> deleteRole(int id){
		ResponseMessageDetail<Role> result ;
		try {
			Optional<Role> optRole = roleRepository.findById(id);
			if(optRole.isPresent()) {
				Role roleDel = Role.builder().id(id).build();
				roleRepository.delete(roleDel);
				result = CreateObject.responseMessageDetailRoleSuccess(null);
			}
			else {
				result = CreateObject.responseMessageDetailRoleError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_NOT_EXIST);
			}
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailRoleError(500, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
}
