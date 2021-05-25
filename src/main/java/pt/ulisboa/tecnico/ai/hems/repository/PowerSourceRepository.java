package pt.ulisboa.tecnico.ai.hems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ulisboa.tecnico.ai.hems.enums.InputOutput;
import pt.ulisboa.tecnico.ai.hems.model.PowerSource;

@Repository
public interface PowerSourceRepository extends JpaRepository<PowerSource, Long> {
	
	public List<PowerSource> findByIo(InputOutput io);
	
	public PowerSource findByCode(String code);

}
