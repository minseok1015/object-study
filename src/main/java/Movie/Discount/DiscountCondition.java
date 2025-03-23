package Movie.Discount;

import Movie.Screening;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
