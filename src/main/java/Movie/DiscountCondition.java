package Movie;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
