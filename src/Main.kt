// TaskManager.kt
// Author: Maxwell Iwe
// Date: May 10, 2026
// Task Manager console application - full implementation

data class Task(val id: Int, val name: String, val description: String) {
    // Data class for task objects, auto-generates toString, equals, copy
}

class TaskManager {
    private var tasks = mutableListOf<Task>()  // Mutable collection for tasks
    private var nextId = 1  // Mutable variable for ID counter

    fun addTask(name: String, description: String) {
        if (name.isBlank()) {  // Conditional to check for blank name
            println("Task name cannot be empty.")
            return
        }
        tasks.add(Task(nextId++, name, description))  // Expression to increment ID and add task
        println("Task '$name' added successfully.")
    }

    fun removeTask(name: String) {
        val task = tasks.find { it.name == name }  // Expression to find task by name
        if (task != null) {  // Conditional to check if task exists
            tasks.remove(task)  // Modify collection by removing task
            println("Task '$name' removed successfully.")
        } else {
            println("Task '$name' not found.")
        }
    }

    fun listTasks() {
        if (tasks.isEmpty()) {  // Conditional for empty list
            println("No tasks available.")
        } else {
            for (task in tasks) {  // Loop to iterate and display tasks
                println("ID: ${task.id}, Name: ${task.name}, Description: ${task.description}")
            }
        }
    }
}

fun main() {
    val manager = TaskManager()  // Class instance
    var choice = 0  // Mutable variable for menu choice
    while (choice != 4) {  // Loop for menu until exit
        println("\nTask Manager Menu:")
        println("1. Add Task")
        println("2. Remove Task")
        println("3. List Tasks")
        println("4. Exit")
        print("Enter choice: ")

        choice = readLine()?.toIntOrNull() ?: 0  // Expression for input with safe null handling
        if (choice == 0) {
            println("Invalid choice. Try again.")
            continue
        }

        when (choice) {  // Conditional when for menu options
            1 -> {
                print("Enter task name: ")
                val name = readLine() ?: ""
                print("Enter task description: ")
                val description = readLine() ?: ""
                manager.addTask(name, description)
            }
            2 -> {
                print("Enter task name to remove: ")
                val name = readLine() ?: ""
                manager.removeTask(name)
            }
            3 -> manager.listTasks()
            4 -> println("Exiting...")
            else -> println("Invalid choice. Try again.")
        }
    }
}