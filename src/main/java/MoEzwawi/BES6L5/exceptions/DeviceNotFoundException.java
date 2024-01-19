package MoEzwawi.BES6L5.exceptions;

public class DeviceNotFoundException extends RuntimeException{
    public DeviceNotFoundException(long id) {
        super("Device n° "+id+" not found!");
    }
}
