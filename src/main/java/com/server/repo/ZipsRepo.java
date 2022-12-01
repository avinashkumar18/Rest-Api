package com.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.server.dto.Zips.ZipsDto;

public interface ZipsRepo extends MongoRepository<ZipsDto, String>{
    
}
