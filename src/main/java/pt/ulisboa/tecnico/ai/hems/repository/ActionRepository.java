package pt.ulisboa.tecnico.ai.hems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ulisboa.tecnico.ai.hems.model.Action;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

	public Action findByCode(String code);
	
}
