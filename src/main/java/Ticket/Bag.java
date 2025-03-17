package Ticket;

public class Bag {
    private Long amount;
    private Ticket ticket;
    private Invitation invitation;

    public boolean hasInvitation(){
        return invitation != null;
    }
    public boolean hasTicket(){
        return ticket != null;
    }

    public void setTicket(Ticket ticket){
        this.ticket = ticket;
    }

    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }

    public Bag(long amount){
        this(null, amount);
    }
    public Bag(Invitation invitation, long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }
}
