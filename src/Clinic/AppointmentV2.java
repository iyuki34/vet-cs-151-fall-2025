package Clinic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentV2 {
    private final Vet vet;
    private final Owner owner;
    private final Pet pet;
    private final LocalDateTime when;

    public AppointmentV2(Vet vet, Owner owner, Pet pet, LocalDateTime when) {
        this.vet = vet;
        this.owner = owner;
        this.pet = pet;
        this.when = when;
    }

    public Vet getVet() { return vet; }
    public Owner getOwner() { return owner; }
    public Pet getPet() { return pet; }
    public LocalDateTime getWhen() { return when; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("%s with %s (owner: %s) at %s",
                pet.getName(), vet.getName(), owner.getName(), when.format(f));
    }

}
