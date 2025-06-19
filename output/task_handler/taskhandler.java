import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TaskHandler {
    private Map<String, Integer> taskList;
    private Map<String, String[]> taskOrder;

    public TaskHandler() {
        taskList = new HashMap<>();
        taskOrder = new HashMap<>();

        taskOrder.put("RE-FUELING SHIP", new String[] {"SHIP DOCKING"});
        taskOrder.put("UNLOADING VESSEL", new String[] {"SHIP DOCKING"});
        taskOrder.put("TRUCK LOADING CONTAINER", new String[] {"UNLOADING VESSEL"});
        taskOrder.put("CUSTOMS CHECK", new String[] {"TRUCK LOADING CONTAINER"});

        taskList.put("SHIP DOCKING", 0);
        taskList.put("RE-FUELING SHIP", 0);
        taskList.put("UNLOADING VESSEL", 0);
        taskList.put("TRUCK LOADING CONTAINER", 0);
        taskList.put("CUSTOMS CHECK", 0);
    }

    public int tasksRemaining() {
        int total = 0;
        for (int count : taskList.values()) {
            total += count;
        }
        return total;
    }

    public void addTask(String[] tasksArray) {
        for (String taskFile : tasksArray) {
            String task = readTaskFile(taskFile);
            taskList.put(task, taskList.get(task) + 1);
        }
    }

    private String readTaskFile(String filename) {
        File file = new File(filename);
        try {
            return new String(java.nio.file.Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public void checkDependencies(String task) {
        String[] dependencies = taskOrder.get(task);
        if (dependencies!= null) {
            for (String dependency : dependencies) {
                checkDependencies(dependency);
            }
        }
    }

    public void removeTask(String task) {
        taskList.put(task, taskList.get(task) - 1);
    }

    @Override
    public String toString() {
        return "<" + tasksRemaining() + " tasks remaining>";
    }