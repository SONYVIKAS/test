class TaskHandler:
    def __init__(self):
        self.task_list = {
            'SHIP DOCKING': 0,
            'RE-FUELING SHIP': 0,
            'UNLOADING VESSEL': 0,
            'TRUCK LOADING CONTAINER': 0,
            'CUSTOMS CHECK': 0
        }
        self._task_order = {
            'RE-FUELING SHIP': ['SHIP DOCKING'],
            'UNLOADING VESSEL': ['SHIP DOCKING'],
            'TRUCK LOADING CONTAINER': ['UNLOADING VESSEL'],
            'CUSTOMS CHECK': ['TRUCK LOADING CONTAINER']
        }

    def tasks_remaining(self):
        return sum(self.task_list.values())

    def add_task(self, tasks_array):
        for task_file in tasks_array:
            task = open(task_file).read().rstrip()
            self.task_list[task] += 1

    def execute_tasks(self):
        for task in self.task_list.keys():
            if task not in self._task_order:
                while self.task_list[task] > 0:
                    print(f'{task} COMPLETED')
                    self.remove_task(task)
            else:
                while self.task_list[task] > 0:
                    self.check_dependencies(task)
                    print(f'{task} COMPLETED')
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