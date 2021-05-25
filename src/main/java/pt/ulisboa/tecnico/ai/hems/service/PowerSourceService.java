package pt.ulisboa.tecnico.ai.hems.service;

import java.util.List;

import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;
import pt.ulisboa.tecnico.ai.hems.enums.PowerSourceTypeEnum;
import pt.ulisboa.tecnico.ai.hems.model.PowerSource;

public interface PowerSourceService {
	
	public Long addPowerSource(String name, PowerSourceTypeEnum type, InputOutput io, Double power, String code);

	public PowerSource getPowerSource(Long id);

	public List<PowerSource> getPowerSources(InputOutput io);
	
	public void removePowerSource(Long id);
	
}
