package Characters;

import Attacks.*;

import java.awt.Point;

/**
 * Concrete implementation of a Priest Class
 *
 * @author - Jarod Primeau B00881489
 */
public class Priest extends Caster{
    /**
     * Priest Constructor
     * @param name - String, Name of Character
     * @param intellect - Int, Amount of intellect for character
     * @param maxHP - Int, MaxHP of a character
     * @param position - Point, The position of a character (x,y)
     * @param maxMana - Int, the MaxMana of a character.
     */
    public Priest(String name, int intellect, int maxHP, Point position, int maxMana) {
        super(name, intellect, maxHP, position, maxMana);

        // Creating the different attacks for the specific characters
        MeleeAttack wand = new MeleeAttack(0, "Wand", 3,3);
        DamageSpell smite = new DamageSpell(10, "Smite", 10,7);
        HealingSpell flashHeal = new HealingSpell(20,"Flash Heal", 15,15);
        Resurrection resurrection = new Resurrection();

        // Adding it to the attackList
        attackList.add(wand);
        attackList.add(smite);
        attackList.add(flashHeal);
        attackList.add(resurrection);
    }
}