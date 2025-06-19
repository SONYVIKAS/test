public class UnloadingVessel {
    public static void main(String[] args) {
        int numContainers = 100;
        int numCranes = 5;
        int containersPerCrane = numContainers / numCranes;
        int remainingContainers = numContainers % numCranes;

        for (int crane = 1; crane <= numCranes; crane++) {
            int containersToUnload = containersPerCrane;
            if (remainingContainers > 0) {
                containersToUnload++;
                remainingContainers--;
            }

            System.out.println("Crane " + crane + " will unload " + containersToUnload + " containers.");
        }
    }