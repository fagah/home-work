import java.util.ArrayList;
import java.util.List;

public class App {

    // // This is for testing the PriorityQueue1 implementation
    // public static void main(String[] args) {

    //     // read query points
    //     PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(System.getProperty("user.dir")+"/lib/"+"siftsmall_query.fvecs"));
               
    //     // read point set
    //     PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(System.getProperty("user.dir")+"/lib/"+"siftsmall_base.fvecs"));

    //     int k = 50; // Number of nearest neighbors to find

    //     KNNPriorityQueue1 knn = new KNNPriorityQueue1(pointSet, k);
    //     List<LabelledPoint> nearestNeighbors = new ArrayList<>();

    //     System.out.println(k+" Nearest Neighbors dataset size: "+ pointSet.getPointsList().size()+ " , query set size: "+queryPts.getPointsList().size());
    //     long startTime = System.currentTimeMillis();
    //     for (LabelledPoint query: queryPts.getPointsList()) {

    //         nearestNeighbors = knn.findNN(query);
    //         for (LabelledPoint neighbor : nearestNeighbors) {
    //             System.out.println(neighbor);
    //         }
    //     }

    //     long endTime = System.currentTimeMillis(); // end timer
    //     System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    // }

        public static void main(String[] args) {

        // read query points
        PointSet queryPts = new PointSet(PointSet.read_ANN_SIFT(System.getProperty("user.dir")+"/lib/"+"siftsmall_query.fvecs"));
               
        // read point set
        PointSet pointSet = new PointSet(PointSet.read_ANN_SIFT(System.getProperty("user.dir")+"/lib/"+"siftsmall_base.fvecs"));

        int k = 2000; // Number of nearest neighbors to find

        KNNPriorityQueue2 knn = new KNNPriorityQueue2(pointSet, k);
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
