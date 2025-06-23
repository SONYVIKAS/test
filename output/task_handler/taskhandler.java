  import java.util.HashMap;
  import java.util.Map;

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
          int totalTasks = 0;
          for (Integer value : taskList.values()) {
              totalTasks += value;
          }
          return totalTasks;
      }

      public void addTask(String[] tasksArray) {
          for (String taskFile : tasksArray) {
              String task = readTaskFile(taskFile);
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

      private String readTaskFile(String taskFile) {
          return "SHIP DOCKING";
      }
  }