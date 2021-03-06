package spending.management.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spending.management.app.config.UserDetailsCustom;
import spending.management.app.dto.request.UserRoleDTO;
import spending.management.app.dto.response.ResponseMessage;
import spending.management.app.dto.response.ResponseMessageDetail;
import spending.management.app.dto.response.UserDTO;
import spending.management.app.model.User;
import spending.management.app.payload.LoginRequest;
import spending.management.app.payload.LoginResponse;
import spending.management.app.payload.RandomStuff;
import spending.management.app.security.JwtTokenProvider;
import spending.management.app.service.UserService;
@RestController
@RequestMapping("/api")
public class UserController {

	 	@Autowired
	    AuthenticationManager authenticationManager;
	    @Autowired
	    private JwtTokenProvider tokenProvider;
	    @Autowired
	    UserService userService;
	    
	    @GetMapping("/user/infor")
	    public ResponseEntity<ResponseMessageDetail<UserDTO>> getUserInfo(){
	    	ResponseMessageDetail<UserDTO> res = userService.getUserInfo();
	    	return new ResponseEntity<ResponseMessageDetail<UserDTO>>(res, res.getStatus());
	    }
	    
	    @GetMapping("/user")
	    public ResponseEntity<ResponseMessage<UserDTO>> getAllUser(@RequestParam int page, @RequestParam int size ){
	    	ResponseMessage<UserDTO> res = userService.getAllUser(page-1, size);
	    	return new ResponseEntity<ResponseMessage<UserDTO>>(res, res.getStatus());
	    }
	    
	    @GetMapping("/user/{id}")
	    public ResponseEntity<ResponseMessageDetail<UserDTO>> getDetailUser(@PathVariable Long id){
	    	ResponseMessageDetail<UserDTO> res = userService.getDetailUser(id);
	    	return new ResponseEntity<ResponseMessageDetail<UserDTO>>(res, res.getStatus());
	    }
	    
	    @PostMapping("/user/new")
	    public ResponseEntity<ResponseMessageDetail<UserDTO>> saveUser(@RequestBody User user){
	    	ResponseMessageDetail<UserDTO> res = userService.saveUser(user);
	    	return new ResponseEntity<ResponseMessageDetail<UserDTO>>(res, res.getStatus());
	    }
	    
	    @PutMapping("/user/change-password")
	    public ResponseEntity<ResponseMessageDetail<UserDTO>> changePassword(@RequestBody User user){
	    	ResponseMessageDetail<UserDTO> res = userService.updatePassword(user);
	    	return new ResponseEntity<ResponseMessageDetail<UserDTO>>(res, res.getStatus());
	    }
	    
	    @PostMapping("/user/update-role")
	    public ResponseEntity<ResponseMessageDetail<UserDTO>> changeRole(@RequestBody UserRoleDTO userRole){
	    	ResponseMessageDetail<UserDTO> res = userService.updateRole(userRole);
	    	return new ResponseEntity<ResponseMessageDetail<UserDTO>>(res, res.getStatus());
	    }
	    
	    @PostMapping("/login")
	    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {

	        // X??c th???c t??? username v?? password.
	    	System.out.println("loginRequest: "+loginRequest.toString());
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );

	        // N???u kh??ng x???y ra exception t???c l?? th??ng tin h???p l???
	        // Set th??ng tin authentication v??o Security Context
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Tr??? v??? jwt cho ng?????i d??ng.
	        String jwt = tokenProvider.generateToken((UserDetailsCustom) authentication.getPrincipal());
	        return new LoginResponse(jwt);
	    }

	    // Api /api/random y??u c???u ph???i x??c th???c m???i c?? th??? request
	    @GetMapping("/random")
	    public RandomStuff randomStuff(){
	        return new RandomStuff("JWT H???p l??? m???i c?? th??? th???y ???????c message n??y");
	    }
}
