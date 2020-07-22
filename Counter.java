package canada.airport;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Counter {

    private static ArrayList<Flight> flights = new ArrayList<Flight>();
    private static ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private static Scanner intBoard = new Scanner(System.in);
    private static Scanner stringBoard = new Scanner(System.in);

    public static void main(String args[]) {
        int input = 0;
        while (true) {
            System.out.println("************ MAIN MENU ************");
            System.out.println("1. Update Database");
            System.out.println("2. Display Arrivals");
            System.out.println("3. Display Departures");
            System.out.println("4. Display Air Canada Flights");
            System.out.println("5. Purchase Tickets");
            System.out.println("6. Refund Tickets");
            System.out.println("7. Logoff");

            input = intBoard.nextInt();

            switch (input) {
                case 1:
                    updateDatabase();
                    break;
                case 2:
                    displayArrivals();
                    break;
                case 3:
                    displayDepartures();
                    break;
                case 4:
                    displayAirCanada();
                    break;
                case 5:
                    purchaseTickets();
                    break;
                case 6:
                    refundTickets();
                    break;
                case 7:
                    logoff();
                    break;
                default:
                    System.out.println("Invalid menu entry!");
                    break;
            }
        }
    }

    // Complete the folllowing methods
    public static void updateDatabase() {
        try {
            System.out.println("File name: ");
            Scanner keyboard = new Scanner(System.in);
            String name = keyboard.nextLine();

            Scanner file = new Scanner(new File(name + ".txt"));
            int count = 0;
            int i = 0;

            while (file.hasNextLine()) {
                flights.add(new Flight(file.nextLine()));
                i++;
            }
        } catch (FileNotFoundException | InputMismatchException ex) {
            Logger.getLogger(Counter.class.getName()).log(Level.SEVERE, null, ex); //just got this from clicking the error, idk what it does lol
        }
    }

    public static void displayArrivals() {
        System.out.println("Arrival flights are:");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Airline", "Flight Number", "Destination", "Date", "Time", "Terminal");
        GregorianCalendar gcal = new GregorianCalendar();
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).status().equals("ARR")
                    && (gcal.get(GregorianCalendar.DATE) == Integer.parseInt(flights.get(i).date().substring(0, 2)))
                    && (gcal.get(GregorianCalendar.MONTH) + 1 == Integer.parseInt(flights.get(i).date().substring(3, 5)))
                    && (gcal.get(GregorianCalendar.YEAR) == Integer.parseInt(flights.get(i).date().substring(6)))) {
                System.out.print(flights.get(i).display());
            }
        }
    }

    public static void displayDepartures() {
        System.out.println("Departure flights are: ");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Airline", "Flight Number", "Destination", "Date", "Time", "Terminal");
        GregorianCalendar gcal = new GregorianCalendar();
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).status().equals("DEP")
                    && (gcal.get(GregorianCalendar.YEAR) == Integer.parseInt(flights.get(i).date().substring(6)))) {
                if (gcal.get(GregorianCalendar.MONTH) + 1 == Integer.parseInt(flights.get(i).date().substring(3, 5))) {
                    if (gcal.get(GregorianCalendar.DATE) == Integer.parseInt(flights.get(i).date().substring(0, 2))) {
                        if ((gcal.get(gcal.HOUR_OF_DAY)) < Integer.parseInt(flights.get(i).time().substring(0, 2))
                                || ((gcal.get(gcal.HOUR_OF_DAY)) == Integer.parseInt(flights.get(i).time().substring(0, 2)))
                                && gcal.get(gcal.MINUTE) <= Integer.parseInt(flights.get(i).time().substring(3))) {
                            System.out.print(flights.get(i).display());
                        }
                    }
                }

            }
        }
    }

    public static void displayAirCanada() {
        System.out.println("All Air Canada flights are:");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Status", "Flight Number", "Destination", "Date", "Time", "Terminal");
        GregorianCalendar gcal = new GregorianCalendar();
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).flightNumber().substring(0, 2).equals("AC")) {
                if (gcal.get(GregorianCalendar.YEAR) < Integer.parseInt(flights.get(i).date().substring(6))) {
                    System.out.print(flights.get(i).displayAirCan());
                } else if (gcal.get(GregorianCalendar.YEAR) == Integer.parseInt(flights.get(i).date().substring(6))) {
                    if (gcal.get(GregorianCalendar.MONTH) + 1 < Integer.parseInt(flights.get(i).date().substring(3, 5))) {
                        System.out.print(flights.get(i).displayAirCan());
                    } else if (gcal.get(GregorianCalendar.MONTH) + 1 == Integer.parseInt(flights.get(i).date().substring(3, 5))) {
                        if (gcal.get(GregorianCalendar.DATE) < Integer.parseInt(flights.get(i).date().substring(0, 2))) {
                            System.out.print(flights.get(i).displayAirCan());
                        } else if (gcal.get(GregorianCalendar.DATE) == Integer.parseInt(flights.get(i).date().substring(0, 2))) {
                            if (gcal.get(gcal.HOUR_OF_DAY) < Integer.parseInt(flights.get(i).time().substring(0, 2))) {
                                System.out.print(flights.get(i).displayAirCan());
                            } else if (gcal.get(gcal.HOUR_OF_DAY) == Integer.parseInt(flights.get(i).time().substring(0, 2))) {
                                if (gcal.get(gcal.MINUTE) <= Integer.parseInt(flights.get(i).time().substring(3))) {
                                    System.out.print(flights.get(i).displayAirCan());
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public static void purchaseTickets() {
        System.out.println("\nAll departing Air Canada flights for today are:");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Choice", "Flight Number", "Destination", "Date", "Time", "Terminal", "Seats Left", "Price");
        GregorianCalendar gcal = new GregorianCalendar();
        int options = 0;
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).flightNumber().substring(0, 2).equals("AC")
                    && (flights.get(i).status().equals("DEP"))
                    && (gcal.get(GregorianCalendar.YEAR) == Integer.parseInt(flights.get(i).date().substring(6)))) {
                if (gcal.get(GregorianCalendar.MONTH) + 1 == Integer.parseInt(flights.get(i).date().substring(3, 5))) {
                    if (gcal.get(GregorianCalendar.DATE) == Integer.parseInt(flights.get(i).date().substring(0, 2))) {
                        if ((gcal.get(gcal.HOUR_OF_DAY)) + 1 < Integer.parseInt(flights.get(i).time().substring(0, 2))
                                || ((gcal.get(gcal.HOUR_OF_DAY)) + 1 == Integer.parseInt(flights.get(i).time().substring(0, 2)))
                                && gcal.get(gcal.MINUTE) <= Integer.parseInt(flights.get(i).time().substring(3))) {
                            System.out.print(options + 1 + String.format("%-19s", ".") + flights.get(i).displayTickets());
                            flights.get(i).setChoice(options + 1);
                            options++;
                        }
                    }
                }
            }
        }

        boolean badChoice = true;
        int choice = 0;
        while (badChoice) {
            System.out.println("Which flight would you like?");
            int inp = intBoard.nextInt();
            if (inp > options || inp == 0) {
                badChoice = true;
                System.out.println("That is not an option. Please input an actual option.");
            } else {
                badChoice = false;
                choice = inp;
                break;
            }
        }

        for (int i = 0; i < flights.size(); i++) {
            if (choice == flights.get(i).choice()) {
                System.out.println("What is your name?");
                flights.get(i).setName(stringBoard.nextLine());
                int purchase = 0;
                boolean invalid = true;
                while (invalid) {
                    System.out.println("How many tickets would you like to purchase?");
                    int input = intBoard.nextInt();
                    if (input == 0) {
                        System.out.println("You can't purchase 0 tickets. Please input a more reasonable number.");
                    } else if (input > flights.get(i).seats()) {
                        System.out.println("The plane only has " + flights.get(i).seats() + " seats. Please input a more reasonable number.");
                    } else {
                        purchase = input;
                        invalid = false;
                        break;
                    }
                }

                System.out.println("=========================================================");
                for (int j = 0; j < purchase; j++) {
                    tickets.add(new Ticket(flights.get(i).flightNumber() + ":" + String.format("%03d", j + flights.get(i).quantity()), flights.get(i).name(), flights.get(i).price(), flights.get(i).date(), flights.get(i).time()));
                }
                System.out.println("Invoice: ");
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Flight Number", "Destination", "Date", "Time", "Terminal", "Quantity", "Price");
                System.out.print(flights.get(i).displayInvoice(purchase));
                System.out.println("\nYour ticket numbers are:");
                for (int k = tickets.size() - purchase; k < tickets.size(); k++) {
                    System.out.println(tickets.get(k).ticket_number());
                }
                flights.get(i).less_seats(purchase);
                flights.get(i).setQuantity(flights.get(i).quantity() + purchase);
                System.out.println("\nThank you for your business, " + flights.get(i).name() + "!");
            }
        }
        System.out.println("=======================================================");
    }

    public static void refundTickets() {
        GregorianCalendar gcal = new GregorianCalendar();
        boolean ticket_found = false;
        System.out.println("Please enter a valid ticket number:");
        String refund = intBoard.next();
        for (int i = 0; i < tickets.size(); i++) {
            if (refund.equals(tickets.get(i).ticket_number())) {
                ticket_found = true;
                if (gcal.get(gcal.HOUR_OF_DAY) + 12 < Integer.parseInt(flights.get(i).time().substring(0, 2))) {
                    System.out.println("Your refund has been approved in the amount of " + tickets.get(i).price() + ". Have a nice day " + tickets.get(i).name());
                    tickets.get(i).refund();
                } else if (gcal.get(gcal.HOUR_OF_DAY) + 12 == Integer.parseInt(flights.get(i).time().substring(0, 2))) {
                    if (gcal.get(gcal.MINUTE) <= Integer.parseInt(flights.get(i).time().substring(3))) {
                        System.out.println("Your refund has been approved in the amount of " + tickets.get(i).price() + ". Have a nice day " + tickets.get(i).name());
                        flights.get(i).add_seats();
                        tickets.get(i).refund();
                    } else {
                        System.out.println("Your flight is departing in less than 12 hours we are unable to process your refund");
                    }
                } else {
                    System.out.println("Your flight is departing in less than 12 hours we are unable to process your refund");
                }
            }

        }
        if (!ticket_found) {
            System.out.println("Sorry! Invalid ticket number.");
        }
    }

    public static void logoff() {
        GregorianCalendar gcal = new GregorianCalendar();
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] dayName = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String month = monthName[gcal.get(GregorianCalendar.MONTH)];
        String day = dayName[gcal.get(GregorianCalendar.DAY_OF_WEEK)];

        System.out.println("\nSummary for " + day + ", " + month + " "
                + gcal.get(GregorianCalendar.DATE) + ", " + gcal.get(GregorianCalendar.YEAR) + ":\n");

        System.out.println("Purchases: \n");
        System.out.printf("%-20s%-20s%-20s\n", "Flight Number", "Ticket Number", "Price");
        double sum = 0;
        double refund = 0;
        for (int i = 0; i < tickets.size(); i++) {
            System.out.print(tickets.get(i).display());
            sum += tickets.get(i).price();
        }
        System.out.println("===============================");
        System.out.printf("%-40s%-30s\n", "Total Sales:", "$" + (double) Math.round(sum * 100) / 100);
        System.out.println();
        System.out.println("Refunds");
        System.out.println();
        System.out.printf("%-20s%-20s%-20s\n", "Flight Number", "Ticket Number", "Price");
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).valid()) {
                System.out.print(tickets.get(i).display());
                refund += tickets.get(i).price();
            }
        }
        System.out.println("===============================");
        System.out.printf("%-40s%-30s\n", "Total Refunds:", "$" + (double) Math.round(refund * 100) / 100);
        System.out.println("********************************************");
        System.out.printf("%-40s%-30s\n", "Profit", "$" + (double) Math.round(sum * 100 - refund * 100) / 100);
        System.exit(0);
    }

}
