package ggoa.dao;

import ggoa.model.Villa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "villas", path = "villas")
public interface VillaRepository extends MongoRepository<Villa, String> {




}