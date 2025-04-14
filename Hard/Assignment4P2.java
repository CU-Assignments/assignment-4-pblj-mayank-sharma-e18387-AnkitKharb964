class TicketBookingSystem {
    private int availableSeats = 10;

    public synchronized void bookTicket(String customerType) {
        if (availableSeats > 0) {
            System.out.println(customerType + " booking in progress...");
            try {
                Thread.sleep(1000); // Simulate time taken for booking
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            availableSeats--;
            System.out.println(customerType + " booked successfully. Remaining seats: " + availableSeats);
        } else {
            System.out.println("Sorry, no seats available.");
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private String customerType;

    public BookingThread(TicketBookingSystem system, String customerType) {
        this.system = system;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        system.bookTicket(customerType);
    }
}

public class Assignment4P2 {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        // Create threads for regular and VIP customers
        BookingThread regularCustomer1 = new BookingThread(system, "Regular Customer 1");
        BookingThread regularCustomer2 = new BookingThread(system, "Regular Customer 2");
        BookingThread vipCustomer1 = new BookingThread(system, "VIP Customer 1");
        BookingThread vipCustomer2 = new BookingThread(system, "VIP Customer 2");

        // Set thread priorities
        regularCustomer1.setPriority(Thread.NORM_PRIORITY);
        regularCustomer2.setPriority(Thread.NORM_PRIORITY);
        vipCustomer1.setPriority(Thread.MAX_PRIORITY); // VIP customer with high priority
        vipCustomer2.setPriority(Thread.MAX_PRIORITY); // VIP customer with high priority

        // Start all booking threads
        vipCustomer1.start();
        vipCustomer2.start();
        regularCustomer1.start();
        regularCustomer2.start();
    }
}
