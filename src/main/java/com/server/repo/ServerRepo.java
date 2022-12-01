package com.server.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.server.dto.Server.ServerDto;

public interface ServerRepo extends MongoRepository<ServerDto, String>{
	
	@Query("{name:'?0'}")
	List<ServerDto> findByName(String name);

}
