package game.data

/**
 * Created on 11.04.2016.
 */
class PlayerDataBase
{
    private ArrayList<PlayerData> playersList = new ArrayList<>();
    private HashMap<String, PlayerData> nameToPlayer = new HashMap<String, PlayerData>();

    public void add(PlayerData playerData)
    {
        playerData.id = playersList.size();

        playersList.add(playerData);
        nameToPlayer.put(playerData.characterData.name, playerData);
    }

    public PlayerData getRandomBut(String name)
    {
        int count = playersList.size();
        PlayerData playerData;

        while(true)
        {
            int index = Math.random() * count;

            playerData = playersList.get(index);

            if(playerData.accountName != name)
                break;
        }

        return playerData;
    }

    public PlayerData getByAccountName(String accountName)
    {
        PlayerData playerData = null;

        playersList.each
        {
            it ->
            if(it.accountName == accountName)
            {
                playerData = it;
            }
        }

        return playerData;
    }

    public PlayerData getByName(String name)
    {
        PlayerData playerData = nameToPlayer.get(name);

        return playerData;
    }

    public PlayerData get(PlayerData playerData)
    {
        return playersList.get(playerData);
    }

    public int playersCount()
    {
        return playersList.size();
    }
}
