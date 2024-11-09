import java.util.ArrayList;

public class PizzaComposee extends Pizza {
    private final static int REMISE = 15;

    public PizzaComposee(String titre, String description, ArrayList<Ingredient> ingredients) {
        super(titre, description, ingredients);
    }

    public boolean ajouter(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    public boolean supprimer(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");

    }

    public double calculerPrix() {
        double prixDeBase = super.calculerPrix();
        double prix = Math.ceil(prixDeBase * (1-(REMISE/100.0)));
        return prix;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
