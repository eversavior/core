package game.data;

public class ScoreData
{
    int scoreId = -1;
    String scoreName = "";
    String scoreDescription;
    int scoreValue = 0;

    public ScoreData(int scoreId, String scoreName, int scoreValue, String scoreDescription) {
        this.scoreId = scoreId;
        this.scoreName = scoreName;
        this.scoreDescription = scoreDescription;
        this.scoreValue = scoreValue;
    }

    public ScoreData(int scoreId, String scoreName, int scoreValue) {
        this.scoreId = scoreId;
        this.scoreName = scoreName;
        this.scoreValue = scoreValue;
    }

    public ScoreData(int scoreId, String scoreName) {
        this.scoreId = scoreId;
        this.scoreName = scoreName;
    }

    String toPrintString()
    {
        return scoreName + " " + scoreValue;
    }
}
