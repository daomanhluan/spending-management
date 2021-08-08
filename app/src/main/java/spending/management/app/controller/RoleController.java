package spending.management.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.model.Role;
import spending.management.app.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/role")
    public ResponseEntity<ResponseMessage<Role>> getAllRole(@RequestParam int page, @RequestParam int size ){
    	ResponseMessage<Role> res = roleService.getAllRole(page-1, size);
    	return new ResponseEntity<ResponseMessage<Role>>(res, res.getStatus());
    }
	
	@GetMapping("/role/{id}")
    public ResponseEntity<ResponseMessageDetail<Role>> getDetailRole(@PathVariable int id){
    	ResponseMessageDetail<Role> res = roleService.getDetailRole(id);
    	return new ResponseEntity<ResponseMessageDetail<Role>>(res, res.getStatus());
    }
	
	@PostMapping("/role")
    public ResponseEntity<ResponseMessageDetail<Role>> saveRole(@RequestBody Role role){
		
		System.out.println("role: "+role.toString());
    	ResponseMessageDetail<Role> res = roleService.saveRole(role);
    	return new ResponseEntity<ResponseMessageDetail<Role>>(res, res.getStatus());
    }
	
	@PutMapping("/role")
    public ResponseEntity<ResponseMessageDetail<Role>> updateRole(@RequestBody Role role){
    	ResponseMessageDetail<Role> res = roleService.updateRole(role);
    	return new ResponseEntity<ResponseMessageDetail<Role>>(res, res.getStatus());
    }
	
	@DeleteMapping("/role/{id}")
    public ResponseEntity<ResponseMessageDetail<Role>> deleteRole(@PathVariable int id){
    	ResponseMessageDetail<Role> res = roleService.deleteRole(id);
    	return new ResponseEntity<ResponseMessageDetail<Role>>(res, res.getStatus());
    }
}
