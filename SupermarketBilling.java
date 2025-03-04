
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SupermarketBilling {
    static Scanner scanner = new Scanner(System.in);

    public static Map<String, Integer> enterProducts() {
        Map<String, Integer> buyingData = new HashMap<>();
        boolean enterDetails = true;

        while (enterDetails) {
            System.out.print("Press A to add product and Q to quit: ");
            String details = scanner.next();

            if (details.equalsIgnoreCase("A")) {
                System.out.print("Enter product: ");
                String product = scanner.next();
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                buyingData.put(product, buyingData.getOrDefault(product, 0) + quantity);
            } else if (details.equalsIgnoreCase("Q")) {
                enterDetails = false;
            } else {
                System.out.println("Please select a correct option.");
            }
        }
        return buyingData;
    }

    public static int getPrice(String product, int quantity) {
        Map<String, Integer> priceData = new HashMap<>();
        priceData.put("Biscuit", 3);
        priceData.put("Chicken", 5);
        priceData.put("Egg", 1);
        priceData.put("Fish", 3);
        priceData.put("Coke", 2);
        priceData.put("Bread", 2);
        priceData.put("Apple", 3);
        priceData.put("Onion", 3);

        if (priceData.containsKey(product)) {
            int subtotal = priceData.get(product) * quantity;
            System.out.println(product + ": $" + priceData.get(product) + " x " + quantity + " = $" + subtotal);
            return subtotal;
        } else {
            System.out.println("Product not found: " + product);
            return 0;
        }
    }

    public static double getDiscount(double billAmount, String membership) {
        double discount = 0;

        if (billAmount >= 25) {
            switch (membership.toLowerCase()) {
                case "gold":
                    discount = 0.20;
                    break;
                case "silver":
                    discount = 0.10;
                    break;
                case "bronze":
                    discount = 0.05;
                    break;
                default:
                    System.out.println("No discount for non-members.");
                    return billAmount;
            }
            double discountAmount = billAmount * discount;
            billAmount -= discountAmount;
            System.out.printf("%.0f%% off for %s membership on total amount: $%.2f\n", discount * 100, membership, billAmount);
        } else {
            System.out.println("No discount on amount less than $25.");
        }
        return billAmount;
    }

    public static void makeBill(Map<String, Integer> buyingData, String membership) {
        double billAmount = 0;

        for (Map.Entry<String, Integer> entry : buyingData.entrySet()) {
            billAmount += getPrice(entry.getKey(), entry.getValue());
        }

        billAmount = getDiscount(billAmount, membership);
        System.out.println("The discounted amount is $" + String.format("%.2f", billAmount));
    }

    public static void main(String[] args) {
        Map<String, Integer> buyingData = enterProducts();
        System.out.print("Enter customer membership: ");
        String membership = scanner.next();
        makeBill(buyingData, membership);
    }
}