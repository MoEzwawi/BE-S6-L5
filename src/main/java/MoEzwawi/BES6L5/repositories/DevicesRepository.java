package MoEzwawi.BES6L5.repositories;

import MoEzwawi.BES6L5.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends JpaRepository<Device,Long> {
}
