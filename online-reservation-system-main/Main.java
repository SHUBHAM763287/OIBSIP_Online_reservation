import java.util.*;

class Reservation {
    private int id;
    private String source;
    private String destination;
    private String date;

    public Reservation(int id, String source, String destination, String date) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    //Override
    public String toString() {
        return "Reservation [id=" + id + ", source=" + source + ", destination=" + destination + ", date=" + date + "]";
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class ReservationSystem {
    private List<Reservation> reservations;
    private List<User> users;
    private int nextReservationId;

    public ReservationSystem() {
        reservations = new ArrayList<>();
        users = new ArrayList<>();
        nextReservationId = 1;
    }

    public void addUser(String username, String password) {
        users.add(new User(username, password));
        System.out.println("User added: " + username);
    }

    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addReservation(String source, String destination, String date) {
        Reservation reservation = new Reservation(nextReservationId, source, destination, date);
        reservations.add(reservation);
        nextReservationId++;
        System.out.println("Reservation added: " + reservation);
    }

    public void listReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations available.");
        } else {
            System.out.println("Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReservationSystem reservationSystem = new ReservationSystem();
	System.out.println("///at first you have to register///");

        while (true) {
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String newUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    reservationSystem.addUser(newUser, newPassword);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (reservationSystem.authenticateUser(username, password)) {
                        System.out.println("Authentication successful!");
                        System.out.println("1. Make Reservation");
                        System.out.println("2. List Reservations");
                        System.out.println("3. Logout");
                        System.out.print("Enter your choice: ");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        switch (userChoice) {
                            case 1:
                                System.out.print("Enter source: ");
                                String source = scanner.nextLine();
                                System.out.print("Enter destination: ");
                                String destination = scanner.nextLine();
                                System.out.print("Enter date: ");
                                String date = scanner.nextLine();
                                reservationSystem.addReservation(source, destination, date);
                      		  continue;
        
                            case 2:
                                reservationSystem.listReservations();
                                break;
                            case 3:
                                System.out.println("Logging out...");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Authentication failed.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
