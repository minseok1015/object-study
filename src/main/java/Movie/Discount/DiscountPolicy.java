package Movie.Discount;

import Movie.Money;
import Movie.Screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface DiscountPolicy {


    public Money calculateDiscountAmount(Screening screening);



}
