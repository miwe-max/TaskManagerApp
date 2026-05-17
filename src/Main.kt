// TaskManager.kt
// Author: Maxwell Iwe
// Date: May 10, 2026
// Task Manager console application - full implementation


// Data class to represent a task with immutable properties
data class Task(
    val id: Int, // Unique identifier for the task
    val name: String, // Task name (immutable to prevent changes)
    val description: String, // Task description
    val status: String = "Pending" // Task status (default: Pending)
) {
    // Override toString for clean, formatted output when listing tasks
    override fun toString(): String {
        return "ID: $id, Name: $name, Description: $description, Status: $status"
    }
}

// Class to manage tasks with full functionality
class TaskManager {
    private var tasks = mutableListOf<Task>() // Mutable collection to store tasks
    private var nextId = 1 // Mutable variable for generating unique task IDs

    // Adds a new task with name, description, and optional status
    fun addTask(name: String, description: String, status: String = "Pending") {
        if (name.isBlank()) { // Validate task name
            println("Error: Task name cannot be empty.")
            return
        }
        if (description.isBlank()) { // Validate task description
            println("Error: Task description cannot be empty.")
            return
        }
        if (status !in listOf("Pending", "Completed")) { // Validate status
            println("Error: Status must be 'Pending' or 'Completed'.")
            return
        }
        // Create and add task with next available ID
        tasks.add(Task(nextId++, name, description, status))
        println("Task '$name' added successfully.")
    }

    // Removes a task by name
    fun removeTask(name: String) {
        if (name.isBlank()) { // Validate input
            println("Error: Task name cannot be empty.")
            return
        }
        val task = tasks.find { it.name.equals(name, ignoreCase = true) } // Case-insensitive search
        if (task != null) { // Check if task exists
            tasks.remove(task) // Modify collection
            println("Task '$name' removed successfully.")
        } else {
            println("Error: Task '$name' not found.")
        }
    }

    // Lists all tasks in the collection
    fun listTasks() {
        if (tasks.isEmpty()) { // Check if list is empty
            println("No tasks available.")
            return
        }
        // Iterate through tasks using a for loop
        println("Current Tasks:")
        for (task in tasks) {
            println(task) // Uses Task's toString method
        }
    }

    // Clears all tasks from the collection
    fun clearTasks() {
        if (tasks.isEmpty()) { // Check if already empty
            println("No tasks to clear.")
            return
        }
        tasks.clear() // Clear collection
        nextId = 1 // Reset ID counter
        println("All tasks cleared successfully.")
    }

    // Updates task status by ID
    fun updateTaskStatus(id: Int, newStatus: String) {
        if (newStatus !in listOf("Pending", "Completed")) { // Validate status
            println("Error: Status must be 'Pending' or 'Completed'.")
            return
        }
        val task = tasks.find { it.id == id } // Find task by ID
        if (task != null) { // Check if task exists
            val updatedTask = task.copy(status = newStatus) // Create copy with new status
            tasks[tasks.indexOf(task)] = updatedTask // Update collection
            println("Task ID $id status updated to '$newStatus'.")
        } else {
            println("Error: Task ID $id not found.")
        }
    }
}

// Main function to run the interactive Task Manager menu
fun main() {
    val manager = TaskManager() // Create TaskManager instance
    var choice: Int? // Mutable variable for menu choice

    // Main menu loop for user interaction
    while (true) {
        // Display menu options
        println("\n=== Task Manager Menu ===")
        println("1. Add Task")
        println("2. Remove Task")
        println("3. List Tasks")
        println("4. Clear All Tasks")
        println("5. Update Task Status")
        println("6. Exit")
        print("Enter choice (1-6): ")

        // Read and validate user input
        choice = readLine()?.toIntOrNull()
        if (choice == null || choice !in 1..6) { // Validate menu choice
            println("Error: Invalid choice. Please enter a number between 1 and 6.")
            continue
        }


        // Handle menu options using when expression
        when (choice) {
            1 -> {
                print("Enter task name: ")
                val name = readLine() ?: ""
                print("Enter task description: ")
                val description = readLine() ?: ""
                print("Enter status (Pending/Completed, default: Pending): ")
                val status = readLine()?.ifBlank { "Pending" } ?: "Pending"
                manager.addTask(name, description, status)
            }
            2 -> {
                print("Enter task name to remove: ")
                val name = readLine() ?: ""
                manager.removeTask(name)
            }
            3 -> manager.listTasks()
            4 -> manager.clearTasks()
            5 -> {
                print("Enter task ID to update: ")
                val id = readLine()?.toIntOrNull() ?: 0
                if (id <= 0) {
                    println("Error: Invalid task ID.")
                    return
                }
                print("Enter new status (Pending/Completed): ")
                val status = readLine() ?: ""
                manager.updateTaskStatus(id, status)
            }
            6 -> {
                println("Exiting Task Manager. Goodbye!")
                break
            }
        }
    }
}