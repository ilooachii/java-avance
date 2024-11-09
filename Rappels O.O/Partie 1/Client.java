public class Client {
    private int numero;
    private static int numeroSuivant = 1;
    private String nom;
    private String prenom;
    private String telephone;

    public Client(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.numero = numeroSuivant;
        numeroSuivant++;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numero;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (numero != other.numero)
            return false;
        return true;
    }

    @Override
     public String toString() {
	return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
     }
}
