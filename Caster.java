package Characters;

import Attacks.DamageSpell;
import Attacks.HealingSpell;
import Attacks.MeleeAttack;

import java.awt.Point;

/**
 * Base Caster Class - Creates a foundation for all Spell Caster Classes.
 *
 * @author - Jarod Primeau B00881489
 */
public abstract class Caster extends RPGCharacter{
    private int maxMana;
    private int currentMana;

    /**
     * The constructor for a Caster Class, Takes from either "Mage or Priest" Currently and creates a new RPGCharacter Object
     * @param name - String, Name of the character
     * @param intellect - Int, Amount of intellect for character
     * @param maxHP - Int, MaxHP of a character
     * @param position - Point, The position of a character (x,y)
     * @param maxMana - Int, the MaxMana of a character.
     */
    public Caster(String name, int intellect, int maxHP, Point position, int maxMana){
        // Passes 1 as Strength.
        super(name, intellect, 1, maxHP, position);
        this.maxMana = maxMana;
        this.currentMana = maxMana;
    }

    public int getCurrentMana(){
        return currentMana;
    }

    /**
     * The attack method in which it takes from the abstract method from RPGCharacter, Does an attack with interactWithTarget
     * based on the attacked used.
     *
     * @param characterToAttack - RPGCharacter, Takes input of a RPGCharacter Object of who they want to attack.
     * @param typeOfAttack - Int, What attack they want to use from the attackList (int = the index of attack)
     * @return - Returns either a negative number (-1 For OOB Index, -2 for Out Of Range, -3 For Not Enough Mana, Or
     * returns the enemies HP if an attack/heal is made.
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
        else if (attackList.get(typeOfAttack).getCost() > currentMana){
            return -3;
        }
        // Attack/Spell successfully Casted
        else{
            // Minuses the mana needed to cast the spell that's chosen
            currentMana -= attackList.get(typeOfAttack).getCost();
            // If it is a MeleeAttack
            if (attackList.get(typeOfAttack) instanceof MeleeAttack){
                // Interacts with enemy and modifies by 0 due to being a caster melee attack
                attackList.get(typeOfAttack).interactWithTarget(characterToAttack, 0);
                return characterToAttack.getCurrentHP();
            }
            // If its a Damage Spell
            else if(attackList.get(typeOfAttack) instanceof DamageSpell){
                // Interacts with enemy and modifies by Intellect
                attackList.get(typeOfAttack).interactWithTarget(characterToAttack, this.intellect);
                return characterToAttack.getCurrentHP();
            }
            // If its a Healing Spell.
            else if(attackList.get(typeOfAttack) instanceof HealingSpell){
                // Interacts with oneself and modifies by intellect.
                attackList.get(typeOfAttack).interactWithTarget(this, this.intellect);
                return characterToAttack.getCurrentHP();
            }
        }
        // This is to make the method happy as it needs a return outside of an else statement for some reason
        return this.getCurrentHP();
    }

    @Override
    public String toString(){
        return this.getName() + " (" + this.getClass().getSimpleName() + ") - " + this.getCurrentHP() + "/" + this.getMaxHP() + "\n" + "Mana: " + currentMana + "/" + maxMana;
    }
}