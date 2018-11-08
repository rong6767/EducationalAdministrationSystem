package Entity;

/**
 * Created by 闻新 on 2018/6/19.
 */
public class Elective {
    String Sno;
    int Teachingno;
    int Score;

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public int getTeachingno() {
        return Teachingno;
    }

    public void setTeachingno(int teachingno) {
        Teachingno = teachingno;
    }
}
