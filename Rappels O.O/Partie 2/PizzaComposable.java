import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class PizzaComposable extends Pizza {
    
    private Client createur;
    private LocalDateTime date;

    public PizzaComposable (Client createur) {
        super("Pizza composable du client " + createur.getNumero(), 
        "Pizza de " + createur.getPrenom() + " " + createur.getNom(), 
        new ArrayList<Ingredient>());
        this.createur = createur;
        this.date = LocalDateTime.now();
    }

    public Client getCreateur() {
        return createur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PizzaComposable other = (PizzaComposable) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return super.toString() + "\nPizza créée le " + formater.format(date);
    }
}
