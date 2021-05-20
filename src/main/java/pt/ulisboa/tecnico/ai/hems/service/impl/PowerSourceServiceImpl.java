package pt.ulisboa.tecnico.ai.hems.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;
import pt.ulisboa.tecnico.ai.hems.enums.PowerSourceType;
import pt.ulisboa.tecnico.ai.hems.ext.PowerSourceInfo;
import pt.ulisboa.tecnico.ai.hems.model.PowerSource;
import pt.ulisboa.tecnico.ai.hems.repository.PowerSourceRepository;
import pt.ulisboa.tecnico.ai.hems.service.PowerSourceService;

@Service
public class PowerSourceServiceImpl implements PowerSourceService {

	@Autowired
	private PowerSourceRepository psRepository;
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public Long addPowerSource(String name, PowerSourceType type, InputOutput io, Double power, String code) {
		PowerSource powerSource = new PowerSource();
		powerSource.setName(name);
		powerSource.setType(type);
		powerSource.setIo(io);
		powerSource.setCode(code);
		powerSource.setPower(new BigDecimal(power));
		powerSource = psRepository.save(powerSource);
		
		PowerSourceInfo info = new PowerSourceInfo();
		info.setName(name);
		info.setType(type);
		info.setIo(io);
		info.setCode(code);
		info.setPower(power);
		
		jmsTemplate.convertAndSend("power_sources", info);
		
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
		
		PowerSource powerSource = psRepository.findById(id).get();
		psRepository.delete(powerSource);
		
		PowerSourceInfo psInfo = new PowerSourceInfo();
		psInfo.setCode(powerSource.getCode());
		psInfo.setDelete(true);
		jmsTemplate.convertAndSend("power_sources", psInfo);
	
	}

}
