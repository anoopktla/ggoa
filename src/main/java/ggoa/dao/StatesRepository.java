package ggoa.dao;

import ggoa.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "states", path = "states")
public interface StatesRepository extends MongoRepository<State, String> {


}
