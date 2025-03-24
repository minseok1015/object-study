package Movie;

import java.time.Duration;

public class AmountDiscountMovie extends Movie {
    private final Money discountAmount;
    public AmountDiscountMovie(String title, Duration runningTime, Money fee, Money discountAmount) {
        super(title, runningTime, fee);
        this.discountAmount = discountAmount;
    }

    @Override
    public Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().minus(discountAmount);
    }


}
