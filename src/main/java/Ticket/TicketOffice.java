package Ticket;

import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets;

    public TicketOffice(Long amount, Ticket... tickets){
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    private Ticket getTicket(){
        return tickets.remove(0);
    }

    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }

    public void sellTicketTo(Audience audience){
        plusAmount(audience.buy(getTicket()));
    }
}
