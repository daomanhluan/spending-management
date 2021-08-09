package spending.management.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spending.management.app.dto.request.FunctionRoleDTO;
import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.model.Function;
import spending.management.app.service.FunctionService;

@RestController
@RequestMapping("/api")
public class FunctionController {

	@Autowired
	private FunctionService functionService;
	
	@PostMapping("/function/assign")
    public ResponseEntity<ResponseMessageDetail<Function>> assignFunctionForRole(FunctionRoleDTO functionRoleDTO){
    	ResponseMessageDetail<Function> res = functionService.assignFunctionForRole(functionRoleDTO);
    	return new ResponseEntity<ResponseMessageDetail<Function>>(res, res.getStatus());
    }
	
	@GetMapping("/function")
    public ResponseEntity<ResponseMessage<Function>> getAllRole(){
    	ResponseMessage<Function> res = functionService.getAllFunction();
    	return new ResponseEntity<ResponseMessage<Function>>(res, res.getStatus());
    }
	
	@GetMapping("/function/{id}")
    public ResponseEntity<ResponseMessageDetail<Function>> getDetailFunction(@PathVariable int id){
    	ResponseMessageDetail<Function> res = functionService.getDetailFunction(id);
    	return new ResponseEntity<ResponseMessageDetail<Function>>(res, res.getStatus());
    }
	
	@PostMapping("/function")
    public ResponseEntity<ResponseMessageDetail<Function>> saveFunction(@RequestBody Function function){
    	ResponseMessageDetail<Function> res = functionService.saveRole(function);
    	return new ResponseEntity<ResponseMessageDetail<Function>>(res, res.getStatus());
    }
	
	@PutMapping("/function")
    public ResponseEntity<ResponseMessageDetail<Function>> updateRole(@RequestBody Function function){
    	ResponseMessageDetail<Function> res = functionService.updateFunction(function);
    	return new ResponseEntity<ResponseMessageDetail<Function>>(res, res.getStatus());
    }
	
	@DeleteMapping("/function/{id}")
    public ResponseEntity<ResponseMessageDetail<Function>> deleteRole(@PathVariable int id){
    	ResponseMessageDetail<Function> res = functionService.deleteRole(id);
    	return new ResponseEntity<ResponseMessageDetail<Function>>(res, res.getStatus());
    }
}
