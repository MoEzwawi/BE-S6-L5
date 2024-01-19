package MoEzwawi.BES6L5.entities;

import MoEzwawi.BES6L5.exceptions.BadRequestException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "devices")
@Getter
@ToString
public class Device {
    @Id
    @GeneratedValue
    private long deviceId;
    @Column(name = "type")
    private String deviceType;
    @Setter
    @Column(name = "model")
    private String deviceModel;

    public void setDeviceType(String deviceType) {
        if(deviceType.equals("Computer") || deviceType.equals("Tablet") || deviceType.equals("Smartphone")){
        this.deviceType = deviceType;
        } else {
            throw new BadRequestException("Invalid device type!");
        }
    }
}
