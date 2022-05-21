package Projekt2;

import java.util.Comparator;

public class CompareScores implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Score s1=(Score) o1;
        Score s2=(Score) o2;
        return (int) (s2.score-s1.score);
    }
}
