package domaine;

import java.time.Duration;

public class Instruction {

    String description;
    Duration dureeEnMinutes;

    public Instruction(String description, int dureeEnMinutes) {
        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes(dureeEnMinutes);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    @Override
    public String toString() {
        return "(" + String.format("%02d:%02d", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutesPart()) + ") "
                + description;
    }

}
