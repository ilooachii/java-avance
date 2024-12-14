package main;

import domaine.*;
import domaine.Plat.Cout;
import domaine.Plat.Difficulte;
import domaine.Plat.Type;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {

		Plat plat = null;
		plat = new Plat("Waterzooi", 4, Difficulte.XX, Cout.$$$, Type.PLAT);

		Instruction instruction = new Instruction("Couper les légumes", 15);
		try {
			plat.insererInstruction(0, instruction);
		} catch (IllegalArgumentException iae) {
		}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Faire revenir les légumes", 5);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet", 50);
		try {
			plat.insererInstruction(4, instruction);
		} catch (IllegalArgumentException iae) {
		}
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser légèrement refroidir", 3);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Ajouter la crème et servir", 0);
		plat.ajouterInstruction(instruction);
		instruction = new Instruction("Laisser mijoter jusqu'à cuisson du poulet", 67);
		plat.remplacerInstruction(3, instruction);
		instruction = new Instruction("Ajouter le poulet", 0);
		plat.insererInstruction(3, instruction);
		plat.supprimerInstruction(5);

		Iterator<Instruction> instructionIterator = plat.instructions().iterator();
		while (instructionIterator.hasNext()) {
			instruction = instructionIterator.next();
		}
		try {
			instructionIterator.remove();
		} catch (UnsupportedOperationException uoe) {
		}

		System.out.println(plat);

		Livre livre = new Livre();
		livre.ajouterPlat(plat);
		livre.ajouterPlat(new Plat("Croquettes au fromage", 4, Difficulte.XXX,
		Cout.$$, Plat.Type.ENTREE));
		System.out.println(livre);
		livre.supprimerPlat(new Plat("Toasts aux champignons", 5, Difficulte.XXX, Cout.$$$, Plat.Type.ENTREE));
		System.out.println(livre);
	}
}
