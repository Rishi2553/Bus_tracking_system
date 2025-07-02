import java.io.*;
import java.util.*;

public class Server {
    public static int currentStop = 0;
    public static int[] passengerCount = new int[32];
    public static final String FILE_PATH = "bus_status.txt";

    public static void saveStateToFile() {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            writer.println("currentStop=" + currentStop);
            writer.print("passengerCount=");
            for (int i = 0; i < 32; i++) {
                writer.print(passengerCount[i]);
                if (i < 31) writer.print(",");
            }
            writer.println();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void loadStateFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("currentStop=")) {
                    currentStop = Integer.parseInt(line.split("=")[1]);
                } else if (line.startsWith("passengerCount=")) {
                    String[] parts = line.split("=")[1].split(",");
                    for (int i = 0; i < 32; i++) {
                        passengerCount[i] = Integer.parseInt(parts[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public static final String[] stopNames = new String[32];
    public static final Map<String, Integer> stopMap = new HashMap<>();

    static {
        stopNames[0] = "Trip Not Started";
        stopNames[1] = "Vandalur Zoo";
        stopNames[2] = "Vandalur Railway Station Gate";
        stopNames[3] = "Perungalathur";
        stopNames[4] = "Peerkankaranai";
        stopNames[5] = "Irumbuliyur";
        stopNames[6] = "Tambaram West Bus Station";
        stopNames[7] = "Kadaperi";
        stopNames[8] = "Tambaram Sanatorium";
        stopNames[9] = "Chromepet M.I.T. Flyover";
        stopNames[10] = "Chromepet";
        stopNames[11] = "Sarvana Store / Chromepet E.S.I";
        stopNames[12] = "Pallavaram";
        stopNames[13] = "Thrisoolam";
        stopNames[14] = "Airport Metro Station";
        stopNames[15] = "Meenambakkam";
        stopNames[16] = "Old Airport";
        stopNames[17] = "Cement Road";
        stopNames[18] = "Alandur Depot";
        stopNames[19] = "Cantonment Board (St. Thomas Mount Head Post Office)";
        stopNames[20] = "CIPET";
        stopNames[21] = "Ekkatuthangal / Ambul Nagar";
        stopNames[22] = "Ekkatuthangal";
        stopNames[23] = "Defence Colony (Kalaimadal Nagar)";
        stopNames[24] = "Jaffarkhan Pet";
        stopNames[25] = "Ashok Pillar / Udhayam Theatre";
        stopNames[26] = "Vadapalani";
        stopNames[27] = "Thiru Nagar";
        stopNames[28] = "Mmda";
        stopNames[29] = "Arumbakkam";
        stopNames[30] = "M.G.R.Koyambedu (C.M.B.T.)";
        stopNames[31] = "Trip Completed";

        for (int i = 1; i <= 30; i++) {
            stopMap.put(stopNames[i], i);
        }
    }
}