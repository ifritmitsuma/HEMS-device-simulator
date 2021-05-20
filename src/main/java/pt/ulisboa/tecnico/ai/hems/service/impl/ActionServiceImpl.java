package pt.ulisboa.tecnico.ai.hems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ulisboa.tecnico.ai.hems.model.Action;
import pt.ulisboa.tecnico.ai.hems.repository.ActionRepository;
import pt.ulisboa.tecnico.ai.hems.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;
	
	@Override
	public Long addAction(String name, String code) {
		Action action = new Action();
		action.setName(name);
		action.setCode(code);
		action = actionRepository.save(action);
		return action.getId();
	}

	@Override
	public Action getAction(Long id) {
		return actionRepository.findById(id).get();
	}

	@Override
	public void removeAction(Long id) {
		actionRepository.deleteById(id);
	}

	@Override
	public List<Action> getActions() {
		return actionRepository.findAll();
	}

}
