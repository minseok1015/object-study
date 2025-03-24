package Movie;

import java.time.Duration;

public abstract class Movie {

    private String title;
    private Duration runningTime;
    private Money fee;

    public Movie(String title, Duration runningTime, Money fee) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
    }

    public Money getFee() {
        return fee;
    }

    public Money calculateMovieFee(Screening screening) {
        return fee.minus(getDiscountAmount(screening));
    }

    public abstract Money getDiscountAmount(Screening screening);


}

