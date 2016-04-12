package game.data.character

import game.data.StatAffixBase
import game.data.StatCommulator
import game.data.inventroy.Inventory

class CharacterData
{
    public ArrayList<Job> jobsList = new ArrayList<Job>();
    public StatAffixBase characterBaseStats = new StatAffixBase();

    public StatCommulator playerCurrentStats = new StatCommulator();

    public Inventory inventory = new Inventory();

    public String name;

    public int currentExp = 0;
    public int nextLevelExp = 0;

    public int level = 0;

    CharacterData()
    {
        initialize();
    }

    void initialize()
    {
        characterBaseStats.strength = Math.random() * 10;
        characterBaseStats.vitality = Math.random() * 10;
        characterBaseStats.intelligence = Math.random() * 10;
        characterBaseStats.dexterity = Math.random() * 10;
        characterBaseStats.spirit = Math.random() * 10;

        playerCurrentStats.addStatSource(characterBaseStats);
    }

    Job getCurrentJob()
    {
        return jobsList.get(jobsList.size()-1);
    }

    void addJob(Job job)
    {
        jobsList.push(job);
        playerCurrentStats.addStatSource(job);
    }
}
