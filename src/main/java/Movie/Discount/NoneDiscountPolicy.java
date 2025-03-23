package Movie.Discount;

import Movie.Money;
import Movie.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {


    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
