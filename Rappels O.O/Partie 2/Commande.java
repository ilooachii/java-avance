import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterator<LigneDeCommande> {
    static int numeroSuivant = 1;
    int numero;
    Client client;
    LocalDateTime date;
    ArrayList<LigneDeCommande> lignesCommande = new ArrayList<LigneDeCommande>();
    private int currentIndex = 0;

    public Commande(Client client) {
        if (client.getCommandeEnCours() != null) {
            throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        }
        this.client = client;
        client.enregistrer(this);
        this.numero = numeroSuivant++;
        this.date = LocalDateTime.now();
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean ajouter(Pizza pizza, int quantite) {
        if (client.getCommandeEnCours() != this) return false;

        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            if (ligneDeCommande.getPizza().equals(pizza)) {
                ligneDeCommande.setQuantite(ligneDeCommande.getQuantite() + quantite);
                return true;
            }
        }

        return lignesCommande.add(new LigneDeCommande(pizza, quantite));
    }

    public boolean ajouter(Pizza pizza) {
        return ajouter(pizza, 1);
    }

    public double calculerMontantTotal() {
        double prix = 0.0;
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            prix += ligneDeCommande.calculerPrixTotal();
        }
        return prix;
    }

    public String detailler() {
        StringBuilder sb = new StringBuilder();
        for (LigneDeCommande ligneDeCommande : lignesCommande) {
            sb.append(ligneDeCommande).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean hasNext() {
        return currentIndex < lignesCommande.size();
    }

    @Override
    public LigneDeCommande next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        return lignesCommande.get(currentIndex++);
    }

    @Override
    public void remove() {
        if (currentIndex <= 0) {
            throw new IllegalStateException("Illegal position");
        }
        lignesCommande.remove(--currentIndex);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formatter.format(date);
    }
}
