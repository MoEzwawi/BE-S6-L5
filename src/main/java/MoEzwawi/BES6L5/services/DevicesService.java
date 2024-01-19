package MoEzwawi.BES6L5.services;

import MoEzwawi.BES6L5.entities.Device;
import MoEzwawi.BES6L5.exceptions.DeviceNotFoundException;
import MoEzwawi.BES6L5.payloads.DeviceRequestDTO;
import MoEzwawi.BES6L5.payloads.ResponseDTO;
import MoEzwawi.BES6L5.repositories.DevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DevicesService {
    @Autowired
    private DevicesRepository devicesRepository;
    public Page<Device> getDevices(int pageN, int size, String orderBy){
        Pageable pageable = PageRequest.of(pageN,size, Sort.by(orderBy));
        return this.devicesRepository.findAll(pageable);
    }
    public Device save(DeviceRequestDTO body){
        Device newDevice = new Device();
        newDevice.setDeviceType(body.type());
        newDevice.setDeviceModel(body.model());
        return devicesRepository.save(newDevice);
    }
    public Device findById(long id){
        return this.devicesRepository.findById(id).orElseThrow(()-> new DeviceNotFoundException(id));
    }
    public void deleteDevice(long id){
        Device found = this.findById(id);
        this.devicesRepository.delete(found);
    }
}
