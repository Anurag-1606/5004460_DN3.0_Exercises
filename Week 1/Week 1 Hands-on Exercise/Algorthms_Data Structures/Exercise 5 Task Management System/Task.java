class Task {
    int taskId;
    String taskName;
    String status;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
}

class Node {
    Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskList {
    private Node head;

    // Method to add a task to the list
    public void addTask(int taskId, String taskName, String status) {
        Task newTask = new Task(taskId, taskName, status);
        Node newNode = new Node(newTask);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to search for a task by taskId
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Method to traverse and display all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println("Task ID: " + current.task.taskId + 
                               ", Task Name: " + current.task.taskName + 
                               ", Status: " + current.task.status);
            current = current.next;
        }
    }

    // Method to delete a task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false; // List is empty
        }
        
        if (head.task.taskId == taskId) {
            head = head.next; // Remove head
            return true;
        }

        Node current = head;
        Node previous = null;
        while (current != null && current.task.taskId != taskId) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false; // Task not found
        }

        previous.next = current.next; // Bypass the current node
        return true; // Task deleted
    }
}

public class TaskManager {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        // Adding tasks
        taskList.addTask(1, "Task One", "Pending");
        taskList.addTask(2, "Task Two", "In Progress");
        taskList.addTask(3, "Task Three", "Completed");

        // Traversing tasks
        System.out.println("Tasks in the list:");
        taskList.traverseTasks();

        // Searching for a task
        Task foundTask = taskList.searchTask(2);
        if (foundTask != null) {
            System.out.println("Found Task: " + foundTask.taskName + " with status " + foundTask.status);
        } else {
            System.out.println("Task not found.");
        }

        // Deleting a task
        boolean isDeleted = taskList.deleteTask(2);
        if (isDeleted) {
            System.out.println("Task with ID 2 deleted.");
        } else {
            System.out.println("Task with ID 2 not found.");
        }

        // Traversing tasks again
        System.out.println("Tasks in the list after deletion:");
        taskList.traverseTasks();
    }
}
