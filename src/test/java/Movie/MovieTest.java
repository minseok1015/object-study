package Movie;

import Movie.Discount.PeriodCondition;
import Movie.Discount.SequenceCondition;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void testChangeDiscountPolicyWithInheritance() {
        // given: AmountDiscountMovie 객체 생성
        AmountDiscountMovie amountDiscountMovie = new AmountDiscountMovie(
                "영화 제목",
                Duration.ofMinutes(120),
                new Money(12000), // 기본 요금
                new Money(2000)   // 고정 할인 금액
        );

        // when: 할인 정책을 비율 할인으로 변경해야 함
        double newDiscountPercent = 15.0;

        // 새로운 PercentDiscountMovie 객체 생성 및 상태 복사
        PercentDiscountMovie percentDiscountMovie = new PercentDiscountMovie(
                amountDiscountMovie.getTitle(),       // 기존 제목 복사
                amountDiscountMovie.getRunningTime(), // 기존 상영 시간 복사
                amountDiscountMovie.getFee(),         // 기존 기본 요금 복사
                newDiscountPercent                    // 새로운 할인율 적용
        );

        // then: 새 객체가 올바르게 생성되었는지 검증
        Assertions.assertEquals("영화 제목", percentDiscountMovie.getTitle());
        Assertions.assertEquals(Duration.ofMinutes(120), percentDiscountMovie.getRunningTime());
        Assertions.assertEquals(new Money(12000), percentDiscountMovie.getFee());
        Assertions.assertEquals(new Money(1800), percentDiscountMovie.getDiscountAmount(null)); // 15% 할인 금액 확인

        // 기존 객체는 더 이상 사용할 수 없음 (이 과정은 불편함을 보여줌)
    }

}