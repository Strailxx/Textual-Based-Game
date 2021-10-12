package Characters;

import java.awt.Point;

/**
 * Base Melee Class - Creates a foundation for all Melee Classes.
 *
 * @author - Jarod Primeau B00881489
 */
public abstract class Melee extends RPGCharacter{
    private int maxEnergy;
    private int currentEnergy;

    /**
     * The constructor for a Melee Class, Takes from "Warrior" Currently and creates a new RPGCharacter Object
     * @param name - String, Name of a Character
     * @param strength - Int, Amount of Strength for a Character
     * @param maxHP - Int, Amount of MaxHP for a Character
     * @param position - Point, The position of a character (x,y)
     * @param maxEnergy - Int, Amount of MaxEnergy for a Character
     */
    public Melee(String name, int strength, int maxHP, Point position, int maxEnergy){
        // Passes 1 as intellect.
        super(name, 1, strength, maxHP, position);
        this.maxEnergy = maxEnergy;
        this.currentEnergy = maxEnergy;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    /**
     * The attack method in which it takes from the abstract method from RPGCharacter, Does an attack with interactWithTarget
     * based on the attacked used.
     * @param characterToAttack - RPGCharacter, Takes input of a RPGCharacter Object of who they want to attack.
     * @param typeOfAttack - Int, What attack they want to use from the attackList (int = the index of attack)
     * @return - Returns either a negative number (-1 For OOB Index, -2 for Out Of Range, -3 For Not Enough Energy, Or
     * returns the enemies HP if an attack is made.
     */
    @Override
    public int attack(RPGCharacter characterToAttack, int typeOfAttack) {
        // Attack Chosen is Out Of Array Index
        if (typeOfAttack > attackList.size()){
            return -1;
        }
        // Attack Chosen Cannot Be Cast Due To Being Out Of Range
        else if (attackList.get(typeOfAttack).getRange() < this.getPosition().distance(characterToAttack.getPosition())){
            return -2;
        }
        // Attack Cannot Be Cast Due To Not Having Enough Mana
        else if (attackList.get(typeOfAttack).getCost() > currentEnergy){
            return -3;
        }
        // Attack successfully Casted
        else{
            // Minuses the amount of Energy Used
            currentEnergy -= attackList.get(typeOfAttack).getCost();
            // Interacts with enemy and modifies by Strength
            attackList.get(typeOfAttack).interactWithTarget(characterToAttack, this.strength);
            return characterToAttack.getCurrentHP();
        }
    }

    @Override
    public String toString(){
        return this.getName() + " (" + this.getClass().getSimpleName() + ") - " + this.getCurrentHP() + "/" + this.getMaxHP() + "\n" + "Energy " + currentEnergy + "/" + maxEnergy;
    }
}