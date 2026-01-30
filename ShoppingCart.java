import java.util.*;

class Product {
    String productName;
    int qty;
    double unitPrice;

    Product(String productName, int qty, double unitPrice) {
        this.productName = productName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    double totalCost() {
        return qty * unitPrice;
    }
}

public class ShoppingCart {

    static ArrayList<Product> basket = new ArrayList<>();
    static HashMap<String, Double> storeItems = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Different products and prices
        storeItems.put("Rice", 60.0);
        storeItems.put("Sugar", 45.0);
        storeItems.put("Oil", 120.0);
        storeItems.put("Soap", 35.0);
        storeItems.put("Shampoo", 90.0);

        int option;

        do {
            System.out.println("\n====== WELCOME TO MY SHOP ======");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Show Cart");
            System.out.println("3. Update Quantity");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    addProduct(input);
                    break;
                case 2:
                    displayCart();
                    break;
                case 3:
                    updateProduct(input);
                    break;
                case 4:
                    generateBill();
                    break;
                case 5:
                    System.out.println("Thank you for visiting our shop!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (option != 5);
    }

    static void addProduct(Scanner input) {
        System.out.print("Enter product name: ");
        String name = input.nextLine();

        if (!storeItems.containsKey(name)) {
            System.out.println("Sorry! Product not available.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = input.nextInt();

        basket.add(new Product(name, quantity, storeItems.get(name)));
        System.out.println("Product added successfully!");
    }

    static void displayCart() {
        if (basket.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n------ CART ITEMS ------");
        for (Product p : basket) {
            System.out.println(p.productName + " | Qty: " + p.qty + " | Unit Price: ₹" + p.unitPrice);
        }
    }

    static void updateProduct(Scanner input) {
        System.out.print("Enter product name to update: ");
        String name = input.nextLine();

        for (Product p : basket) {
            if (p.productName.equalsIgnoreCase(name)) {
                System.out.print("Enter new quantity: ");
                p.qty = input.nextInt();
                System.out.println("Quantity updated!");
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    static void generateBill() {
        double grandTotal = 0;

        if (basket.isEmpty()) {
            System.out.println("Cart is empty. Nothing to bill.");
            return;
        }

        System.out.println("\n====== FINAL BILL ======");
        for (Product p : basket) {
            double cost = p.totalCost();
            grandTotal += cost;
            System.out.println(p.productName + " x " + p.qty + " = ₹" + cost);
        }

        System.out.println("-------------------------");
        System.out.println("TOTAL AMOUNT: ₹" + grandTotal);
    }
}
