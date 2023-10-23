import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KNNPriorityQueue2 {
    private PointSet pointSet;
    private int k;
    private PriorityQueue2<LabelledPoint> priorityQueue;

    public KNNPriorityQueue2(PointSet pointSet, int k) {
        this.pointSet = pointSet;
        this.k = k;
        this.priorityQueue = new PriorityQueue2<>(k);
    }

    public void setK(int k) {
        this.k = k;
        priorityQueue = new PriorityQueue2<>(k); // Create a new PriorityQueue2 with updated capacity
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

        // Sort neighbors by distance (optional)
        Collections.sort(neighbors, (p1, p2) -> Double.compare(queryPoint.distanceTo(p1), queryPoint.distanceTo(p2)));

        return neighbors;
    }
}

