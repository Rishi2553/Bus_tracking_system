import java.util.Scanner;

public class Passenger {
    public static void checkBusStatus() {
        Server.loadStateFromFile();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Available Stops:");
        for (int i = 1; i <= 30; i++) {
            System.out.println(i + ". " + Server.stopNames[i]);
        }

        System.out.print("Enter your current stop number: ");
        int passengerStop = scanner.nextInt();

        if (Server.currentStop == 0 || Server.currentStop > passengerStop) {
            System.out.println("Bus not available or already passed your stop.");
        } else {
            System.out.println("Current Passenger Count in Bus: " + Server.passengerCount[Server.currentStop]);
            System.out.println("Expected Passenger Count at Your Stop: " + Server.passengerCount[passengerStop]);
        }
    }

    public static void main(String[] args) {
        checkBusStatus();
    }
}