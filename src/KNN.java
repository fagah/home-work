import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class KNN {
    private PointSet pointSet;
    private int k;
    private PriorityQueue1<LabelledPoint> priorityQueue;

    public KNN(PointSet pointSet, int k) {
        this.pointSet = pointSet;
        this.k = k;
        this.priorityQueue = new PriorityQueue1<>(k);
    }

    public void setK(int k) {
        this.k = k;
        priorityQueue.setK(k);
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

    public static void main(String[] args) {

        // read query points
        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(System.getProperty("user.dir")+"/lib/"+"siftsmall_query.fvecs"));
               
        // read point set
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(System.getProperty("user.dir")+"/lib/"+"siftsmall_base.fvecs"));

        int k = 50; // Number of nearest neighbors to find

        KNN knn = new KNN(pointSet, k);
        List<LabelledPoint> nearestNeighbors = new ArrayList<>();

        System.out.println(k+" Nearest Neighbors dataset size: "+ pointSet.getPointsList().size()+ " , query set size: "+queryPts.getPointsList().size());
        long startTime = System.currentTimeMillis();
        for (LabelledPoint query: queryPts.getPointsList()) {

            nearestNeighbors = knn.findNN(query);
            for (LabelledPoint neighbor : nearestNeighbors) {
                System.out.println(neighbor);
            }
        }

        long endTime = System.currentTimeMillis(); // end timer
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
}
