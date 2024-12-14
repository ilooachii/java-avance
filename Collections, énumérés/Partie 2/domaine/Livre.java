package domaine;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import domaine.Plat.Type;

public class Livre {

    private Map<Type, SortedSet<Plat>> plats = new HashMap<>();

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * 
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        if (!plats.containsKey(plat.getType())) {
            plats.put(plat.getType(), new TreeSet<>(new Comparator<Plat>() {
                public int compare(Plat p1, Plat p2) {
                    return p1.getNom().compareTo(p2.getNom());
                }
            }));
        }
        return plats.get(plat.getType()).add(plat);
    }

    /**
     * Supprime un plat du livre, s'il est dedans.
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer
     * ce type de
     * plat de la Map.
     * 
     * @param plat le plat à supprimer
     * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat(Plat plat) {
        if (!plats.containsKey(plat.getType())) {
            return false;
        }
        if (!plats.get(plat.getType()).contains(plat)) {
            return false;
        }
        boolean platSupp = plats.get(plat.getType()).remove(plat);
        plats.remove(plat.getType());
        return platSupp;
    }

    @Override
    public String toString() {
        String res = "";

        // Parcourir chaque type de plat dans la Map
        for (Type type : plats.keySet()) { // plats est supposé être une Map<TypePlat, List<Plat>>
            res += type + "\n"; // Nom du type en majuscules
            res += "=====\n"; // Séparateur

            // Parcourir les plats pour ce type
            for (Plat plat : plats.get(type)) {
                res += plat.getNom() + "\n"; // Ajout du nom de chaque plat
            }

            res += "\n"; // Ligne vide entre les sections
        }

        return res;
    }
}