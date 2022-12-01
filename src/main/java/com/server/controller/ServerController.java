package com.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.server.dto.ServerDto;
import com.server.dto.ZipsDto;
import com.server.repo.ServerRepo;
import com.server.repo.ZipsRepo;

@RestController
public class ServerController  {
	
	@Autowired
	private ServerRepo repo;

	@Autowired
	private ZipsRepo zipRepo;
	
	@GetMapping("/zips")
	@ResponseBody
	public List<ZipsDto> getAllZips() {
			return zipRepo.findAll();
     } 
	
	@GetMapping("/hello")
	public String name() {
		return "success";
	}
	
	@PostMapping("/addServer")
	public String saveServer(@RequestBody ServerDto dto) {
		repo.save(dto);
		return "Server Added";
	}
	
	@GetMapping("/servers")
	@ResponseBody
	public List<ServerDto> getAllServers() {
			return repo.findAll();
     }
	
	@GetMapping("/servers/{id}")
	public Optional<ServerDto> getServerById(@PathVariable String id) {
		try {
			return  repo.findById(id);
		}
		catch(Exception e) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Server Not Found", e);
		}
	}
	
	@GetMapping("/server/{name}")
	public List<ServerDto> getServerByName(@PathVariable String name){
		try {
			return  repo.findByName(name);
		}
		catch(Exception e) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Server Not Found", e);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String DeleteServerById(@PathVariable String id){
		repo.deleteById(id);
		return  id+" Deleted Successfully";
	}

}
