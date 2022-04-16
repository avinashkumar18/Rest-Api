package com.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ServerDto;
import com.server.repo.ServerRepo;

@RestController
public class ServerController {
	
	@Autowired
	private ServerRepo repo;
	
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
	public List<ServerDto> getAllServers() {
         return repo.findAll();
     }
	
	@GetMapping("/servers/{id}")
	public Optional<ServerDto> getServerById(@PathVariable String id){
		return  repo.findById(id);
	}
	
	@GetMapping("/server/{name}")
	public List<ServerDto> getServerByName(@PathVariable String name){
		return  repo.findByName(name);
	}
	
	@DeleteMapping("/delete/{id}")
	public String DeleteServerById(@PathVariable String id){
		repo.deleteById(id);
		return  id+" Deleted Successfully";
	}

}
