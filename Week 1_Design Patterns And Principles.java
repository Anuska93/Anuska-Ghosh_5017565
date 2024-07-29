
// Exercise 1: Implementing the Singleton Pattern


public class Logger {
    private static Logger instance;
    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("This is my message: " + message);
    }
}
public class LoggerTest {
    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        if (logger1 == logger2) {
            System.out.println("Both the logger instances are same");
        } else {
            System.out.println("Logger instances are different");
        }
    }
}



// Exercise 2: Implementing the Factory Method Pattern



public interface Document {
    void open();
    void close();
}
public class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word document.");
    }
    public void close() {
        System.out.println("Closing Word document.");
    }
}
public class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF document.");
    }
    public void close() {
        System.out.println("Closing PDF document.");
    }
}
public class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel document.");
    }
    public void close() {
        System.out.println("Closing Excel document.");
    }
}
public abstract class DocumentFactory {
    public abstract Document createDocument();
}
public class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}
public class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}
public class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}
public class FactoryMethodPatternTest {
    public static void main(String[] args) {
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.close();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();
        
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.close();
    }
}



// Exercise 3: Implementing the Builder Pattern




public class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private String motherboard;
    private String powerSupply;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
    }
    public String getCPU() { return CPU; }
    public String getRAM() { return RAM; }
    public String getStorage() { return storage; }
    public String getGPU() { return GPU; }
    public String getMotherboard() { return motherboard; }
    public String getPowerSupply() { return powerSupply; }
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage +
               ", GPU=" + GPU + ", motherboard=" + motherboard + ", powerSupply=" + powerSupply + "]";
    }
    public static class Builder {
        private String CPU;
        private String RAM;
        private String storage;
        private String GPU;
        private String motherboard;
        private String powerSupply;

        // Methods to set each attribute
        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        public Builder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder setMotherboard(String motherboard) {
            this.motherboard = motherboard;
            return this;
        }

        public Builder setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
            return this;
        }
        public Computer build() {
            return new Computer(this);
        }
    }
}
public class BuilderPatternTest {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .setMotherboard("ASUS ROG")
                .setPowerSupply("750W")
                .build();

        Computer officePC = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .setGPU("Integrated")
                .setMotherboard("MSI")
                .setPowerSupply("500W")
                .build();

        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
    }
}



// Exercise 4: Implementing the Adapter Pattern




public interface PaymentProcessor {
    void processPayment(double amount);
}
public class PayPal {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}
public class Stripe {
    public void charge(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}
public class Square {
    public void doPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through Square.");
    }
}
public class PayPalAdapter implements PaymentProcessor {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }
    public void processPayment(double amount) {
        payPal.makePayment(amount);
    }
}
public class StripeAdapter implements PaymentProcessor {
    private Stripe stripe;

    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }
    public void processPayment(double amount) {
        stripe.charge(amount);
    }
}
public class SquareAdapter implements PaymentProcessor {
    private Square square;

    public SquareAdapter(Square square) {
        this.square = square;
    }
    public void processPayment(double amount) {
        square.doPayment(amount);
    }
}
public class AdapterPatternTest {
    public static void main(String[] args) {
        PayPal payPal = new PayPal();
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        payPalProcessor.processPayment(100.00);

        Stripe stripe = new Stripe();
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);
        stripeProcessor.processPayment(200.00);

        Square square = new Square();
        PaymentProcessor squareProcessor = new SquareAdapter(square);
        squareProcessor.processPayment(300.00);
    }
}



// Exercise 5: Implementing the Decorator Pattern




public interface Notifier {
    void send(String message);
}
public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Sending email with message: " + message);
    }
}
public abstract class NotifierDecorator implements Notifier {
    protected Notifier wrapped;

    public NotifierDecorator(Notifier wrapped) {
        this.wrapped = wrapped;
    }
    public void send(String message) {
        wrapped.send(message);
    }
}
public class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }
    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }
   private void sendSMS(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}
   public class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }
    public void send(String message) {
        super.send(message);
        sendSlack(message);
    }

    private void sendSlack(String message) {
        System.out.println("Sending Slack message: " + message);
    }
}
public class DecoratorPatternTest {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);
        slackNotifier.send("Hello, this is a test message!");
    }
}



// Exercise 6: Implementing the Proxy Pattern



public interface Image {
    void display();
}
public class RealImage implements Image {
    private String url;
    public RealImage(String url) {
        this.url = url;
        loadImageFromServer();
    }
       private void loadImageFromServer() {
        System.out.println("Loading image from " + url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Image loaded from " + url);
    }
    public void display() {
        System.out.println("Displaying image from " + url);
    }
}
public class ProxyImage implements Image {
    private String url;
    private RealImage realImage;

    public ProxyImage(String url) {
        this.url = url;
    }
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(url);
        }
        realImage.display();
    }
}
public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("http://example.com/image1.jpg");
        Image image2 = new ProxyImage("http://example.com/image2.jpg");
        image1.display();
        System.out.println();
        image1.display();
        System.out.println();
        image2.display();
        System.out.println();
        image2.display();
    }
}



// Exercise 7: Implementing the Observer Pattern



import java.util.ArrayList;
import java.util.List;

public interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}
import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private List<Observer> observers;
    private double stockPrice;

    public StockMarket() {
        observers = new ArrayList<>();
}
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }
    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}
public interface Observer {
    void update(double stockPrice);
}
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }
    public void update(double stockPrice) {
        System.out.println(appName + " received stock price update: $" + stockPrice);
    }
}
public class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }
    public void update(double stockPrice) {
        System.out.println(appName + " received stock price update: $" + stockPrice);
    }
}
public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("MobileApp1");
        Observer mobileApp2 = new MobileApp("MobileApp2");
        Observer webApp1 = new WebApp("WebApp1");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(mobileApp2);
        stockMarket.registerObserver(webApp1);

        stockMarket.setStockPrice(100.00);
        System.out.println();

        stockMarket.deregisterObserver(mobileApp2);

        stockMarket.setStockPrice(105.50);
    }
}


// Exercise 8: Implementing the Strategy Pattern



public interface PaymentStrategy {
    void pay(double amount);
}
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card. Card Holder: " + cardHolderName);
    }
}
public class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal. Email: " + email);
    }
}
public class PaymentContext {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}
public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();
        PaymentStrategy creditCard = new CreditCardPayment("1234-5678-9876-5432", "John Doe");
        context.setPaymentStrategy(creditCard);
        context.executePayment(150.00);
        PaymentStrategy payPal = new PayPalPayment("john.doe@example.com");
        context.setPaymentStrategy(payPal);
        context.executePayment(200.00);
    }
}


// Exercise 9: Implementing the Command Pattern



public interface Command {
    void execute();
}
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOn();
    }
}
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnOff();
    }
}
public class RemoteControl {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
     public void pressButton() {
        command.execute();
    }
}
public class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }
    public void turnOff() {
        System.out.println("The light is off");
    }
}
public class CommandPatternTest {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);
        remote.pressButton();
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}



// Exercise 10: Implementing the MVC Pattern



public class Student {
    private String id;
    private String name;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
public class StudentView {
    public void displayStudentDetails(String studentId, String studentName, String studentGrade) {
        System.out.println("Student Details:");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + studentName);
        System.out.println("Grade: " + studentGrade);
    }
}
public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    public void setStudentName(String name) {
        model.setName(name);
    }
    public String getStudentName() {
        return model.getName();
    }
    public void setStudentId(String id) {
        model.setId(id);
    }
    public String getStudentId() {
        return model.getId();
    }
    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }
    public String getStudentGrade() {
        return model.getGrade();
    }
    public void updateView() {
        view.displayStudentDetails(model.getId(), model.getName(), model.getGrade());
    }
}
public class MVCPatternTest {
    public static void main(String[] args) {
        Student model = new Student("1", "John Doe", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");
        controller.updateView();
    }
}



// Exercise 11: Implementing Dependency Injection



public interface CustomerRepository {
    Customer findCustomerById(String id);
}
public class CustomerRepositoryImpl implements CustomerRepository {
    public Customer findCustomerById(String id) {
        return new Customer(id, "John Doe", "john.doe@example.com");
    }
}
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerById(id);
    }
}
public class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}
public class DependencyInjectionTest {
    public static void main(String[] args) {
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerService customerService = new CustomerService(customerRepository);
        Customer customer = customerService.getCustomerById("1");
        System.out.println("Customer found: " + customer);
    }
}



























