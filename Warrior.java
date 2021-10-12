package Characters;

import Attacks.MeleeAttack;

import java.awt.Point;

/**
 * Concrete implementation of a Warrior Class
 *
 * @author - Jarod Primeau B00881489
 */
public class Warrior extends Melee{

    /**
     * Warrior Constructor
     * @param name - String, Name of a Character
     * @param strength - Int, Amount of Strength for a Character
     * @param maxHP - Int, Amount of MaxHP for a Character
     * @param position - Point, The position of a character (x,y)
     * @param maxEnergy - Int, Amount of MaxEnergy for a Character
     */
    public Warrior(String name, int strength, int maxHP, Point position, int maxEnergy) {
        super(name, strength, maxHP, position, maxEnergy);

        // Creating the different attacks for the specific characters
        MeleeAttack punch = new MeleeAttack(0,"Punch", 5,3);
        MeleeAttack slam = new MeleeAttack(3,"Slam",5,3);
        MeleeAttack charge = new MeleeAttack(20,"Charge",30,15);

        // Adding it to the attackList
        attackList.add(punch);
        attackList.add(slam);
        attackList.add(charge);
    }
}