import java.io.*;
import java.util.*;

public class AddressBook {
    private final Map<String, String> contacts;

    public AddressBook() {
        contacts = new HashMap<>();
    }

    // Método para cargar los contactos desde un archivo
    public void load(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String number = parts[0].trim();
                    String name = parts[1].trim();
                    contacts.put(number, name);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error al cargar los contactos: " + e.getMessage());
        }
    }

    // Método para guardar los contactos en un archivo
    public void save(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                String number = entry.getKey();
                String name = entry.getValue();
                writer.write(number + " : " + name);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos: " + e.getMessage());
        }
    }

    // Método para mostrar todos los contactos en la agenda
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            String number = entry.getKey();
            String name = entry.getValue();
            System.out.println(number + " : " + name);
        }
    }

    // Método para crear un nuevo contacto
    public void create(String number, String name) {
        contacts.put(number, name);
        System.out.println("Contacto creado: " + number + " : " + name);
    }

    // Método para eliminar un contacto
    public void delete(String number) {
        if (contacts.containsKey(number)) {
            String name = contacts.remove(number);
            System.out.println("Contacto eliminado: " + number + " : " + name);
        } else {
            System.out.println("No se encontró el contacto con el número: " + number);
        }
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.load("contacts.txt");

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menú:");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Guardar cambios y salir");

            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {

                // Llama al método list para mostrar los contactos
                case 1 -> addressBook.list();

                // Llama al método create para crear un nuevo contacto
                case 2 -> {
                    System.out.print("Ingresa el número de contacto: ");
                    String number = scanner.nextLine();
                    System.out.print("Ingresa el nombre de contacto: ");
                    String name = scanner.nextLine();
                    addressBook.create(number, name);
                }

                // Llama al método delete para eliminar un contacto
                case 3 -> {
                    System.out.print("Ingresa el número de contacto a borrar: ");
                    String deleteNumber = scanner.nextLine();
                    addressBook.delete(deleteNumber);
                }

                // Llama al método save para guardar los cambios y salir
                case 4 -> {
                    addressBook.save("contacts.txt"); // Llama al método save para guardar los cambios y salir
                    exit = true;
                }
                default -> System.out.println("Opción inválida. Inténtalo de nuevo.");
            }

            System.out.println();
        }

        scanner.close();
    }
}