import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

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

    public void addTask(String[] tasksArray) {
        for (String taskFile : tasksArray) {
            String task = readTask(taskFile);
            taskList.put(task, taskList.get(task) + 1);
        }
    }

    private String readTask(String taskFile) {
        try {
            return new java.util.Scanner(new File(taskFile)).useDelimiter("\\Z").next();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        String[] dependenciesRemaining = new String[0];

        if (taskOrder.containsKey(task)) {
            String[] dependencies = taskOrder.get(task);

            dependenciesRemaining = dependencies;
        }

        if (dependenciesRemaining.length == 0) {
            return;
        } else {
            for (String dependency : dependenciesRemaining) {
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