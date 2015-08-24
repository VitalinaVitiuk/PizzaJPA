package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class AccumulativeCard {
    @Column(name = "card_balance")
    private Double balance;
    public AccumulativeCard() {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double totalSum) {
        this.balance = totalSum;
    }

}
