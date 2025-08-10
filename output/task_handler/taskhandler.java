import java.io.*;
import java.nio.file.*;

// Class to handle tasks
class TaskHandler {

    private Map<String, List<String>> taskOrder;
    private Map<String, Integer> taskList;

    public TaskHandler() {
        // Define the order of tasks
        taskOrder = new HashMap<>();
        taskOrder.put("RE-FUELING SHIP", Arrays.asList("SHIP DOCKING"));
        taskOrder.put("UNLOADING VESSEL", Arrays.asList("SHIP DOCKING"));
        taskOrder.put("TRUCK LOADING CONTAINER", Arrays.asList("UNLOADING VESSEL"));
        taskOrder.put("CUSTOMS CHECK", Arrays.asList("TRUCK LOADING CONTAINER"));

        // Initialize task list
        taskList = new HashMap<>();
        taskList.put("SHIP DOCKING", 0);
        taskList.put("RE-FUELING SHIP", 0);
        taskList.put("UNLOADING VESSEL", 0);
        taskList.put("TRUCK LOADING CONTAINER", 0);
        taskList.put("CUSTOMS CHECK", 0);
    }

    // Get total number of tasks remaining
    public int tasksRemaining() {
        return taskList.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Add an array of tasks to existing task list
    public void addTask(String[] tasksArray) throws IOException {
        for (String taskFile : tasksArray) {
            String task = new String(Files.readAllBytes(Paths.get(taskFile))).trim();
            taskList.put(task, taskList.getOrDefault(task, 0) + 1);
        }
    }

    // Execute tasks in sensible order
    public void executeTasks() {
        for (String task : taskList.keySet()) {
            if (!taskOrder.containsKey(task)) {
                while (taskList.get(task) > 0) {
                    System.out.println(task + " COMPLETED");
                    removeTask(task);
                }
            } else {
                while (taskList.get(task) > 0) {
                    checkDependencies(task);
                    System.out.println(task + " COMPLETED");
                    removeTask(task);
                }
            }
        }
    }

    // Check for any task dependencies
    private void checkDependencies(String task) {
        List<String> dependenciesRemaining = new ArrayList<>();

        if (taskOrder.containsKey(task)) {
            List<String> dependencies = taskOrder.get(task);
            dependenciesRemaining.addAll(dependencies);
        }

        if (!dependenciesRemaining.isEmpty()) {
            for (String dependency : dependenciesRemaining) {
                checkDependencies(dependency);
            }
        }
    }

    // Remove a task from the task list once it has been completed
    private void removeTask(String task) {
        taskList.put(task, taskList.get(task) - 1);
    }

    @Override
    public String toString() {
        return "<" + tasksRemaining() + " tasks remaining>";
    }