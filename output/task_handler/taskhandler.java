import unittest
import sys
from io import StringIO


class TaskHandler:
    def __init__(self):
        self._task_order = {
            "RE-FUELING SHIP": ["SHIP DOCKING"],
            "UNLOADING VESSEL": ["SHIP DOCKING"],
            "TRUCK LOADING CONTAINER": ["UNLOADING VESSEL"],
            "CUSTOMS CHECK": ["TRUCK LOADING CONTAINER"],
        }
        self.task_list = {
            "SHIP DOCKING": 0,
            "RE-FUELING SHIP": 0,
            "UNLOADING VESSEL": 0,
            "TRUCK LOADING CONTAINER": 0,
            "CUSTOMS CHECK": 0,
        }

    def tasks_remaining(self):
        return sum(self.task_list.values())

    def add_task(self, tasks_array):
        for task_file in tasks_array:
            task = open(task_file).read().rstrip()
            self.task_list[task] = self.task_list.get(task, 0) + 1

    def execute_tasks(self):
        for task in self.task_list.keys():
            if task not in self._task_order:
                while self.task_list[task] > 0:
                    print(f"{task} COMPLETED")
                    self.remove_task(task)
            else:
                while self.task_list[task] > 0:
                    self.check_dependencies(task)
                    print(f"{task} COMPLETED")
                    self.remove_task(task)

    def check_dependencies(self, task):
        dependencies_remaining = []

        if task in self._task_order:
            dependencies = self._task_order[task]

            for dependency in dependencies:
                dependencies_remaining.append(dependency)

        if dependencies_remaining == []:
            return
        else:
            for dependency in dependencies_remaining:
                self.check_dependencies(dependency)

    def remove_task(self, task):
        self.task_list[task] -= 1

    def __repr__(self):
        return f"<{self.tasks_remaining()} tasks remaining>"


class TestingTaskHandler(unittest.TestCase):
    def setUp(self):
        self.job = TaskHandler()
        self.job.add_task(
            ["task0.py", "task1.py", "task4.py", "task2.py", "task3.py", "task3.py", "task4.py", "task2.py", "task1.py"]
        )
        self.held = sys.stdout
        sys.stdout = StringIO()

    def test_add_tasks(self):
        self.assertEqual(self.job.tasks_remaining(), 9)
        self.assertEqual(
            self.job.task_list,
            {"SHIP DOCKING": 1, "UNLOADING VESSEL": 2, "CUSTOMS CHECK": 2, "TRUCK LOADING CONTAINER": 2, "RE-FUELING SHIP": 2},
        )

    def test_remove_task(self):
        self.job.remove_task("SHIP DOCKING")
        self.assertEqual(self.job.tasks_remaining(), 8)
        self.assertEqual(
            self.job.task_list,
            {"SHIP DOCKING": 0, "UNLOADING VESSEL": 2, "CUSTOMS CHECK": 2, "TRUCK LOADING CONTAINER": 2, "RE-FUELING SHIP": 2},
        )

    def test_execute_tasks(self):
        self.job.execute_tasks()
        self.assertEqual(self.job.tasks_remaining(), 0)
        self.assertEqual(
            sys.stdout.getvalue(),
            "SHIP DOCKING COMPLETED\nUNLOADING VESSEL COMPLETED\nUNLOADING VESSEL COMPLETED\nCUSTOMS CHECK COMPLETED\nCUSTOMS CHECK COMPLETED\nTRUCK LOADING CONTAINER COMPLETED\nTRUCK LOADING CONTAINER COMPLETED\nRE-FUELING SHIP COMPLETED\nRE-FUELING SHIP COMPLETED\n",
        )


if __name__ == "__main__":