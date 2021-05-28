package pt.ulisboa.tecnico.ai.hems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.ulisboa.tecnico.ai.hems.model.Action;
import pt.ulisboa.tecnico.ai.hems.model.State;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

	public Action findByCode(String code);

	@Query("SELECT a FROM Action a WHERE a.stateAfter = ?1")
	public Action findByStateAfter(State stateAfter);
	
}
