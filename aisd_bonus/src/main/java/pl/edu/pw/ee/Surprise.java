package pl.edu.pw.ee;

public class Surprise {
    private int maxPathValue = 0;

    public int countMaxSumPoints(int[] points) {

        int position = 0;

        while (true) {
            position = findMax(points, position);

            maxPathValue += points[position];

            if(position >= points.length - 1) {
                break;
            }
        }

        return maxPathValue;
    }

    private int findMax(int[] points, int position) {
        int max = -99999;

        int minVal = -99999;
        int minValPos = position;

        for (int i = position; i < position + 6; i++) {
            if (points[i] > 0) {
                return i;
            }
            
            if (points.length - 1 == i) {
                break;
            }
        }

        for (int i = position; i < position + 6; i++) {
            if (minVal < points[i]) {
                minValPos = i;
            }

            if (points.length - 1 == i) {
                break;
            }
        }

        return minValPos;
    }
}
