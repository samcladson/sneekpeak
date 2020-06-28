package com.sneekpeak.mystore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sneekpeak.mystore.model.User;
import com.sneekpeak.mystore.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/allUser")
	public List<User> getAllUser() {
		return userService.findAll();
	}

	@GetMapping("/user/{email}-{password}")
	public Object getUser(@PathVariable String email, @PathVariable String password) {
		Optional<User> data = userService.findById(email);
		if (data != null) {
			if (data.get().getPassword().equals(password)) {
				HashMap<String, Object> res = new HashMap<>();
				res.put("Status", new ResponseEntity<>(HttpStatus.OK));
				res.put("data", data);
				return res;
			} else {
				HashMap<String, Object> res = new HashMap<>();
				res.put("message", "User mail or password incorrect!");
				res.put("Status", new ResponseEntity<>(HttpStatus.NOT_FOUND));
				return res;
			}
		} else {
			HashMap<String, Object> res = new HashMap<>();
			res.put("message", "User not found");
			res.put("Status", new ResponseEntity<>(HttpStatus.NOT_FOUND));
			return res;
		}

	}

	@PostMapping("/addUser")
	public void getAllUser(@RequestBody User user) {
		userService.save(user);
	}
}
