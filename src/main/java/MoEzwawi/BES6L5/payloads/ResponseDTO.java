package MoEzwawi.BES6L5.payloads;

import java.time.LocalDateTime;

public class ResponseDTO{
    private long id;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ResponseDTO(long id) {
        this.id = id;
    }
}
