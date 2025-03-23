package Movie;

import Movie.Discount.AmountDiscountPolicy;
import Movie.Discount.PercentDiscountPolicy;
import Movie.Discount.PeriodCondition;
import Movie.Discount.SequenceCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MovieTest {

    private Movie amountDiscountMovie;
    private Movie percentDiscountMovie;

    @BeforeEach
    void setUp() {
        // 금액 할인 정책 적용 (800원 할인)
        amountDiscountMovie = new Movie(
                "아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(
                        Money.wons(800),
                        new SequenceCondition(1),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59))
                )
        );

        // 비율 할인 정책 적용 (10% 할인)
        percentDiscountMovie = new Movie(
                "인셉션",
                Duration.ofMinutes(150),
                Money.wons(12000),
                new PercentDiscountPolicy(
                        0.1,
                        new SequenceCondition(2),
                        new PeriodCondition(DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(16, 59))
                )
        );
    }

    @Test
    void 금액_할인_적용_확인() {
        Screening screening = new Screening(amountDiscountMovie, 1, LocalDateTime.of(2025, 3, 24, 10, 30));
        Money discountedPrice = amountDiscountMovie.calculateMovieFee(screening);

        assertEquals(Money.wons(9200).getAmount(), discountedPrice.getAmount());
    }

    @Test
    void 비율_할인_적용_확인() {
        Screening screening = new Screening(percentDiscountMovie, 2, LocalDateTime.of(2025, 3, 26, 15, 0));
        Money discountedPrice = percentDiscountMovie.calculateMovieFee(screening);

        assertEquals(0, Money.wons(10800).getAmount().compareTo(discountedPrice.getAmount()));
    }

}