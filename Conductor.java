import java.util.Scanner;

public class Conductor {

    public void moveToNextStop() {
        Server.loadStateFromFile();
        if (Server.currentStop < 31) {
            Server.currentStop++;
            Server.saveStateToFile();
            System.out.println("Moved to stop: " + Server.stopNames[Server.currentStop]);
        } else {
            System.out.println("End of trip reached.");
        }
    }

    public void addPassenger(int fromStop, int toStop) {
        for (int i = fromStop; i < toStop; i++) {
            Server.passengerCount[i]++;
        }
    }

    public void issueTicket() {
        Scanner scanner = new Scanner(System.in);
        Server.loadStateFromFile();
        System.out.println("Select your destination stop:");

        for (int i = Server.currentStop + 1; i <= 30; i++) {
            System.out.println(i + ". " + Server.stopNames[i]);
        }

        int destStop;

        while (true) {
            System.out.print("Enter destination stop number: ");
            destStop = scanner.nextInt();

            if (destStop > Server.currentStop && destStop <= 31) {
                break;
            } else {
                System.out.println("Invalid stop number.");
                System.out.print("Enter 1 to try again or 2 to cancel: ");
                int choice = scanner.nextInt();
                if (choice == 2) {
                    System.out.println("Ticket issue cancelled.");
                    return;
                }
            }
        }

        addPassenger(Server.currentStop, destStop);
        Server.saveStateToFile();
        System.out.println("Ticket issued from \"" + Server.stopNames[Server.currentStop] + "\" to \"" + Server.stopNames[destStop] + "\"");
    }

    public void resetTrip() {
        Server.loadStateFromFile();
        Server.currentStop = 0;
        for (int i = 0; i < Server.passengerCount.length; i++) {
            Server.passengerCount[i] = 0;
        }
        Server.saveStateToFile();
        System.out.println("Trip has been reset. All passenger counts cleared.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Conductor conductor = new Conductor();

        while (true) {
            System.out.println("\n--- Conductor Menu ---");
            System.out.println("1. Issue Ticket");
            System.out.println("2. Move to Next Stop");
            System.out.println("3. Reset Trip");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> conductor.issueTicket();
                case 2 -> conductor.moveToNextStop();
                case 3 -> conductor.resetTrip();
                 case 4 -> {
                conductor.resetTrip(); // 👈 Reset before exiting
                System.out.println("Trip reset. Exiting Conductor Program...");
                return;
            }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
