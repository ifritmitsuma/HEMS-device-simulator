package pt.ulisboa.tecnico.ai.hems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ulisboa.tecnico.ai.hems.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

	public State findByValue(String value);

}
