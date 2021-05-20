package pt.ulisboa.tecnico.ai.hems.service;

import java.util.List;

import pt.ulisboa.tecnico.ai.hems.model.Action;

public interface ActionService {

	public Long addAction(String name, String code);
	
	public Action getAction(Long id);
	
	public void removeAction(Long id);

	public List<Action> getActions();
	
}
