package services.models;

public class Attendee {
    public final Long id;
    public final String name;
    public final Integer availableSeats;

    public Attendee(Long id, String name, Integer availableSeats) {
        this.id = id;
        this.name = name;
        this.availableSeats = availableSeats;
    }
}
