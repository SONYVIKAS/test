public class UnloadingVessel {
    public static void main(String[] args) {
        int numContainers = 100;
        int numCranes = 4;
        int containersPerCrane = numContainers / numCranes;
        int remainingContainers = numContainers % numCranes;

        for (int i = 1; i <= numCranes; i++) {
            int containersToUnload = containersPerCrane;
            if (remainingContainers > 0) {
                containersToUnload++;
                remainingContainers--;
            }
            System.out.println("Crane " + i + " unloads " + containersToUnload + " containers.");
        }
    }