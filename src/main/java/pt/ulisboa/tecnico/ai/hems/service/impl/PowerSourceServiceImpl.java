package pt.ulisboa.tecnico.ai.hems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;
import pt.ulisboa.tecnico.ai.hems.ext.PowerSourceInfo;
import pt.ulisboa.tecnico.ai.hems.model.PowerSource;
import pt.ulisboa.tecnico.ai.hems.repository.PowerSourceRepository;
import pt.ulisboa.tecnico.ai.hems.service.PowerSourceService;

@Service
@Log
public class PowerSourceServiceImpl implements PowerSourceService {

	@Autowired
	private PowerSourceRepository psRepository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public Long addPowerSource(String name, InputOutput io, String code, String icon) {
		PowerSource powerSource = new PowerSource();
		powerSource.setName(name);
		powerSource.setIo(io);
		powerSource.setCode(code);
		powerSource.setIcon(icon);
		powerSource = psRepository.save(powerSource);
		return powerSource.getId();
	}
	
	@Override
	public PowerSource getPowerSource(Long id) {
		return psRepository.findById(id).get();
	}

	@Override
	public List<PowerSource> getPowerSources(InputOutput io) {
		
		if(io != null) {
			return psRepository.findByIo(io);
		} else {
			return psRepository.findAll();
		}
	}

	@Override
	public void removePowerSource(Long id) {
		psRepository.deleteById(id);
	}
	
	@JmsListener(destination = "power_sources", containerFactory = "jmsFactory")
	public void receivePowerSourceInfo(PowerSourceInfo powerSourceInfo) {
		
		PowerSource powerSource = psRepository.findByCode(powerSourceInfo.getCode());
		
		if(powerSource == null) {
			log.warning("New Power Source detected!");
			log.warning(powerSourceInfo.toString());
			jmsTemplate.convertAndSend("app_notifications", powerSourceInfo);
		}
		
		powerSource.setPower(powerSourceInfo.getPower());
		
		psRepository.save(powerSource);
		
	}


}
