
import java.util.Scanner;

class Product {
    String name;
    int quantity;
    double price;

    // Constructor
    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Método para calcular el valor total de un producto
    public double getTotalValue() {
        return this.quantity * this.price;
    }
}

public class AplicacionInventarioProductos {
    private static Scanner scanner = new Scanner(System.in);
    private static Product[] products = null;

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            showMenu();
            int option = getIntInput("Elija una opción: ");

            switch (option) {
                case 1:
                    addProducts();
                    break;
                case 2:
                    showInventory();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    updateProductQuantity();
                    break;
                case 5:
                    exit = true;
                    System.out.println("\nSaliendo del programa...");
                    System.out.println("© 2024 Jose David Garcia Pineda, Geraldine Garcia Torrado, Laura Jimena Velasquez Coronel. All rights reserved. \n");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }

        scanner.close();
    }

    // Método para mostrar el menú
    private static void showMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Agregar productos");
        System.out.println("2. Mostrar inventario");
        System.out.println("3. Eliminar un producto");
        System.out.println("4. Actualizar cantidad de un producto");
        System.out.println("5. Salir");
    }

    // Método para agregar productos
    private static void addProducts() {
        int numProducts = getIntInput("¿Cuántos productos desea ingresar? ");
        products = new Product[numProducts];

        for (int i = 0; i < numProducts; i++) {
            System.out.println("Producto " + (i + 1) + ":");
            String name = getStringInput("Ingrese el nombre del producto: ");
            int quantity = getIntInput("Ingrese la cantidad del producto: ");
            double price = getDoubleInput("Ingrese el precio del producto: ");

            products[i] = new Product(name, quantity, price);
        }
    }

    // Método para mostrar el inventario
    private static void showInventory() {
        if (products != null && products.length > 0) {
            double totalInventoryValue = 0;
            System.out.println("\nInventario de productos:");
            for (Product product : products) {
                double total = product.getTotalValue();
                totalInventoryValue += total;
                System.out.printf("Nombre: %s, Cantidad: %d, Precio: %.2f, Total: %.2f\n",
                        product.name, product.quantity, product.price, total);
            }
            System.out.printf("\nValor total del inventario: %.2f\n", totalInventoryValue);
        } else {
            System.out.println("No hay productos en el inventario.");
        }
    }

    // Método para eliminar un producto
    private static void deleteProduct() {
        if (products != null && products.length > 0) {
            String nameToDelete = getStringInput("Ingrese el nombre del producto a eliminar: ");
            products = deleteProductByName(products, nameToDelete);
        } else {
            System.out.println("No hay productos para eliminar.");
        }
    }

    private static Product[] deleteProductByName(Product[] products, String nameToDelete) {
        int indexToDelete = findProductIndexByName(nameToDelete);
        if (indexToDelete != -1) {
            Product[] newProducts = new Product[products.length - 1];
            for (int i = 0, j = 0; i < products.length; i++) {
                if (i != indexToDelete) {
                    newProducts[j++] = products[i];
                }
            }
            System.out.println("Producto eliminado: " + nameToDelete);
            return newProducts;
        } else {
            System.out.println("Producto no encontrado.");
            return products;
        }
    }

    // Método para actualizar la cantidad de un producto
    private static void updateProductQuantity() {
        if (products != null && products.length > 0) {
            String nameToUpdate = getStringInput("Ingrese el nombre del producto a actualizar: ");
            int newQuantity = getIntInput("Ingrese la nueva cantidad: ");
            int index = findProductIndexByName(nameToUpdate);
            if (index != -1) {
                products[index].quantity += newQuantity;
                System.out.printf("Cantidad actualizada de %s: %d\n", products[index].name, products[index].quantity);
            } else {
                System.out.println("Producto no encontrado.");
            }
        } else {
            System.out.println("No hay productos para actualizar.");
        }
    }

    // Método para encontrar el índice de un producto por su nombre
    private static int findProductIndexByName(String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].name.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Métodos auxiliares para leer entradas y manejar errores
    private static String getStringInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número decimal válido.");
            }
        }
    }
}