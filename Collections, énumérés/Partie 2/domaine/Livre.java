package domaine;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import domaine.Plat.Type;
import util.Util;

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
        SortedSet<Plat> plats = this.plats.get(plat.getType());
        if (plats==null) {
            this.plats.put(plat.getType(), new TreeSet<>(new Comparator<Plat>() {
                public int compare(Plat p1, Plat p2) {
                    if (p1.getNiveauDeDifficulte().compareTo(p2.getNiveauDeDifficulte()) != 0) {
                        return p1.getNiveauDeDifficulte().compareTo(p2.getNiveauDeDifficulte());
                    }
                    return p1.getNom().compareTo(p2.getNom());
                }
            }));
        }
        return this.plats.get(plat.getType()).add(plat);
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
        if(platSupp && plats.get(plat.getType()).isEmpty()) {
            plats.remove(plat.getType());
        }
        return platSupp;
    }

    /**
     * Renvoie un ensemble contenant tous les plats d'un certain type.
     * 7
     * L'ensemble n'est pas modifable.
     * 
     * @param type le type de plats souhaité
     * @return l'ensemble des plats
     */
    public SortedSet<Plat> getPlatsParType(Plat.Type type) {
        // L'ensemble renvoyé ne doit pas être modifiable !
        Util.checkObject(type);
        SortedSet<Plat> platsDuType = this.plats.get(type);
        if (platsDuType==null) {
            return null;
        }
        return Collections.unmodifiableSortedSet(plats.get(type));
    }

    /**
    * Renvoie true si le livre contient le plat passé en paramètre. False
    sinon.
    * Pour cette recherche, un plat est identique à un autre si son type, son
    niveau de
    * difficulté et son nom sont identiques.
    * @param plat le plat à rechercher
    * @return true si le livre contient le plat, false sinon.
    */
    public boolean contient(Plat plat) {
        // Ne pas utiliser 2 fois la méthode get() de la map, et ne pas déclarer de variable locale !
        Util.checkObject(plat);
        return plats.containsKey(plat.getType()) && plats.get(plat.getType()).contains(plat);
    }

    /**
     * Renvoie un ensemble contenant tous les plats du livre. Ils ne doivent
     * pas être triés.
     * 
     * @return l'ensemble de tous les plats du livre.
     */
    public Set<Plat> tousLesPlats() {
        // Ne pas utiliser la méthode keySet() ou entrySet() ici !
        Set<Plat> setPlats = new HashSet<>();

        for (SortedSet<Plat> platsParType : plats.values()) {
            setPlats.addAll(platsParType);
        }
        return setPlats;
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