package Movie;

import java.time.Duration;

public class PercentDiscountMovie extends Movie {
    private final double discountPercent; // 할인율 필드

    public PercentDiscountMovie(String title, Duration runningTime, Money fee,double discountPercent) {
        super(title, runningTime, fee);
        this.discountPercent = discountPercent;
    }

    @Override
    public Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(discountPercent);
    }
}
