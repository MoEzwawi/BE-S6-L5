package MoEzwawi.BES6L5.entities;

import MoEzwawi.BES6L5.exceptions.BadRequestException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Device events register")
@Getter
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private long eventId;
    @Column(name="date")
    private LocalDate eventDate;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Device device;
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    @Column(name="event_type")
    private String eventType;
    @Column(name="event_result")
    private String eventResult;

    public Event(LocalDate eventDate, Device device, User user, String eventType) {
        this.eventDate = eventDate;
        this.device = device;
        this.user = user;
        this.eventType = eventType;
        switch (eventType) {
            case "PURCHASE", "RETURN", "RETURN_FROM_MAINTENANCE" -> this.eventResult = "available";
            case "ASSIGNMENT" -> this.eventResult = "assigned";
            case "PUT_IN_MAINTENANCE" -> this.eventResult = "in_maintenance";
            case "DISPOSAL" -> this.eventResult = "dismissed";
            default -> throw new BadRequestException("Invalid event type!");
        }
    }
}
