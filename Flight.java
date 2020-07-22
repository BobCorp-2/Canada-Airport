package canada.airport;

public class Flight {

    private String status;
    private String airline;
    private String flightNumber;
    private String destination;
    private String date;
    private String time;
    private int terminal;
    private String plane;
    private double price;
    private String line;
    private int seats;
    private int choice;
    private String name;
    private int quantity;

    public Flight(String line) {
        String[] parts = line.split(",");
        try {
            this.status = parts[0].trim();
            this.airline = parts[1].trim();
            this.flightNumber = parts[2].trim();
            this.destination = parts[3].trim();
            this.date = parts[4].trim();
            this.time = parts[5].trim();
            this.terminal = Integer.parseInt(parts[6].trim());
            switch (parts[7].trim()) {
                case "B747":
                    this.seats = 400;
                    this.plane = parts[7].trim();
                    break;
                case "B787":
                    this.seats = 300;
                    this.plane = parts[7].trim();
                    break;
                case "A310":
                    this.seats = 250;
                    this.plane = parts[7].trim();
                    break;
                default:
                    System.out.println("Invalid plane type for flight: " + line);
                    System.exit(0);
            }
            if(status.equals("DEP")){
                this.price = Double.parseDouble(parts[8].substring(1).trim());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds");
        }

    }

    public String display() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s\n", airline, flightNumber, destination, date, time, terminal);
    }

    public String displayAirCan() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s\n", status, flightNumber, destination, date, time, terminal);
    }

    public String displayTickets() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", flightNumber, destination, date, time, terminal, seats, "$" + price);
    }

    public String displayInvoice(int amount) {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", flightNumber, destination, date, time, terminal, amount, "$" + (double) Math.round(amount * price * 100) / 100);
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int choice() {
        return choice;
    }

    public String name() {
        return name;
    }

    public int quantity() {
        return quantity;
    }

    public String status() {
        return status;
    }

    public String airline() {
        return airline;
    }

    public String flightNumber() {
        return flightNumber;
    }

    public String destination() {
        return destination;
    }

    public String date() {
        return date;
    }

    public String time() {
        return time;
    }

    public int terminal() {
        return terminal;
    }

    public String plane() {
        return plane;
    }

    public double price() {
        return price;
    }

    public int seats() {
        return seats;
    }
    
    public void less_seats(int less){
       seats -= less;
    }
    
    public void add_seats(){
        seats ++;
    }

}



