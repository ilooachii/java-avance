package domaine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Util;

public class Plat {

    public enum Difficulte {

        X, XX, XXX, XXXX, XXXXX;

        public String toString() {
            return name().replace('X', '*');
        }

    }

    public enum Cout {

        $, $$, $$$, $$$$, $$$$$;

        public String toString() {
            return name().replace('$', '€');
        }

    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);

    List<Instruction> listeInstructions = new ArrayList<>();
    Set<IngredientQuantifie> listeIngredients = new HashSet<>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        Util.checkString(nom);
        Util.checkStrictlyPositive(nbPersonnes);
        Util.checkObject(niveauDeDifficulte);
        Util.checkObject(cout);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }
    /**
     * Cette méthode insère l'instruction à la position données en paramètre
     * @param position l'index où ajouter l'instruction
     * @param instruction l'objet à ajouter dans la liste
     * @throws Illegalargumentexception si 
     */
    public void insererInstruction(int position, Instruction instruction) {
        Util.checkStrictlyPositive(position);
        if(position>listeInstructions.size()+1) throw new IllegalArgumentException();
        Util.checkObject(instruction);
        listeInstructions.add(position-1, instruction);
        dureeEnMinutes=dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    public void ajouterInstruction (Instruction instruction) {
        Util.checkObject(instruction);
        listeInstructions.addLast(instruction);
        dureeEnMinutes=dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    public Instruction remplacerInstruction (int position, Instruction instruction) {
        Util.checkStrictlyPositive(position);
        if(position>listeInstructions.size()+1) throw new IllegalArgumentException();
        Util.checkObject(instruction);
        
        int index = position - 1;
        Instruction instAZig = listeInstructions.get(index);
        listeInstructions.set(index, instruction);
        dureeEnMinutes=dureeEnMinutes.minus(instAZig.getDureeEnMinutes());
        dureeEnMinutes=dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return instAZig;
    }

    public Instruction supprimerInstruction (int position) {

        if(position>listeInstructions.size()+1) throw new IllegalArgumentException();

        dureeEnMinutes=dureeEnMinutes.minus(listeInstructions.get(position-1).getDureeEnMinutes());
        return listeInstructions.remove(position-1);

    }

    public List<Instruction> instructions() {
        return Collections.unmodifiableList(listeInstructions);
    }
	
	@Override
	public String toString() {
		String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
		String res = this.nom + "\n\n";
		res += "Pour " + this.nbPersonnes + " personnes\n";
		res += "Difficulté : " + this.niveauDeDifficulte + "\n";
		res += "Coût : " + this.cout + "\n";
		res += "Durée : " + hms + " \n\n";
		res += "Ingrédients :\n";
		for (IngredientQuantifie ing : this.listeIngredients) {
			res += ing + "\n";
		}
		int i = 1;
		res += "\n";
		for (Instruction instruction : this.listeInstructions) {
			res += i++ + ". " + instruction + "\n";
		}
		return res;
	}

}