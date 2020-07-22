package canada.airport;

public class Ticket {

    private String ticketNumber;
    private String name;
    private double price;
    private String date;
    private String time;
    private boolean refund = false;

    public Ticket(String ticketNumber, String name, double price, String date, String time) {
        this.ticketNumber = ticketNumber;
        this.name = name;
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public String ticket_number() {
        return ticketNumber;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    public String date() {
        return date;
    }

    public String time() {
        return time;
    }

    public void refund() {
        refund = true;
    }

    public boolean valid() {
        return refund;
    }

    public String display() {
        return String.format("%-20s%-20s%-20s\n", ticketNumber.substring(0, 6), ticketNumber, "$" + price);
    }
}



