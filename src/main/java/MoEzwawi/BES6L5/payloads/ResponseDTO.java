package MoEzwawi.BES6L5.payloads;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResponseDTO{
    private long id;
    private LocalDateTime timestamp;

    public ResponseDTO(long id) {
        this.id = id;
        this.timestamp = LocalDateTime.now();
    }
}
