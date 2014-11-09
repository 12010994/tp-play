package services.models;

import java.util.List;

public class Journey {
    public final Long id;
    public final String name;
    public final List<Attendee> attendees;

    public Journey(Long id, String name, List<Attendee> attendees) {
        this.id = id;
        this.name = name;
        this.attendees = attendees;
    }
}
