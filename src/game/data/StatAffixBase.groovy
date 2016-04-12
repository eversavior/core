package game.data

/**
 * Created on 12.04.2016.
 */
class StatAffixBase
{
    int getStrength() {
        return strength
    }

    void setStrength(int strength) {
        this.strength = strength
    }

    int getDexterity() {
        return dexterity
    }

    void setDexterity(int dexterity) {
        this.dexterity = dexterity
    }

    int getIntelligence() {
        return intelligence
    }

    void setIntelligence(int intelligence) {
        this.intelligence = intelligence
    }

    int getVitality() {
        return vitality
    }

    void setVitality(int vitality) {
        this.vitality = vitality
    }

    int getSpirit() {
        return spirit
    }

    void setSpirit(int spirit) {
        this.spirit = spirit
    }

    StatAffixBase() {
    }

    StatAffixBase(int strength, int dexterity, int intelligence, int vitality, int spirit) {
        this.strength = strength
        this.dexterity = dexterity
        this.intelligence = intelligence
        this.vitality = vitality
        this.spirit = spirit
    }
    protected int strength = 0;
    protected int dexterity = 0;
    protected int intelligence = 0;
    protected int vitality = 0;
    protected int spirit = 0;


}
