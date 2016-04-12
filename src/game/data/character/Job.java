package game.data.character;

import game.data.StatAffixBase;
import groovy.transform.AutoClone;

@AutoClone
public class Job extends StatAffixBase implements Cloneable
{
    public String description = "";
    public String jobName;
    public int jobId;

    public int currentExp = 0;
    public int nextLevelExp = 0;

    public int level = 0;

    public Job() {
    }

    public Job(int jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    public Job(int jobId, String jobName, int strength, int dexterity, int intelligence, int vitality, int spirit)
    {
        this.jobId = jobId;
        this.jobName = jobName;

        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.vitality = vitality;
        this.spirit = spirit;
    }

    @Override
    protected Job clone() throws CloneNotSupportedException {

        return (Job) super.clone();
    }
}
