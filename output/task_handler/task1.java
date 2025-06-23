  class UnloadingVessel {
    private String vesselName;
    private int numContainers;
    private int numContainersUnloaded;

    public UnloadingVessel(String vesselName, int numContainers) {
        this.vesselName = vesselName;
        this.numContainers = numContainers;
        this.numContainersUnloaded = 0;
    }

    public void unloadContainer() {
        numContainersUnloaded++;
    }

    public int getnumContainersUnloaded() {
        return numContainersUnloaded;
    }

    public int getnumContainers() {
        return numContainers;
    }

    public String getVesselName() {
        return vesselName;
    }
  }