package spending.management.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spending.management.app.dto.request.FunctionRoleDTO;
import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.model.Function;
import spending.management.app.model.Role;
import spending.management.app.repository.FunctionRepository;
import spending.management.app.repository.RoleRepository;
import spending.management.app.until.Constant;
import spending.management.app.until.CreateObject;

@Service
public class FunctionService {

	@Autowired
	FunctionRepository functionRepository;
	@Autowired
	RoleRepository roleRepository;
	
	public ResponseMessageDetail<Function> assignFunctionForRole(FunctionRoleDTO functionRoleDTO){
		ResponseMessageDetail<Function> result ;
		try {
			Set<Role> roles =  roleRepository.findByIdIn(functionRoleDTO.getIds());
			Optional<Function> optFunction = functionRepository.findById(functionRoleDTO.getFunctionId());
			if(optFunction.isPresent()) {
				Function func = optFunction.get();
				func.setRoles(roles);
				functionRepository.save(func);
				result = CreateObject.responseMessageDetailFunctionSuccess(func);
			}
			else {
				result = CreateObject.responseMessageDetailFunctionError(400,Constant.MESSAGE_FAIL,Constant.MESSAGE_ERROR_NOT_EXIST);
			}
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailFunctionError(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public ResponseMessage<Function> getAllFunction(){
		ResponseMessage<Function> result ;
		try {
			List<Function> listFunction = functionRepository.findAll();
			result = CreateObject.responseMessageFunction(listFunction);
		}catch(Exception e) {
			result = CreateObject.responseMessageFunction(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Function> getDetailFunction(int id){
		ResponseMessageDetail<Function> result ;
		try {
			Optional<Function> optRole = functionRepository.findById(id);
			if(optRole.isPresent()) {
				result = CreateObject.responseMessageDetailFunctionSuccess(optRole.get());
			}
			else {
				result = CreateObject.responseMessageDetailFunctionError(400,Constant.MESSAGE_FAIL,Constant.MESSAGE_ERROR_NOT_EXIST);
			}
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailFunctionError(500,Constant.MESSAGE_FAIL,e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Function> saveRole(Function function){
		ResponseMessageDetail<Function> result ;
		try {
			function.setCreateDate(new Date());
			Function functionRes = functionRepository.save(function);
			result = CreateObject.responseMessageDetailFunctionSuccess(functionRes);
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailFunctionError(500, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Function> updateFunction(Function function){
		ResponseMessageDetail<Function> result ;
		try {
			Optional<Function> optRole = functionRepository.findById(function.getId());
			if(optRole.isPresent()) {
				Function functionRes = functionRepository.save(function);
				result = CreateObject.responseMessageDetailFunctionSuccess(functionRes);
			}
			else {
				result = CreateObject.responseMessageDetailFunctionError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_NOT_EXIST);
			}
			
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailFunctionError(500, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
	
	public ResponseMessageDetail<Function> deleteRole(int id){
		ResponseMessageDetail<Function> result ;
		try {
			Optional<Function> optRole = functionRepository.findById(id);
			if(optRole.isPresent()) {
				Function functionDel = Function.builder().id(id).build();
				functionRepository.delete(functionDel);
				result = CreateObject.responseMessageDetailFunctionSuccess(null);
			}
			else {
				result = CreateObject.responseMessageDetailFunctionError(400, Constant.MESSAGE_FAIL, Constant.MESSAGE_ERROR_NOT_EXIST);
			}
		}catch(Exception e) {
			result = CreateObject.responseMessageDetailFunctionError(500, Constant.MESSAGE_FAIL, e.toString());
		}
		return result;
	}
}
