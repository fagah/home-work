import java.util.ArrayList;
import java.util.List;

public class KNNPriorityQueue3 {
    private PointSet pointSet;
    private int k;
    private PriorityQueue3<LabelledPoint> priorityQueue;

    public KNNPriorityQueue3(PointSet pointSet, int k) {
        this.pointSet = pointSet;
        this.k = k;
        this.priorityQueue = new PriorityQueue3<>(k);
    }

    public void setK(int k) {
        this.k = k;
        priorityQueue = new PriorityQueue3<>(k);
    }

    public List<LabelledPoint> findNN(LabelledPoint queryPoint) {
        for (LabelledPoint point : pointSet.getPointsList()) {
            double distance = queryPoint.distanceTo(point);
            if (priorityQueue.size() < k) {
                priorityQueue.offer(point);
            } else {
                LabelledPoint maxDistancePoint = priorityQueue.peek();
                double maxDistance = queryPoint.distanceTo(maxDistancePoint);
                if (distance < maxDistance) {
                    priorityQueue.poll();
                    priorityQueue.offer(point);
                }
            }
        }

        List<LabelledPoint> neighbors = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            neighbors.add(priorityQueue.poll());
        }

        return neighbors;
    }
}

