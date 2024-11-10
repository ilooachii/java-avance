import java.util.ArrayList;

public abstract class Pizza {
    
    private final static double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Pizza (String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);
        this.ingredients = ingredients;

        ArrayList<Ingredient> ingredientsVerifies = new ArrayList<Ingredient>();
        for (Ingredient ingredient : ingredients) {
            if (!ingredientsVerifies.contains(ingredient)) {
                ingredientsVerifies.add(ingredient);
            }
            else {
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
            }
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter (Ingredient ingredient) {
        if (ingredients.contains(ingredient)) {
            return false;
        }
        return ingredients.add(ingredient);
    }

    public boolean supprimer (Ingredient ingredient) {
        return ingredients.remove(ingredient);
    }

    public double calculerPrix() {
        double prixTotal = 0;

        for (Ingredient ingredient : ingredients) {
            prixTotal+=ingredient.getPrix();
        }

        return prixTotal + PRIX_BASE;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
        Pizza other = (Pizza) obj;
        if (titre == null) {
            if (other.titre != null)
                return false;
        } else if (!titre.equals(other.titre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
