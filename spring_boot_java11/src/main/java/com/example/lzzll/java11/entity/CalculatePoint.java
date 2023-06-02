package com.example.lzzll.java11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lf
 * @Date 2023/6/2 10:34
 * @Description: 计算评分
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatePoint {

    // 平均分
    private Double averagePoint;
    // 回复列表
    private List<Review> reviews = new ArrayList<>();

    public void add(Review review){
        reviews.add(review);
        countAveragePoint();
    }

    /**
     * 计算评论的平均分
     */
    private void countAveragePoint() {
        Double totalResultPoint = reviews.stream().reduce(0d, (totalPoint, review) -> totalPoint + review.getPoint(), Double::sum);
        this.averagePoint = totalResultPoint/reviews.size();
    }

    public static CalculatePoint average(CalculatePoint r1, CalculatePoint r2) {
        CalculatePoint combined = new CalculatePoint();
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.countAveragePoint();
        return combined;
    }
}
