package spending.management.app.until;

import java.util.Collections;
import java.util.List;

import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.dto.response.UserDTO;
import spending.management.app.model.Role;

public class CreateObject {

	public static ResponseMessageDetail<UserDTO> getResponseMessageDetailError(int errorCode, String message, String errorMessage ) {
		ResponseMessageDetail<UserDTO> result = new ResponseMessageDetail<>();
		result.setErrorCode(errorCode);
//		result.setStatus(status.OK);
		result.setMessage(message);
		result.setErrorMessage(errorMessage);
		return result;
	}
	
	public static ResponseMessageDetail<UserDTO> getResponseMessageDetailSuccess(UserDTO user) {
		ResponseMessageDetail<UserDTO> result = new ResponseMessageDetail<>();
		result.setData(user);
		return result;
	}
	
	public static ResponseMessage<UserDTO> responseMessageUser(List<UserDTO> users) {
		ResponseMessage<UserDTO> result = new ResponseMessage<>();
		result.setData(users);
		return result;
	}
	public static ResponseMessage<UserDTO> responseMessageUser(int errorCode, String message, String errorMessage) {
		ResponseMessage<UserDTO> result = new ResponseMessage<>();
		result.setErrorCode(errorCode);
		result.setMessage(message);
		result.setErrorMessage(errorMessage);
		result.setData(Collections.emptyList());
		return result;
	}
	
	public static ResponseMessageDetail<Role> responseMessageDetailRoleError(int errorCode, String message, String errorMessage ) {
		ResponseMessageDetail<Role> result = new ResponseMessageDetail<>();
		result.setErrorCode(errorCode);
//		result.setStatus(status.OK);
		result.setMessage(message);
		result.setErrorMessage(errorMessage);
		return result;
	}
	
	public static ResponseMessageDetail<Role> responseMessageDetailRoleSuccess(Role role) {
		ResponseMessageDetail<Role> result = new ResponseMessageDetail<>();
		result.setData(role);
		return result;
	}
	
	public static ResponseMessage<Role> responseMessageRole(List<Role> roles) {
		ResponseMessage<Role> result = new ResponseMessage<>();
		result.setData(roles);
		return result;
	}
	
	public static ResponseMessage<Role> responseMessageRole(int errorCode, String message, String errorMessage) {
		ResponseMessage<Role> result = new ResponseMessage<>();
		result.setErrorCode(errorCode);
		result.setMessage(message);
		result.setErrorMessage(errorMessage);
		result.setData(Collections.emptyList());
		return result;
	}
}
