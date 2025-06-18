import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.BufferedReader;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int findLocationIndex(String[][] arr, String location) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0].equalsIgnoreCase(location)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isAirlineInCountry(String[] airlineCountries, String country) {
        for (int i = 0; i < airlineCountries.length; i++) {
            if (airlineCountries[i].equalsIgnoreCase(country)) {
                return true;
            }
        }
        return false;
    }

    public static boolean findAndPrintRoute(String[][] arr, String cL, String dL, int sp, int travelType) {
        boolean found = false;

        if (travelType == 1) {
            // Direct flight check
            for (int i = 0; i < arr[sp].length; i++) {
                if (arr[sp][i].equalsIgnoreCase(dL)) {
                    System.out.println("✈️ Direct Flight: " + cL + " → " + dL);
                    return true;
                }
            }
        } else {
            // Connected Flights

            // Level 2: via1
            for (int j = 0; j < arr[sp].length && !found; j++) {
                String via1 = arr[sp][j];
                if (via1.equalsIgnoreCase(cL) || via1.equalsIgnoreCase(dL)) continue;

                int via1Index = findLocationIndex(arr, via1);
                if (via1Index == -1) continue;

                for (int k = 0; k < arr[via1Index].length; k++) {
                    if (arr[via1Index][k].equalsIgnoreCase(dL)) {
                        System.out.println("🛫 Connected Flight: " + cL + " → " + via1 + " → " + dL);
                        return true;
                    }
                }
            }

            // Level 3: via1 → via2
            for (int j = 0; j < arr[sp].length && !found; j++) {
                String via1 = arr[sp][j];
                if (via1.equalsIgnoreCase(cL) || via1.equalsIgnoreCase(dL)) continue;
                int via1Index = findLocationIndex(arr, via1);
                if (via1Index == -1) continue;

                for (int i = 0; i < arr[via1Index].length && !found; i++) {
                    String via2 = arr[via1Index][i];
                    if (via2.equalsIgnoreCase(cL) || via2.equalsIgnoreCase(via1) || via2.equalsIgnoreCase(dL)) continue;
                    int via2Index = findLocationIndex(arr, via2);
                    if (via2Index == -1) continue;

                    for (int k = 0; k < arr[via2Index].length; k++) {
                        if (arr[via2Index][k].equalsIgnoreCase(dL)) {
                            System.out.println("🛫 Connected Flight: " + cL + " → " + via2 + " → " + dL);
                            return true;
                        }
                    }
                }
            }

            // Level 4: via1 → via2 → via3
            for (int j = 0; j < arr[sp].length && !found; j++) {
                String via1 = arr[sp][j];
                if (via1.equalsIgnoreCase(cL) || via1.equalsIgnoreCase(dL)) continue;
                int via1Index = findLocationIndex(arr, via1);
                if (via1Index == -1) continue;

                for (int i = 0; i < arr[via1Index].length && !found; i++) {
                    String via2 = arr[via1Index][i];
                    if (via2.equalsIgnoreCase(cL) || via2.equalsIgnoreCase(via1) || via2.equalsIgnoreCase(dL)) continue;
                    int via2Index = findLocationIndex(arr, via2);
                    if (via2Index == -1) continue;

                    for (int z = 0; z < arr[via2Index].length && !found; z++) {
                        String via3 = arr[via2Index][z];
                        if (via3.equalsIgnoreCase(cL) || via3.equalsIgnoreCase(via1) || via3.equalsIgnoreCase(via2) || via3.equalsIgnoreCase(dL)) continue;
                        int via3Index = findLocationIndex(arr, via3);
                        if (via3Index == -1) continue;

                        for (int k = 0; k < arr[via3Index].length; k++) {
                            if (arr[via3Index][k].equalsIgnoreCase(dL)) {
                                System.out.println("🛫 Connected Flight: " + cL + " → "  + via2 + " → " + dL);
                                return true;
                            }
                        }
                    }
                }
            }

            // Level 5: via1 → via2 → via3 → via4
            for (int j = 0; j < arr[sp].length && !found; j++) {
                String via1 = arr[sp][j];
                if (via1.equalsIgnoreCase(cL) || via1.equalsIgnoreCase(dL)) continue;
                int via1Index = findLocationIndex(arr, via1);
                if (via1Index == -1) continue;

                for (int i = 0; i < arr[via1Index].length && !found; i++) {
                    String via2 = arr[via1Index][i];
                    if (via2.equalsIgnoreCase(cL) || via2.equalsIgnoreCase(via1) || via2.equalsIgnoreCase(dL)) continue;
                    int via2Index = findLocationIndex(arr, via2);
                    if (via2Index == -1) continue;

                    for (int z = 0; z < arr[via2Index].length && !found; z++) {
                        String via3 = arr[via2Index][z];
                        if (via3.equalsIgnoreCase(cL) || via3.equalsIgnoreCase(via1) || via3.equalsIgnoreCase(via2) || via3.equalsIgnoreCase(dL)) continue;
                        int via3Index = findLocationIndex(arr, via3);
                        if (via3Index == -1) continue;

                        for (int y = 0; y < arr[via3Index].length && !found; y++) {
                            String via4 = arr[via3Index][y];
                            if (via4.equalsIgnoreCase(cL) || via4.equalsIgnoreCase(via1) || via4.equalsIgnoreCase(via2) || via4.equalsIgnoreCase(via3) || via4.equalsIgnoreCase(dL)) continue;
                            int via4Index = findLocationIndex(arr, via4);
                            if (via4Index == -1) continue;

                            for (int k = 0; k < arr[via4Index].length; k++) {
                                if (arr[via4Index][k].equalsIgnoreCase(dL)) {
                                    System.out.println("🛫 Connected Flight: " + cL + " →"   + via3 + " → " + dL);
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("❌ No possible route found from " + cL + " to " + dL);
        }

        return false;
    }









    public static boolean passcheck(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(regex);
    }

    public static boolean validemail(String emailadderess) {
        String regex1 = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return emailadderess.matches(regex1);
    }


    public static void printBoardingPass(String name, String flight, String from, String to, String time, String seat, String className, String gate,String arrival) {
        String filename = "boarding_pass_" + name.toLowerCase() + ".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("**************** BOARDING PASS✈️ ****************\n");
            writer.write("Passenger     : " + name.toUpperCase() + "\n");
            writer.write("Flight        : " + flight + "\n");
            writer.write("From          : " + from.toUpperCase() + "\n");
            writer.write("To            : " + to.toUpperCase() + "\n");
            writer.write("Seat          : " + seat + "\n");
            writer.write("Class         : " + className + "\n");
            writer.write("Gate          : " + gate + "\n");
            writer.write("Departure Time: " + time + "\n");
            writer.write("Arrival Time  : " + arrival + "\n");
            writer.write("************************************************\n");
            writer.close();
            System.out.println("✅ Boarding pass saved to " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error writing boarding pass file.");
        }
    }

    public static String generateFlightNumber(String airlineName) {
        String code = "";
        switch (airlineName.toLowerCase().replaceAll("\\s+", "")) {
            case "pia": code = "PK"; break;
            case "airindia": code = "AI"; break;
            case "emirates": code = "EK"; break;
            case "airblue": code = "AB"; break;
            case "air koryo": code = "KOR"; break;
            default: code = "XX"; break;
        }

        Random rand = new Random();
        int number = 100 + rand.nextInt(900); // Generates a 3-digit number (100–999)
        return code + number;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        boolean firstconnect = false;
        USER user = null;
//        boolean firstcheck = true;
        int usership;

        do {
            System.out.println("welcome! in our project");
            System.out.println("press 1) for USER");
            System.out.println("press 2) for ADMIN");
            System.out.print("Enter your choice: ");
            usership = sc
                    .nextInt();

            if (usership != 1 && usership != 2) {
                System.out.println("❌ Invalid input. Please enter 1 or 2.\n");
            }

        } while (usership != 1 && usership != 2);


        boolean checkLogOrSign=false;
        int check;
        if (usership == 1) {
            sc.nextLine();
            System.out.println("press 1) for login ");
            System.out.println("press 2) for signup");

            while(!checkLogOrSign) {
                check = sc.nextInt();
                sc.nextLine();
                if (check == 1 || check == 2) {
                    checkLogOrSign = true;
                } else {
                    System.out.println("Please Enter Valid Choice");
                }

                if (check == 1) {
                    System.out.println("LOGIN PAGE");
                    System.out.println("***************************************");
                    {
                        System.out.println("enter the user name");
                        String userlogin1 = sc
                                .nextLine();
                        sc
                                .nextLine();
                        System.out.println("enter the password");
                        String userpass = sc
                                .nextLine();

                        if (user == null || (user.username == null && user.pass == null)) {
                            System.out.println("Please make an account first.");
                        }
                    }
                    System.out.println("press 2) for signup");
                    check = sc
                            .nextInt();
                    sc
                            .nextLine();


                }

                if (check == 2) {
                    System.out.println("SIGNUP PAGE");
                    System.out.println("***************************************");


                    System.out.print("Enter your email: ");
                    String email = sc.nextLine();
                    String ans;
                    while (true) {

                        if (validemail(email)) {
                            System.out.println("✅ Email accepted.");
                            break;
                        } else {
                            System.out.println("❌ Invalid email format. Please try again.");
                            System.out.print("Enter your email: ");
                            email = sc.nextLine();
                        }

                    }


                    //username
                    System.out.print("enter the username:");
                    String username = sc.nextLine();

                    //password

                    String password;
                    do {
                        System.out.print("Enter your password:");
                        password = sc.nextLine();
                        if (passcheck(password)) {
                            System.out.println("✅ your password is strong");
                            user = new USER(email, password, username);
                            break;
                        }
                        if (!passcheck(password)) {
                            System.out.println("❌ Invalid password format. Please try again.");
                        }

                    } while (true);
                    System.out.println("✅ Signup successful!");
                    boolean aftersignup = true;
                    if (check == 2) {
                        while (aftersignup) {
                            System.out.println("LOGIN PAGE");
                            System.out.println("***************************************");

                            System.out.print("enter the user name: ");
                            String userlogin1 = sc.nextLine();
                            System.out.print("enter the password: ");
                            String userpass = sc.nextLine();

                            if ((userlogin1.equals(user.username) && userpass.equals(user.pass))) {
                                System.out.println("✅login successful");
                                aftersignup = false;
                            } else {
                                System.out.println("❌login unsuccessful");
                            }
                        }
                    }
                }
            }
        }


        int flightco = 0;
        int count = 5;
        if (usership == 2) {
            System.out.println("LOGIN PAGE");
            System.out.println("***************************************");
            boolean conadmin = false;
            boolean conaduser = false;
            boolean conadpass = false;
            String useradmin = "adminperson";
            String userpass = "admin123";
            while (!conadmin) {
                while (!conaduser) {
                    System.out.println("enter the username");
                    String usernamead = sc.nextLine().toLowerCase();
                    sc.nextLine();
                    System.out.println("enter the password");
                    String userpassad = sc.nextLine().toLowerCase();
                    if (usernamead.equals(useradmin)) {
                        if (userpassad.equals(userpass)) {

                            System.out.println("login successfull");
                            conaduser = true;
                            conadmin = true;

                        }
                    } else {
                        System.out.println("login unsuccessful");
                    }
                }


            }

            boolean running = true;
            ADMIN[] admin = new ADMIN[5];
            while (running) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Flight");
                System.out.println("2. Remove Flight");
                System.out.println("3. View Flights");
                System.out.println("4. Exit");

                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline


                switch (choice) {
                    case 1:

                        for (int i = flightco; i < admin.length; i++) {
                            System.out.println("Enter Flight name: ");
                            String airlinename = sc.nextLine();
                            System.out.print("ADD FLIGHT NUMBER ");
                            String flightnum = sc.nextLine();

                            System.out.println("seats in eco");
                            int seatineco = sc.nextInt();
                            sc.nextLine();
                            System.out.println("seats in bus");
                            int seatinbus = sc.nextInt();
                            sc.nextLine();
                            System.out.println("seats in stan");
                            int seatinstan = sc.nextInt();
                            sc.nextLine();
                            admin[i] = new ADMIN(airlinename, flightnum, seatineco, seatinbus, seatinstan);
                            System.out.println("✅ Flight added.");
                            flightco++;
                            System.out.println("wanna add more if yes press 1)");
                            System.out.println("for exit press 2)");
                            int con = sc.nextInt();
                            sc.nextLine();
                            if (con == 2) {
                                break;
                            }
                        }
                        break;

                    case 2:

                        System.out.print("Enter Flight Number to Remove: ");
                        String removeNum = sc.nextLine();
                        boolean found = false;
                        for (int i = 0; i < flightco; i++) {
                            if (admin[i].airnum.equals(removeNum)) {  // ✅ Use .equals() to compare strings
                                // Shift flights to the left
                                for (int j = i; j < 5 - 1; j++) {
                                    admin[j] = admin[j + 1];
                                }
                                admin[5 - 1] = null; // optional: clear last duplicate
                                count--; // reduce total flight count
                                System.out.println("❌ Flight removed.");
                                flightco--;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("⚠️ Flight not found.");
                        }
                        break;

                    case 3:
                        System.out.println("--- List of Flights ---");
                        if (flightco == 0) {
                            System.out.println("No flights available.");
                            break;
                        } else {
                            for (int j = 0; j < flightco; j++) {
                                System.out.println("AIRLINE NAME : " + admin[j].airline);
                                System.out.println("AIRLINE NUM : " + admin[j].airnum);
                                System.out.println("*******************");
                            }
                        }
                        break;

                    case 4:
                        running = false;
                        break;

                    default:
                        System.out.println("❌ Invalid option.");
                }
            }
        }




        int flightTimeIndex = -1;

        String[][] arr = {
                {"Pakistan", "Afghanistan", "China", "India", "Iran", "UAE"},                  // 0
                {"Afghanistan", "Pakistan", "Iran", "China", "Uzbekistan", "Turkmenistan"},    // 1
                {"China", "Pakistan", "India", "Afghanistan", "Nepal", "North Korea", "South Korea","Kazakhstan"}, // 2
                {"India", "China", "Nepal", "Pakistan"},                                        // 3
                {"Iran", "Pakistan", "Afghanistan", "Iraq", "Turkey", "Turkmenistan"},         // 4
                {"Nepal", "India", "China","Qatar"},                                                   // 5
                {"Turkey", "Iran", "Iraq"},      {"UAE", "Saudi Arabia", "Oman", "Pakistan"},                                    // 7
                {"Saudi Arabia", "Yemen", "Oman", "UAE", "Iraq","Pakistan"},                               // 8
                {"Oman", "Yemen", "UAE", "Saudi Arabia"},                                       // 9
                {"Yemen", "Saudi Arabia", "Oman","Qatar"},                                              // 10
                {"Iraq", "Iran", "Turkey", "Saudi Arabia"},                                     // 11
                {"Turkmenistan", "Iran", "Uzbekistan", "Afghanistan"},                          // 12
                {"Uzbekistan", "Turkmenistan", "Afghanistan"},                                  // 13
                {"North Korea", "China", "South Korea"},                                        // 14
                {"South Korea", "North Korea", "China"},
                {"Bangladesh", "India", "Nepal", "China"},
                {"Kazakhstan", "Uzbekistan", "China", "Russia","Bangladesh"},
                {"Qatar", "UAE", "Saudi Arabia", "Oman","Nepal"},
                {"Azerbaijan", "Iran", "Turkey", "Russia"},
                {"Russia","Azerbaijan","Kazakhstan","China"}
        };


        String[] piaCountries = {"Pakistan", "UAE", "Saudi Arabia", "China", "Turkey","Yemen","Afghanistan","Nepal","Oman","Qatar","Azerbaijan","Iran","Iraq","Uzbekistan","Turkmenistan","Kazakhstan","South Korea","North Korea","Russia"};

        String[] airblueCountries = {"Pakistan", "UAE", "Saudi Arabia"};

        String[] emiratesCountries = {
                "UAE", "Pakistan", "India", "China", "Saudi Arabia", "Iran", "Oman","Iraq", "Russia", "Qatar", "Bangladesh", "Nepal", "South Korea", "North Korea", "Turkey"};
        String[] airIndiaCountries = {"India","Qatar", "UAE","China", "Nepal", "Pakistan","Bangladesh","Kazakhstan","Turkey","yemen","Russia"};
        String[] airKoryo={"North Korea","South Korea","China"};

        String[][] flightTimes = {
                {"Today at 11:00 PM", "Tomorrow at 3:00 PM"}, // 0 → Emirates
                {"Today at 8:00 AM", "Tomorrow at 10:00 PM"}, // 1 → PIA
                {"Today at 1:00 PM", "Tomorrow at 6:00 PM"},  // 2 → AirBlue
                {"Today at 5:00 AM", "Tomorrow at 9:00 PM"},   // 3 → Air India
                {"Today at 6:00 AM", "Tomorrow at 2:00 AM"}    // 4-> AirKoryo
        };
        String[][] Arrivaltimedirect = {
                {"Tomorrow at 3:00 AM", "Tomorrow at 7:00 PM"}, // 0 → Emirates
                {"Today at 12:00 PM", "After The Day Of Tomorrow at 2:00 AM"}, // 1 → PIA
                {"Today at 5:00 PM", "Tomorrow at 10:00 PM"},  // 2 → AirBlue
                {"Today at 8:00 AM", "After The Day Of Tomorrow at 1:00 AM"},   // 3 → Air India
                {"Today at 9:00 AM", "Tomorrow at 6:00 AM"}    // 4-> AirKoryo
        };
        String[][] Arrivaltimevia = {
                {"Tomorrow at 11:00 AM", "The day after tomorrow at 3:00 AM"}, // 0 → Emirates
                {"Today at 6:00 PM", "The day after Tomorrow at 9:00 AM"}, // 1 → PIA
                {"Today at 11:00 PM", "The day after Tomorrow at 5:00 AM"},  // 2 → AirBlue
                {"Today at 4:00 PM", "After The Day Of Tomorrow at 8:00 AM"},   // 3 → Air India
                {"Today at 2:00 PM", "Tomorrow at 1:00 PM"}    // 4-> AirKoryo
        };

        int[][] flightPrices = {
                {250000, 150000, 100000}, // Emirates (index 0)
                {150000, 100000, 70000},  // PIA (index 1)
                {95000, 80000, 65000},   // AirBlue (index 2)
                {120000, 95000, 60000},  // AirIndia (index 3)
                {80000, 60000, 40000}    // Air Koryo (index 4)
        };


        System.out.println("***************************************");
        System.out.println("\t\tFLIGHT BOOKING ✈️");
        System.out.println("***************************************");

        String[]  seatnumfor1 = {"1A","2A" ,"3A" ,"4A","5A","6A" ,"7A" ,"8A","9A","10A" ,"11A" ,"12A","13A","14A" ,"15A" ,"16A"};
        boolean[] seatAssigned1 = new boolean[seatnumfor1.length];

        String[]  seatnumfor2 = {"1A","2A" ,"3A" ,"4A","5A","6A" ,"7A" ,"8A","9A","10A" ,"11A" ,"12A","13A","14A" ,"15A" ,"16A"};
        boolean[] seatAssigned2 = new boolean[seatnumfor2.length];

        String[]  seatnumfor3 = {"1A","2A" ,"3A" ,"4A","5A","6A" ,"7A" ,"8A","9A","10A" ,"11A" ,"12A","13A","14A" ,"15A" ,"16A"};
        boolean[] seatAssigned3 = new boolean[seatnumfor3.length];

        Random rand = new Random();
        int randomnum1 = rand.nextInt(seatnumfor1.length);
        int randomnum2 = rand.nextInt(seatnumfor2.length);
        int randomnum3 = rand.nextInt(seatnumfor3.length);

        boolean currentLocation=false;
        boolean destinationLocation=false;
        boolean flightWay=false;
        int travelType=-1;
        String cL="";
        String dL="";
        int sp = -1;

        while(!currentLocation) {
            System.out.println("🌍 Enter your Current Location");
            cL = sc.nextLine();


            for (int i = 0; i < arr.length; i++) {
                if (arr[i][0].equalsIgnoreCase(cL)) {
                    sp = i;
                    currentLocation = true;
                    break;
                }
            }
            if (!currentLocation) {
                System.out.println("❌ Invalid location. Please try again.");
            }
        }

        if (sp != -1) {
            String[] gate = {"A", "B", "C"};
            while (!destinationLocation) {
                System.out.println("WHERE TO GO?📍");
                dL = sc.nextLine();

                if (!dL.equalsIgnoreCase(cL)) {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i][0].equalsIgnoreCase(dL)) {
                            destinationLocation = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("Destination Location Can't be same as Current Location");
                }
                if (!destinationLocation) {
                    System.out.println("❌ Invalid location. Please try again.");
                }
            }

            while (!flightWay) {
                System.out.println("Do you want to travel\n1- Direct\n2- Via (connected flight)?");
                travelType = sc.nextInt();
                if (travelType == 1 || travelType == 2) {
                    flightWay = true;
                } else {
                    System.out.println("❌ Invalid Choice. Please try again");
                }
            }
            sc.nextLine();

            String[] availableAirlines = new String[5]; // max 5 airlines
            int c = 0;
            String airline="";
            String flightNumber="";

            if (travelType == 1) {
                if (isAirlineInCountry(piaCountries, cL) && isAirlineInCountry(piaCountries, dL)) {
                    airline = "PIA";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }

                if (isAirlineInCountry(airblueCountries, cL) && isAirlineInCountry(airblueCountries, dL)) {

                     airline = "AirBlue";
                     flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(emiratesCountries, cL) && isAirlineInCountry(emiratesCountries, dL)) {
                     airline = "Emirates";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(airIndiaCountries, cL) && isAirlineInCountry(airIndiaCountries, dL)) {
                     airline = "AirIndia";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(airKoryo, cL) && isAirlineInCountry(airKoryo, dL)) {
                    airline = "Air Koryo";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
            } else if (travelType == 2) {
                boolean viaAvailable = findAndPrintRoute(arr, cL, dL, findLocationIndex(arr, cL), 2);

                if (!viaAvailable) {
                    System.out.println("No via route available from " + cL + " to " + dL);
                    return;
                }

                if (isAirlineInCountry(piaCountries, cL)) {
                    airline = "PIA";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(airblueCountries, cL)) {
                    airline = "AirBlue";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(emiratesCountries, cL)) {
                    airline = "Emirates";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(airIndiaCountries, cL)) {
                    airline = "AirIndia";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
                if (isAirlineInCountry(airKoryo, cL)) {
                    airline = "Air Koryo";
                    flightNumber = generateFlightNumber(airline);
                    availableAirlines[c++] = airline + " - " + flightNumber;
                }
            } else {
                System.out.println("Invalid choice! Please Select Valid Option");
                return;
            }

            if (c == 0) {
                System.out.println("No available airlines match your route and choice.");
                return;
            }

            System.out.println("Available airlines:");
            for (int i = 0; i < c; i++) {
                System.out.println((i + 1) + ". " + availableAirlines[i]);
            }

            boolean checkOpt=false;
            String selectedAirline="";
            String selectedTime="";
            while(!checkOpt) {

                System.out.println("Select the airline by number:");
                int opt = sc.nextInt();
                sc.nextLine(); // consume leftover newline

                if (opt < 1 || opt > c) {
                    System.out.println("Invalid airline selection.");
                } else {
                    checkOpt = true;

                    String flightType = "";
                    if (travelType == 1) {
                        flightType = "Direct Flight";
                    } else {
                        flightType = "Connected Flight";
                    }

                    selectedAirline = availableAirlines[opt - 1];
                    String cAirline = selectedAirline.split(" - ")[0];
                    System.out.println("You selected: " + selectedAirline + " " + flightType);

                    switch (cAirline) {
                        case "Emirates":
                            flightTimeIndex = 0;
                            break;
                        case "PIA":
                            flightTimeIndex = 1;
                            break;
                        case "AirBlue":
                            flightTimeIndex = 2;
                            break;
                        case "AirIndia":
                            flightTimeIndex = 3;
                            break;
                        case "Air Koryo":
                            flightTimeIndex = 4;
                            break;
                        default:
                            flightTimeIndex = -1;
                    }
                    int now = 0;
                    boolean con3 = true;
                    int scheduleChoice = 0;
                    if (flightTimeIndex != -1) {
                        System.out.println("Select your flight schedule:");
                        System.out.println("1. " + flightTimes[flightTimeIndex][0]);
                        System.out.println("2. " + flightTimes[flightTimeIndex][1]);
                        while (con3) {
                            System.out.println("Choose 1 or 2: ");

                            scheduleChoice = sc.nextInt();
                            now = scheduleChoice;
                            sc.nextLine();
                            if (scheduleChoice == 1 || scheduleChoice == 2) {
                                con3 = false;
                            } else {
                                System.out.println("enter the correct selection");
                                System.out.println("1. " + flightTimes[flightTimeIndex][0]);
                                System.out.println("2. " + flightTimes[flightTimeIndex][1]);
                            }
                        }
                        if (scheduleChoice == 1 || scheduleChoice == 2) {
                            selectedTime = flightTimes[flightTimeIndex][scheduleChoice - 1];
                            System.out.println("Your Flight scheduled for  " + selectedTime);
                        } else {
                            System.out.println("Enter valid choice");
                        }
                    } else {
                        System.out.println("Flight times not available for the selected airline.");
                    }

                    String arrivalTime="";
                    if(travelType == 1){
                      arrivalTime= Arrivaltimedirect[flightTimeIndex][now - 1];
                    }
                    if(travelType == 2){
                        arrivalTime=Arrivaltimevia[flightTimeIndex][now - 1];
                    }


                    int chooseClass = 0;
                    int choosePersons;

                    System.out.println("***************************************");
                    boolean con5 = true;
                    while (con5) {
                        System.out.println("Select the Class (Choose number)");
                        System.out.println("1- First Class \n2- Business Class \n3- Economy Class");
                        chooseClass = sc.nextInt();
                        if (chooseClass == 1 || chooseClass == 2 || chooseClass == 3) {
                            con5 = false;
                        } else {
                            System.out.println("enter the correct class");
                        }
                    }
                    int price = -1;
                    if (flightTimeIndex >= 0 && flightTimeIndex < flightPrices.length && chooseClass >= 1 && chooseClass <= 3) {
                        price = flightPrices[flightTimeIndex][chooseClass - 1];
                        String className = switch (chooseClass) {
                            case 1 -> "First Class";
                            case 2 -> "Business Class";
                            case 3 -> "Economy Class";
                            default -> "Unknown";
                        };
                        System.out.println("You selected: " + className);
                        System.out.println("Total Fare: PKR " + price + "(per person)");
                        System.out.println("Luggage weight Allowed: 30 kg (per person)");

                    } else {
                        System.out.println("Invalid class or airline selection.");
                    }

                    System.out.println("***************************************");

                    boolean personCheck = false;
                    while (!personCheck) {
                        System.out.println("How Many Persons to Go?");
                        choosePersons = sc.nextInt();
                        String[] seatNumbers = new String[choosePersons];
                        String[] classNames = new String[choosePersons];
                        String[] gatesAssigned = new String[choosePersons];

                        sc.nextLine(); // Consume leftover newline

                        if (choosePersons > 0) {

                            personCheck = true;

                            String[] names = new String[choosePersons];
                            String[] ids = new String[choosePersons];
                            String[][] verification = new String[choosePersons][2];

                            for (int i = 0; i < choosePersons; i++) {
                                System.out.println("Enter name of person " + (i + 1) + ":");
                                names[i] = sc.nextLine();
                                verification[i][0] = names[i];

                                System.out.println("Enter ID of person " + (i + 1) + ":");
                                ids[i] = sc.nextLine();
                                verification[i][1] = ids[i];
                            }
                            boolean con4 = true;
                            int currencyChoice = 0;
                            System.out.println("***************************************");
                            while (con4) {
                                System.out.println("Choose Payment Currency");
                                System.out.println("1- Dollars\t\t\t($) \n2- Rupees\t\t\t(PKR) \n3- Dirham\t\t\t(AED) \n4- Chinese yuan\t\t(¥)");
                                currencyChoice = sc.nextInt();
                                sc.nextLine(); // consume newline
                                if (currencyChoice == 1 || currencyChoice == 2 || currencyChoice == 3 || currencyChoice == 4) {
                                    con4 = false;
                                } else {
                                    System.out.println("enter the correct payment currency");
                                }
                            }
                            String currencySymbol = "";
                            double conversionRate ; // PKR by default

                            switch (currencyChoice) {
                                case 1: // Dollars
                                    currencySymbol = "$";
                                    conversionRate = 1.0 / 280.0; // Example: 1 USD = 280 PKR
                                    break;
                                case 2: // Rupees
                                    currencySymbol = "PKR";
                                    conversionRate = 1.0; // PKR to PKR
                                    break;
                                case 3: // Dirham
                                    currencySymbol = "AED";
                                    conversionRate = 1.0 / 76.0; // Example: 1 AED = 76 PKR
                                    break;
                                case 4: // Chinese Yuan
                                    currencySymbol = "¥";
                                    conversionRate = 1.0 / 39.0; // Example: 1 CNY = 39 PKR
                                    break;
                                default:
                                    System.out.println("Invalid currency selection. Defaulting to PKR.");
                                    currencySymbol = "PKR";
                                    conversionRate = 1.0;
                            }

                            int totalFarePKR = price * choosePersons;
                            double convertedFare = totalFarePKR * conversionRate;

                            System.out.printf("Total fare for %d person(s): %.2f %s%n", choosePersons, convertedFare, currencySymbol);

                            System.out.println("***************************************");

                            boolean paymentCheck = false;

                            while (!paymentCheck) {

                                System.out.println("Select Payment Method");
                                System.out.println("1- Standard Chartered \n2- National Bank of Pakistan \n3- HSBC UAE \n4- Commercial Bank of China (ICBC) ");
                                int bank = sc.nextInt();
                                sc.nextLine();
                                switch (bank) {
                                    case 1:
                                        System.out.println("You selected Standard Chartered ");
                                        paymentCheck = true;
                                        break;
                                    case 2:
                                        System.out.println("You selected  National Bank of Pakistan ");
                                        paymentCheck = true;
                                        break;
                                    case 3:
                                        System.out.println("You selected HSBC UAE");
                                        paymentCheck = true;
                                        break;
                                    case 4:
                                        System.out.println(" You selected Commercial Bank of China (ICBC)");
                                        paymentCheck = true;
                                        break;
                                    default:
                                        System.out.println("Select Valid Option Please");
                                }
                                int flag = -1;
                                boolean paymentStatus = false;
                                while (!paymentStatus) {
                                    System.out.println("Payment Done? \n1- YES \n2-NO");
                                    flag = sc.nextInt();
                                    sc.nextLine();
                                    if (flag == 1) {
                                        paymentStatus = true;
                                        break;
                                    } else {
                                        System.out.println("Clear Your Dues Please");
                                    }
                                }

                                try (FileWriter writer = new FileWriter("C:\\Users\\PMLS\\Documents\\Ticket.txt")) {
                                    writer.write("\n***************************************\n");
                                    writer.write("           ✈️ Ticket Summary ✈️         \n");
                                    writer.write("***************************************\n");

                                    for (
                                            int i = 0;
                                            i < choosePersons; i++) {
                                        writer.write("----------- Ticket " + (i + 1) + " -----------\n");
                                        writer.write("Passenger Name : " + names[i].toUpperCase() + "\n");
                                        writer.write("Passenger ID   : " + ids[i] + "\n");
                                        writer.write("Flight         : " + selectedAirline + "\n");
                                        writer.write("Departure      : " + flightTimes[flightTimeIndex][now - 1] + "\n");

                                        int seatIndex = -1;

                                        if (chooseClass == 1) {
                                            do {
                                                seatIndex = rand.nextInt(seatnumfor1.length);
                                            } while (seatAssigned1[seatIndex]); // retry if already assigned
                                            seatAssigned1[seatIndex] = true; // mark seat as assigned

                                            seatNumbers[i] = seatnumfor1[seatIndex];
                                            classNames[i] = "First Class";
                                            gatesAssigned[i] = gate[0];

                                            writer.write("SeatNum        : " + seatnumfor1[seatIndex] + "\n");
                                            writer.write("Gate           : " + gate[0] + "\n");
                                            writer.write("Class          : First Class\n");
                                        }

                                        else if (chooseClass == 2) {
                                            do {
                                                seatIndex = rand.nextInt(seatnumfor2.length);
                                            } while (seatAssigned2[seatIndex]);

                                            seatAssigned2[seatIndex] = true;

                                            // ✅ Add these missing assignments:
                                            seatNumbers[i] = seatnumfor2[seatIndex];
                                            classNames[i] = "Business Class";
                                            gatesAssigned[i] = gate[1];

                                            writer.write("SeatNum        : " + seatnumfor2[seatIndex] + "\n");
                                            writer.write("Gate           : " + gate[1] + "\n");
                                            writer.write("Class          : Business Class\n");
                                        }

                                        else if (chooseClass == 3) {
                                            do {
                                                seatIndex = rand.nextInt(seatnumfor3.length);
                                            } while (seatAssigned3[seatIndex]);

                                            seatAssigned3[seatIndex] = true;

                                            // ✅ Assign values for use in check-in
                                            seatNumbers[i] = seatnumfor3[seatIndex];
                                            classNames[i] = "Economy Class";
                                            gatesAssigned[i] = gate[2];

                                            // ✅ Write ticket details
                                            writer.write("SeatNum        : " + seatnumfor3[seatIndex] + "\n");
                                            writer.write("Gate           : " + gate[2] + "\n");
                                            writer.write("Class          : Economy Class\n");
                                        }
                                        writer.write("From           : " + cL.toUpperCase() + "\n");
                                        writer.write("To             : " + dL.toUpperCase() + "\n");
                                        writer.write("--------------------------------\n\n");

                                    }
                                    System.out.println("Check Your Ticket in File!");

                                } catch (IOException e) {
                                    System.out.println("not possible");
                                }

                                String checkInID = "";
                                String checkInName = "";
                                String checkInDestination = "";
                                String checkInAirLine = "";
                                int checkInWeight;
                                int overweightKg;
                                boolean checkWeight = false;
                                System.out.println(selectedAirline+"Debug");
                                System.out.println("***************************************");
                                System.out.println("\t\t WELCOME TO CHECK-IN");
                                System.out.println("***************************************");

                                for (int attempt = 0; attempt < choosePersons; attempt++) {

                                    System.out.println("Your Name:");
                                    checkInName = sc.nextLine();

                                    System.out.println("Show Your ID Please:");
                                    checkInID = sc.nextLine();

                                    boolean verified = false;

                                    int passengerIndex = -1;
                                    for (int i = 0; i < choosePersons; i++) {
                                        if (verification[i][0].equalsIgnoreCase(checkInName) && verification[i][1].equalsIgnoreCase(checkInID)) {
                                            passengerIndex = i;
                                            verified=true;
                                            break;
                                        }
                                    }
                                    if (verified) {

                                        System.out.println("Your Destination?");
                                        checkInDestination = sc.nextLine();

                                        if (!checkInDestination.equalsIgnoreCase(dL)) {
                                            System.out.println("❌ Destination does not match our records. Next Person Please!");
                                            continue; // Skip to next passenger
                                        }

                                        System.out.println("From Which Airline You are Going?");
                                        checkInAirLine = sc.nextLine();

                                        if (!checkInAirLine.equalsIgnoreCase(cAirline)) {
                                            System.out.println("❌ Airline mismatch. Next Person Please!");
                                            continue; // Skip to next passenger
                                        }

                                        // Luggage check starts here
                                         checkWeight = false;
                                        while (!checkWeight) {
                                            System.out.println("Enter Your Luggage weight");
                                            checkInWeight = sc.nextInt();
                                            sc.nextLine();

                                            if (checkInWeight <= 30 && checkInWeight>0) {
                                                System.out.println("✅ Verification successful. Welcome, " + checkInName + "!");
                                                printBoardingPass(checkInName, selectedAirline, cL, checkInDestination, selectedTime, seatNumbers[passengerIndex], classNames[passengerIndex], gatesAssigned[passengerIndex], arrivalTime);
                                                checkWeight = true;
                                            } else {
                                                 overweightKg = checkInWeight - 30;
                                                double overweightFeeInUserCurrency = overweightKg * 500 * conversionRate;

                                                System.out.printf("You are %d kg over the allowed 30kg.%n", overweightKg);
                                                System.out.printf("Please pay an overweight fee of: %.2f %s%n", overweightFeeInUserCurrency, currencySymbol);

                                                // Confirm fee payment
                                                boolean feePaid = false;
                                                while (!feePaid) {
                                                    System.out.println("Overweight Fee Paid? \n1 - Yes \n2 - No");
                                                    int pay = sc.nextInt();
                                                    sc.nextLine();
                                                    if (pay == 1) {
                                                        feePaid = true;
                                                        checkWeight = true;
                                                        System.out.println("✅ Thank you! Verification complete. Welcome, " + checkInName + "!");
                                                        printBoardingPass(checkInName, selectedAirline, cL, checkInDestination, selectedTime, seatNumbers[passengerIndex], classNames[passengerIndex], gatesAssigned[passengerIndex], arrivalTime);


                                                    } else {
                                                        System.out.println("❌ Please clear your overweight dues.");
                                                    }
                                                }
                                            }
                                        }

                                    } else {
                                        System.out.println("❌ Verification failed. Name or ID did not match. Next Person Please!");
                                    }
                                }



                            }


                        }
                        else {
                            System.out.println("Enter Valid Number");
                        }
                    }
                }
            }
        }else{
            System.out.println("Current location not found.");
        }
    }
}
