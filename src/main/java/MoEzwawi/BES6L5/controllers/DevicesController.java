package MoEzwawi.BES6L5.controllers;

import MoEzwawi.BES6L5.entities.Device;
import MoEzwawi.BES6L5.payloads.DeviceRequestDTO;
import MoEzwawi.BES6L5.payloads.ResponseDTO;
import MoEzwawi.BES6L5.services.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    private DevicesService devicesService;
    @GetMapping
    public Page<Device> getAll(@RequestParam(defaultValue = "0")int pageNumber,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy){
        return this.devicesService.getDevices(pageNumber, size, orderBy);
    }
    @GetMapping("/{id}")
    public Device getById(@PathVariable long id){
        return devicesService.findById(id);
    }
    @PostMapping
    public ResponseDTO save(@RequestBody DeviceRequestDTO body){
        Device newDevice = this.devicesService.save(body);
        return new ResponseDTO(newDevice.getDeviceId());
    }
    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getDeviceByIdAndDelete(@PathVariable long deviceId) {
        devicesService.deleteDevice(deviceId);
    }
}
