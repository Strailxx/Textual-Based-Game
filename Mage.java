package Characters;

import Attacks.*;
import java.awt.Point;

/**
 * Concrete implementation of a Mage Class
 *
 * @author - Jarod Primeau B00881489
 */
public class Mage extends Caster{
    /**
     * Mage Constructor
     * @param name - String, Name of Character
     * @param intellect - Int, Amount of intellect for character
     * @param maxHP - Int, MaxHP of a character
     * @param position - Point, The position of a character (x,y)
     * @param maxMana - Int, the MaxMana of a character.
     */
    public Mage(String name, int intellect, int maxHP, Point position, int maxMana) {
        super(name, intellect, maxHP, position, maxMana);

        // Creating the different attacks for the specific characters
        MeleeAttack staff = new MeleeAttack(0, "Staff", 3,3);
        DamageSpell fireball = new DamageSpell(20, "Fire Ball", 10,20);
        DamageSpell frostball = new DamageSpell(15,"Frost Ball", 7,20);
        DamageSpell lightning = new DamageSpell(30, "Lightning", 15, 20);

        // Adding it to the attackList
        attackList.add(staff);
        attackList.add(fireball);
        attackList.add(frostball);
        attackList.add(lightning);
    }
}