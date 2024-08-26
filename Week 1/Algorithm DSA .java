1.
public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    // Constructor
    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Product> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    // Add a product to the inventory
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // Update a product in the inventory
    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Delete a product from the inventory
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Retrieve a product from the inventory
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }
}
Time Complexity Analysis:

Add Operation: Using HashMap, the average time complexity for adding a product is O(1). Inserting a new product involves calculating the hash and placing the product in the appropriate bucket.

Update Operation: Updating a product in the inventory also takes O(1) on average, as it involves a lookup followed by an insertion or replacement in the HashMap.

Delete Operation: Deleting a product by its ID takes O(1) on average. The HashMap removes the entry associated with the given key.

  --------------------------------------------------------------------------------------------------------------------------------
  2.
  
  Big O Notation:

Definition: Big O notation describes the upper bound of the time complexity of an algorithm, representing the worst-case scenario. It helps in understanding how the runtime of an algorithm scales with the size of the input data.

Best, Average, and Worst-Case Scenarios for Search Operations:

Best Case: The search finds the target element at the first position (e.g., O(1) for linear search).

Average Case: The search finds the target element in the middle of the dataset (e.g., O(n/2) ≈ O(n) for linear search).

Worst Case: The search does not find the element or finds it at the last position (e.g., O(n) for linear search, O(log n) for binary search).

  public class Product {
    private String productId;
    private String productName;
    private String category;

    // Constructor
    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }
}
public class Search {

    public Product linearSearch(Product[] products, String targetId) {
        for (Product product : products) {
            if (product.getProductId().equals(targetId)) {
                return product;
            }
        }
        return null; // Product not found
    }
}
import java.util.Arrays;
import java.util.Comparator;

public class Search {

    public Product binarySearch(Product[] products, String targetId) {
        // Sort products by productId
        Arrays.sort(products, Comparator.comparing(Product::getProductId));

        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(targetId);

            if (comparison == 0) {
                return products[mid]; // Product found
            } else if (comparison < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return null; // Product not found
    }
  Suitable Algorithm for the Platform:

Binary Search: For an e-commerce platform, where quick and efficient search operations are essential for a large product database, binary search is more suitable. However, it requires the data to be sorted. If the products are stored in a sorted order by product ID, binary search will provide significantly faster search performance, especially as the dataset grows.
}
---------------------------------------------------------------------------------------------------

  3.
  Bubble Sort:

How It Works: Bubble Sort repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. This process is repeated until the list is sorted.
Time Complexity:
Best Case: O(n) (when the array is already sorted)
Average Case: O(n²)
Worst Case: O(n²)

  public class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    // Constructor
    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
public class SortOrders {

    public void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
}
public class SortOrders {

    public void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;

                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i + 1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}
Why Quick Sort is Preferred Over Bubble Sort:

Efficiency: Quick Sort is generally much faster than Bubble Sort for large datasets due to its O(n log n) average-case time complexity. Bubble Sort, with its O(n²) complexity, becomes inefficient as the number of elements increases.
------------------------------------------------------------------------------------------------------------------------

4.
  Advantages of Arrays:

Fast Access: Direct access to elements using indices provides constant-time retrieval and update operations.

Memory Efficiency: Arrays are memory-efficient because they allocate a single contiguous block of memory, minimizing overhead.

Simplicity: Arrays are straightforward to implement and use, making them suitable for simple data storage and manipulation tasks.

  public class EmployeeManagement {
    private Employee[] employees;
    private int size;

    // Constructor
    public EmployeeManagement(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add an employee
    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size++] = employee;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    // Search for an employee by ID
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null; // Employee not found
    }

    // Traverse and print all employees
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            Employee emp = employees[i];
            System.out.println("ID: " + emp.getEmployeeId() +
                               ", Name: " + emp.getName() +
                               ", Position: " + emp.getPosition() +
                               ", Salary: " + emp.getSalary());
        }
    }

    // Delete an employee by ID
    public void deleteEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null; // Remove the last element
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
Time Complexity Analysis:

Add Operation:

Time Complexity: O(1) for adding an employee, assuming there is space in the array.
Limitation: If the array is full, adding an employee requires resizing the array, which is not handled in this implementation.
Search Operation:

Time Complexity: O(n), where n is the number of employees. Each element is checked until the target is found or the end of the array is reached.
Traverse Operation:

Time Complexity: O(n), where n is the number of employees. Every element in the array is visited once.
Delete Operation:

Time Complexity: O(n), where n is the number of employees. Finding the employee is O(n), and shifting elements to fill the gap is also O(n).

-----------------------------------------------------------------------------------------------------------------

  5.
  Linked Lists:

Singly Linked List:

Structure: Each node contains data and a reference (or pointer) to the next node in the sequence. The last node's reference points to null.
Operations:
Add: Insertions are generally straightforward and involve adjusting pointers.
Search: Requires traversing from the head node to the desired node.
Delete: Involves updating pointers to remove the node.
Advantages: Efficient insertions and deletions at the beginning or end of the list.
Disadvantages: Linear time complexity for search operations; requires traversing from the head.
Doubly Linked List:

Structure: Each node contains data, a reference to the next node, and a reference to the previous node. This allows for traversal in both directions.
Operations:
Add: Similar to singly linked lists but with additional complexity due to backward references.
Search: More efficient traversal in both directions.
Delete: Easier deletion of nodes with direct access to the previous node.
Advantages: Allows bidirectional traversal and easier deletion of nodes.
Disadvantages: More complex and uses extra memory for the backward pointer.

  public class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManager {
    private TaskNode head;

    // Constructor
    public TaskManager() {
        head = null;
    }

    // Add a task
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search for a task by ID
    public Task searchTask(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getTaskId().equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }

    // Traverse and print all tasks
    public void traverseTasks() {
        TaskNode current = head;
        while (current != null) {
            System.out.println("ID: " + current.task.getTaskId() +
                               ", Name: " + current.task.getTaskName() +
                               ", Status: " + current.task.getStatus());
            current = current.next;
        }
    }

    // Delete a task by ID
    public void deleteTask(String taskId) {
        if (head == null) return;

        if (head.task.getTaskId().equals(taskId)) {
            head = head.next; // Remove the head
            return;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.task.getTaskId().equals(taskId)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next; // Remove the task
        }
    }
}
Time Complexity Analysis:

Add Operation:

Time Complexity: O(n) in the worst case, where n is the number of tasks. Inserting at the end requires traversing the list, but adding at the head is O(1).
Search Operation:

Time Complexity: O(n), where n is the number of tasks. Requires traversing the list to find the task.
Traverse Operation:

Time Complexity: O(n), where n is the number of tasks. Requires visiting each node once.
Delete Operation:

Time Complexity: O(n), where n is the number of tasks. Requires finding the node to be deleted and updating pointers.
---------------------------------------------------------------------------------------------------------------------------------------------------------------

6.
Linear Search:

How It Works: Linear Search iterates through each element of the list sequentially until it finds the target value or reaches the end of the list.
Time Complexity:
Best Case: O(1) (when the target is at the beginning)
Average Case: O(n)
Worst Case: O(n) (when the target is at the end or not present)
Advantages: Simple to implement and works with unsorted lists.
Disadvantages: Inefficient for large lists as it may require checking every element.
Binary Search:

How It Works: Binary Search requires a sorted list and works by repeatedly dividing the search interval in half. It compares the target value to the middle element and discards half of the list based on the comparison.
Time Complexity:
Best Case: O(1) (when the target is at the middle)
Average Case: O(log n)
Worst Case: O(log n)
Advantages: Much more efficient than linear search for large, sorted lists.
Disadvantages: Requires the list to be sorted. Inserting or deleting elements can be costly if the list needs to remain sorted.

  public class Library {

    // Linear search by title
    public Book linearSearchByTitle(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }
}
import java.util.Arrays;

public class Library {

    // Binary search by title (assuming books is sorted by title)
    public Book binarySearchByTitle(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);

            if (cmp == 0) {
                return books[mid]; // Book found
            } else if (cmp < 0) {
                low = mid + 1; // Search in the right half
            } else {
                high = mid - 1; // Search in the left half
            }
        }
        return null; // Book not found
    }
}
 linear search is more flexible and can handle unsorted lists, while binary search is much faster for large, sorted lists. The choice of algorithm should be based on the dataset size, whether the data is sorted, and the frequency of search operations

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

   7.
   Recursion is a method of solving a problem where the solution depends on solutions to smaller instances of the same problem. It involves a function calling itself.
  public class FinancialForecasting {

    // Recursive method to calculate future value
    public double calculateFutureValue(double initialValue, double growthRate, int periods) {
        // Base case: no more periods left
        if (periods == 0) {
            return initialValue;
        }
        // Recursive case: calculate the future value for one period
        return calculateFutureValue(initialValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        FinancialForecasting forecasting = new FinancialForecasting();
        double initialValue = 1000.0; // Initial investment
        double growthRate = 0.05; // 5% growth rate per period
        int periods = 10; // Number of periods

        double futureValue = forecasting.calculateFutureValue(initialValue, growthRate, periods);
        System.out.println("Future Value: " + futureValue);
    }
}
Time Complexity: O(n), where n is the number of periods. Each recursive call processes one period, making the time complexity proportional to the number of periods.

  Recursion is a powerful tool for problems that can be broken down into smaller instances of the same problem, but it can lead to deep call stacks and redundant computations.

Memoization can optimize recursive solutions by storing previously computed results.

Iterative solutions can be more efficient in terms of memory and performance for problems where recursion is not necessary.
