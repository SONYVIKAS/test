import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class TaskHandler {

    // Define task order dependencies
    private Map<String, List<String>> taskOrder;
    // Define task list with initial counts
    private Map<String, Integer> taskList;

    public TaskHandler() {
        taskOrder = new HashMap<>();
        taskOrder.put("RE-FUELING SHIP", List.of("SHIP DOCKING"));
        taskOrder.put("UNLOADING VESSEL", List.of("SHIP DOCKING"));
        taskOrder.put("TRUCK LOADING CONTAINER", List.of("UNLOADING VESSEL"));
        taskOrder.put("CUSTOMS CHECK", List.of("TRUCK LOADING CONTAINER"));

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

    // Add tasks from an array of file paths
    public void addTask(String[] tasksArray) throws IOException {
        for (String taskFile : tasksArray) {
            String task = Files.readString(Paths.get(taskFile)).trim();
            taskList.put(task, taskList.get(task) + 1);
        }
    }

    // Execute tasks in order
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

    // Check for task dependencies
    private void checkDependencies(String task) {
        if (taskOrder.containsKey(task)) {
            for (String dependency : taskOrder.get(task)) {
                checkDependencies(dependency);
            }
        }
    }

    // Remove a completed task
    private void removeTask(String task) {
        taskList.put(task, taskList.get(task) - 1);
    }

    @Override
    public String toString() {
        return "<" + tasksRemaining() + " tasks remaining>";
    }
}

public class TestingTaskHandler {

    private TaskHandler job;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws IOException {
        job = new TaskHandler();
        job.addTask(new String[]{"task0.py", "task1.py", "task4.py", "task2.py", "task3.py", "task3.py", "task4.py", "task2.py", "task1.py"});
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddTasks() {
        assertEquals(9, job.tasksRemaining());
        Map<String, Integer> expectedTaskList = new HashMap<>();
        expectedTaskList.put("SHIP DOCKING", 1);
        expectedTaskList.put("UNLOADING VESSEL", 2);
        expectedTaskList.put("CUSTOMS CHECK", 2);
        expectedTaskList.put("TRUCK LOADING CONTAINER", 2);
        expectedTaskList.put("RE-FUELING SHIP", 2);
        assertEquals(expectedTaskList, job.taskList);
    }

    @Test
    public void testRemoveTask() {
        job.removeTask("SHIP DOCKING");
        assertEquals(8, job.tasksRemaining());
        Map<String, Integer> expectedTaskList = new HashMap<>();
        expectedTaskList.put("SHIP DOCKING", 0);
        expectedTaskList.put("UNLOADING VESSEL", 2);
        expectedTaskList.put("CUSTOMS CHECK", 2);
        expectedTaskList.put("TRUCK LOADING CONTAINER", 2);
        expectedTaskList.put("RE-FUELING SHIP", 2);
        assertEquals(expectedTaskList, job.taskList);
    }

    @Test
    public void testExecuteTasks() {
        job.executeTasks();
        assertEquals(0, job.tasksRemaining());
        assertEquals("SHIP DOCKING COMPLETED\nUNLOADING VESSEL COMPLETED\nUNLOADING VESSEL COMPLETED\nCUSTOMS CHECK COMPLETED\nCUSTOMS CHECK COMPLETED\nTRUCK LOADING CONTAINER COMPLETED\nTRUCK LOADING CONTAINER COMPLETED\nRE-FUELING SHIP COMPLETED\nRE-FUELING SHIP COMPLETED\n", outContent.toString());
    }