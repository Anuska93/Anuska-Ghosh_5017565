
// Exercise 1: Inventory Management System


Data Structures and Algorithms are Essential:

1.Efficiency: Managing large inventories requires efficient storage and retrieval of product information. Data structures affect the performance of these operations significantly.
2.Scalability: As the inventory grows, the chosen data structures and algorithms must handle the increasing data size effectively without a performance hit.
3.Speed: Quick access to product data (e.g., looking up a product's details) is crucial for real-time operations and user experience.

Suitable Data Structures For This Problem:
1.HashMap:
Use Case: Efficient for lookups, updates, and deletions. Ideal for scenarios where you need to quickly access elements based on a key (e.g., productId).
Operations:
Add: Average O(1)
Update: Average O(1)
Delete: Average O(1)
Lookup: Average O(1)
2.ArrayList:
Use Case: Useful when the order of elements is important, and you need to perform operations like adding and removing elements at specific positions.
Operations:
Add: O(1) (amortized) for adding at the end
Update: O(1)
Delete: O(n) (since removing requires shifting elements)
Lookup: O(n) (if not using an index)

    public class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
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
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Product> inventory;
    public InventoryManager() {
        inventory = new HashMap<>();
    }
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }
    public void updateProduct(String productId, Product updatedProduct) {
        if (inventory.containsKey(productId)) {
            inventory.put(productId, updatedProduct);
        } else {
            System.out.println("Product not found.");
        }
    }
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found.");
        }
    }
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }
    public void displayAllProducts() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}
Time Complexity Analysis:
Add Operation: O(1) (average) - Adding a product to HashMap involves hashing the key and inserting the value.
Update Operation: O(1) (average) - Updating a product involves locating the entry by key and replacing the value.
Delete Operation: O(1) (average) - Removing a product involves locating the entry by key and deleting it.
Lookup Operation: O(1) (average) - Finding a product by key involves hashing the key and accessing the value.

Optimization:
For HashMap: Ensure the initial capacity and load factor are set appropriately to reduce rehashing overhead.
For ArrayList: Use it when maintaining order and indexing are important. Consider using a HashMap for fast lookups combined with an ArrayList if you need to preserve the order.



// Exercise 2: E-commerce Platform Search Function



Big O Notation:
Definition: Big O notation describes the upper bound of the time complexity of an algorithm, expressing how the running time grows relative to the input size. It provides a way to evaluate the efficiency of algorithms in terms of time and space.
Purpose: It helps in comparing algorithms based on their efficiency and scalability. It abstracts away constant factors and lower-order terms, focusing on how the runtime increases with larger input sizes.

Linear Search:
Best Case: O(1) - The desired element is found at the first position in the array.
Average Case: O(n) - The element is found somewhere in the array, requiring a linear scan.
Worst Case: O(n) - The element is not found or is at the last position, requiring a full scan of the array.

Binary Search:
Best Case: O(1) - The desired element is at the middle of the array.
Average Case: O(log n) - The element is found after logarithmic divisions of the array.
Worst Case: O(log n) - The element is not found or is at the end after multiple divisions.

public class Product {
    private String productId;
    private String productName;
    private String category;
    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
    public String getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public String getCategory() {
        return category;
    }
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
public class LinearSearch {
    public static Product search(Product[] products, String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
}
import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {
    public static Product search(Product[] products, String productId) {
        Arrays.sort(products, Comparator.comparing(Product::getProductId));
        int left = 0;
        int right = products.length - 1;
         while (left <= right) {
            int mid = left + (right - left) / 2;
            String midProductId = products[mid].getProductId();

            if (midProductId.equals(productId)) {
                return products[mid];
            } else if (midProductId.compareTo(productId) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; 
    }
}

Time Complexity Comparison:
Linear Search:
Best Case: O(1)
Average Case: O(n)
Worst Case: O(n)

Binary Search:
Best Case: O(1)
Average Case: O(log n)
Worst Case: O(log n)
Sorting Time Complexity for Binary Search: Sorting the array adds a time complexity of O(n log n) if sorting is required.

Suitability Discussion:
Linear Search:
Advantages: Simple to implement, no requirement for the array to be sorted.
Disadvantages: Inefficient for large datasets due to linear time complexity.

Binary Search:
Advantages: Much faster for large datasets due to logarithmic time complexity, provided the data is sorted.
Disadvantages: Requires the array to be sorted, and the sorting operation itself can be time-consuming.



// Exercise 3: Sorting Customer Orders



Bubble Sort
Description: Bubble Sort repeatedly compares adjacent elements and swaps them if they are in the wrong order. This process continues until the list is sorted.

Insertion Sort
Description: Insertion Sort builds the sorted array one item at a time. It picks each item and inserts it into its correct position among the previously sorted items.

Quick Sort
Description: Quick Sort is a divide-and-conquer algorithm that picks an element as a pivot and partitions the array around the pivot. It recursively applies the same logic to the subarrays.

Merge Sort
Description: Merge Sort divides the array into halves, recursively sorts each half, and then merges the sorted halves.

public class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;
    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
    public int getOrderId() {
        return orderId;
    }
    public String getCustomerName() {
        return customerName;
    }
   public double getTotalPrice() {
        return totalPrice;
    }
    public String toString() {
        return "OrderID: " + orderId + ", CustomerName: " + customerName + ", TotalPrice: " + totalPrice;
    }
}
public class BubbleSort {
    public static void sort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j+1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
}
public class QuickSort {
    public static void sort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            sort(orders, low, pi - 1);
            sort(orders, pi + 1, high);
        }
    }
    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);
         for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
}
Performance Comparison
Bubble Sort:
Time Complexity: O(n2) on average, which makes it inefficient for large datasets.
Space Complexity: O(1) as it sorts in-place.
Quick Sort:
Time Complexity:O(nlogn) on average, which is generally faster than Bubble Sort.
Space Complexity: O(logn) due to the recursion stack.

Why Quick Sort is Preferred
Efficiency: Quick Sort generally performs better than Bubble Sort, especially for large datasets, due to its average time complexity ofO(nlogn) compared to Bubble Sort's O(n2).
Scalability: Quick Sort scales better with increasing data size. Bubble Sort's performance degrades significantly with larger datasets due to its quadratic time complexity.



// Exercise 4: Employee Management System



Array Representation in Memory
Memory Layout: An array is a collection of elements stored in contiguous memory locations. Each element is of the same data type, and the size of the array determines the number of elements it can hold.
Access: Arrays provide constant-timeO(1) access to elements because the memory address of any element can be computed using the base address and the index.
Advantages:
Fast Access: Direct access to elements via index makes operations like retrieval and update very fast.
Simplicity: Arrays are straightforward to implement and use, and their structure is easy to understand.
Memory Efficiency: Arrays don’t have overhead for pointers or metadata (except the base address), making them memory efficient compared to some other data structures.

public class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public double getSalary() {
        return salary;
    }
    public String toString() {
        return "EmployeeID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary;
    }
}
public class EmployeeManagementSystem {
    private Employee[] employees;
    private int size; 
    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size++] = employee;
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }
    public Employee searchEmployeeById(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null; 

    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }
    public boolean deleteEmployeeById(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {           
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[size - 1] = null; 
                size--;
                return true; 
            }
        }
        return false; 
    }
}

Time Complexity
Add Employee:
Time Complexity: 
O(1) (if there is space in the array)
Space Complexity: O(n) (where n is the number of employees)

Search Employee by ID:
Time Complexity: O(n) (linear search through the array)
Space Complexity:O(1) (constant space for searching)

Traverse Employees:
Time Complexity: O(n) (iterating through the array)
Space Complexity: O(1) (constant space for traversal)

Delete Employee by ID:
Time Complexity: O(n) (linear search and shifting elements)
Space Complexity: O(1) (constant space for deletion)

Limitations of Arrays and When to Use Them
Fixed Size: Arrays have a fixed size, which means that you need to know the maximum number of employees beforehand. This can lead to inefficiencies if the size is overestimated or if the array becomes full.
Insertions and Deletions: Adding or removing elements involves shifting, which can be inefficient compared to more dynamic data structures like linked lists.
Limited Flexibility: Arrays do not support dynamic resizing, unlike data structures such as ArrayLists or other collection classes.

When to Use Arrays:
When the number of elements is known and fixed.
When quick access to elements via an index is needed.
When memory efficiency is a concern and the overhead of more complex data structures is not justified.



// Exercise 5: Task Management System



Singly Linked List
Description: A singly linked list is a collection of nodes where each node contains data and a reference (or pointer) to the next node in the sequence. It allows for efficient insertion and deletion operations.
Operations:
Add: Insert at the beginning, end, or middle.
Search: Traverse from the head to find the desired node.
Traverse: Visit each node starting from the head.
Delete: Remove a node by updating pointers.

Doubly Linked List
Description: A doubly linked list is similar to a singly linked list but with two references per node: one to the next node and one to the previous node. This allows traversal in both directions.
Operations:
Add: Insert at the beginning, end, or middle.
Search: Traverse forward or backward to find the desired node.
Traverse: Visit each node starting from either end.
Delete: Remove a node by updating both next and previous pointers.

public class Task {
    private int taskId;
    private String taskName;
    private String status;
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
    public int getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public String getStatus() {
        return status;
    }
    public String toString() {
        return "TaskID: " + taskId + ", TaskName: " + taskName + ", Status: " + status;
    }
}
class Node {
    Task task;
    Node next;
    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}
public class TaskManager {
    private Node head;

    public TaskManager() {
        this.head = null;
    }
    public void addTask(Task task) {
        Node newNode = new Node(task);
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
    public Task searchTaskById(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null; // Task not found
    }
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }
    public boolean deleteTaskById(int taskId) {
        Node current = head;
        Node previous = null;
        while (current != null && current.task.getTaskId() != taskId) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false; // Task not found
        }
        if (previous == null) {
            head = current.next; // Removing the head node
        } else {
            previous.next = current.next; // Removing a non-head node
        }
        return true; // Task deleted successfully
    }
}
Time Complexity
Add Task:
Time Complexity: O(n) (where n is the number of tasks; requires traversal to find the end)
Space Complexity:O(1) (constant space for addition)

Search Task by ID:
Time Complexity: O(n) (linear search through the list)
Space Complexity: O(1) (constant space for searching)

Traverse Tasks:
Time Complexity: O(n) (iterating through the list)
Space Complexity: O(1) (constant space for traversal)

Delete Task by ID:
Time Complexity: O(n) (linear search and node removal)
Space Complexity: O(1) (constant space for deletion)

Advantages of LinkedLists Over Arrays
Dynamic Size: Linked lists can grow or shrink dynamically, which is advantageous when the number of tasks is not known in advance or changes frequently.
Efficient Insertions and Deletions: Inserting or deleting elements can be more efficient, particularly when the operations are performed at the beginning or in the middle of the list. Arrays may require shifting elements, which can be costly.
Memory Utilization: Linked lists use memory proportional to the number of elements, as each node contains only the required data and pointers.

When to Use Linked Lists:
When the number of elements is unknown or changes frequently.
When frequent insertions and deletions are required.
When memory overhead for pointers is acceptable and you don’t need constant-time random access.



// Exercise 6: Library Management System



Linear Search
Description: Linear search, also known as sequential search, involves checking each element in a list one by one until the target element is found or the end of the list is reached.
Time Complexity:
Best Case: O(1) 
Average Case: O(n) (where n is the number of elements)
Worst Case: O(n) 
Space Complexity: O(1) 

Binary Search
Description: Binary search is an efficient algorithm that repeatedly divides a sorted list in half until the target element is found or the search range is empty. It requires that the list be sorted.
Time Complexity:
Best Case:O(1) 
Average Case: O(logn)
Worst Case: O(logn) 
Space Complexity: O(1) 

  public class Book {
    private int bookId;
    private String title;
    private String author;
      public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }  public int getBookId() {
        return bookId;
    }public String getTitle() {
        return title;
    } public String getAuthor() {
        return author;
    } public String toString() {
        return "BookID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}
import java.util.List;
public class Library {
    public Book searchBookByTitleLinear(List<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; 
    }
}

import java.util.Arrays;
import java.util.List;

public class Library {
    public Book searchBookByTitleBinary(List<Book> books, String title) {
        int left = 0;
        int right = books.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return midBook; 
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
       return null;
    }
}
Linear Search:
Time Complexity: O(n)
Space Complexity: O(1)
Suitable for small or unsorted data sets where sorting is not feasible or required.

Binary Search:
Time Complexity: O(logn)
Space Complexity: O(1) (iterative) or O(logn) (recursive)
Suitable for large, sorted data sets where the overhead of maintaining order is acceptable.

Linear Search:
Use When: The list is unsorted or small, and sorting the list is not practical. Also useful for cases where search needs to be done frequently and the overhead of sorting is too high.
Advantages: Simplicity and no requirement for the data to be sorted.

Binary Search:
Use When: The list is sorted or can be sorted. Ideal for large lists where frequent searches are performed.
Advantages: More efficient with large data sets due to O(logn) time complexity. Requires maintaining order, which might involve additional overhead



// Exercise 7: Financial Forecasting



 Recursion is a technique where a function calls itself in order to solve a problem. Each recursive call works on a smaller subset of the problem, gradually breaking it down until it reaches a base case.
 public class FinancialForecast {

    public static double calculateFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue; // Base case: no more years, return the current value
        } else {
            return calculateFutureValue(currentValue * (1 + growthRate), growthRate, years - 1);
        }
    }
     public static void main(String[] args) {
        double initialValue = 1000.0; 
        double growthRate = 0.05; 
        int years = 10;
        double futureValue = calculateFutureValue(initialValue, growthRate, years);
        System.out.println("Future Value: " + futureValue);
    }
}
Time Complexity
Time Complexity: O(n), where n is the number of years. Each recursive call represents a year, so the function makes n recursive calls.
Space Complexity: O(n) due to the recursion call stack. Each recursive call adds a new frame to the call stack, which can grow up to n frames deep.
Optimization:To avoid excessive computation and improve efficiency, you can use memoization or iterative approaches:
Memoization: Store previously computed results to avoid redundant calculations. This technique can be applied if you are performing multiple recursive calls with overlapping subproblems.
Iterative Approach: Convert the recursive algorithm to an iterative one to avoid the overhead of recursive calls and stack space.
