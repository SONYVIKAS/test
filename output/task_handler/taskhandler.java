import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskHandler {

    private Map<String, Integer> taskList;
    private Map<String, String[]> taskOrder;

    public TaskHandler() {
        taskList = new HashMap<String, Integer>();
        taskList.put("SHIP DOCKING", 0);
        taskList.put("RE-FUELING SHIP", 0);
        taskList.put("UNLOADING VESSEL", 0);
        taskList.put("TRUCK LOADING CONTAINER", 0);
        taskList.put("CUSTOMS CHECK", 0);

        taskOrder = new HashMap<String, String[]>();
        taskOrder.put("RE-FUELING SHIP", new String[] {"SHIP DOCKING"});
        taskOrder.put("UNLOADING VESSEL", new String[] {"SHIP DOCKING"});
        taskOrder.put("TRUCK LOADING CONTAINER", new String[] {"UNLOADING VESSEL"});
        taskOrder.put("CUSTOMS CHECK", new String[] {"TRUCK LOADING CONTAINER"});
    }

    public int tasksRemaining() {
        int total = 0;
        for (Integer count : taskList.values()) {
            total += count;
        }
        return total;
    }

    public void addTask(String[] taskFiles) {
        for (String taskFile : taskFiles) {
            String task = readFile(taskFile).trim();
            taskList.put(task, taskList.get(task) + 1);
        }
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
        if (dependencies == null) {
            return;
        }
        for (String dependency : dependencies) {
            checkDependencies(dependency);
        }
    }

    public void removeTask(String task) {
        taskList.put(task, taskList.get(task) - 1);
    }

    private String readFile(String filename) {
        try {
            File file = new File(filename);
            return new String(java.nio.file.Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    public String toString() {
        return "<" + tasksRemaining() + " tasks remaining>";
    }