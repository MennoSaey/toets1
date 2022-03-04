package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Collaborator;
import org.springframework.data.repository.CrudRepository;

public interface CollaboratorRepository extends CrudRepository<Collaborator, Integer> {
}
