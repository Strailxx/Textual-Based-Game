package Characters;

import Attacks.Attack;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Base RPGCharacter Class - Creates a foundation for all Classes, Damage/Healing Needing To Be Taken and anything else
 * a RPGCharacter Needs.
 *
 * @author - Jarod Primeau B00881489
 */
public abstract class RPGCharacter{
    private String name;
    private int maxHP;
    private Point position;

    protected int currentHP;
    protected int intellect;
    protected int strength;
    protected ArrayList<Attack> attackList = new ArrayList<Attack>();

    /**
     * RPGCharacter Constructor, Taking from Either Caster/Melee
     * @param name - String, Name Of Character
     * @param intellect - Int, Intellect Of Character
     * @param strength - Int, Strength Of Character
     * @param maxHP - Int, MaxHP Of Character
     * @param position - Point, Position Of Character
     */
    public RPGCharacter(String name, int intellect, int strength, int maxHP, Point position){
        this.name = name;
        this.intellect = intellect;
        this.strength = strength;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.position = position;
    }

    public int getCurrentHP(){
        return currentHP;
    }

    public Point getPosition(){
        return position.getLocation();
    }

    public int getMaxHP() {
        return maxHP;
    }

    public String getName() {
        return name;
    }

    /**
     * Move Method - To Move the character by two params (x,y) Uses Points .translate() method to move the character
     * @param x - Int, How far to move on X axis
     * @param y - Int, How far to move on Y Axis
     */
    public void move(int x, int y){
        position.translate(x, y);
    }

    /**
     * TakeDamage Method - Does the small math needed if damage is taken by a Spell/Melee attack.
     * @param damageToTake - Int, How much damage is taken from the Spell/Melee attack.
     * @return - Returns false if characters health reaches 0 on the attack, True if damage is successfully given
     */
    public boolean takeDamage(int damageToTake){
        // Sets HP to 0 and returns false if the damage takes the characters HP past or equal to 0
        if (currentHP - damageToTake <= 0){
            this.currentHP = 0;
            return false;
        }
        // Does the damage and returns true if successfully done.
        else{
            this.currentHP -= damageToTake;
            return true;
        }
    }

    /**
     * Heal Method - Does the small math neeeded if a heal spell is casted.
     * @param healingToDo - Int, How much healing is taken from the Spell.
     * @return - Returns true if the healing heals the Character to their MaxHP, False if Healing is Successfully done.
     */
    public boolean heal(int healingToDo){
        // Sets HP to Max and Returns true if healing does above maxHP.
        if (currentHP + healingToDo > maxHP){
            this.currentHP = maxHP;
            return true;
        }
        // Sets HP with the healing and returns false.
        else{
            this.currentHP += healingToDo;
            return false;
        }
    }

    /**
     * Abstract Method Of Attack to be used by Caster/Melee classes
     * @param characterToAttack - RPGCharacter, Takes input of a RPGCharacter Object of who they want to attack.
     * @param typeOfAttack - Int, What attack they want to use from the attackList (int = the index of attack)
     * @return - Returns Ints based on successful/unsuccessful attacks
     */
    public abstract int attack(RPGCharacter characterToAttack, int typeOfAttack);

    /**
     * getAttacks Method - Puts the attackList ArrayList into a string format with each index on a new line
     * @return - Returns full String made of all Attacks
     */
    public String getAttacks(){
        String result = "";
        for (int i = 0; i < attackList.size(); i++) {
            result += i + " - " + attackList.get(i) + "\n";
        }
        return result;
    }

    public String toString(){
        return name + " (" + this.getClass().getSimpleName() + ") - " + this.getCurrentHP() + "/" + this.getMaxHP();
    }

}