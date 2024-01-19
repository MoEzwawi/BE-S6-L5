package MoEzwawi.BES6L5.exceptions;

import java.time.LocalDateTime;

public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
