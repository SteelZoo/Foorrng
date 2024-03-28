package com.d205.foorrng.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewResDto {
    private Boolean rvIsDelicious;
    private Boolean isCool;
    private Boolean isClean;
    private Boolean isKind;
    private Boolean rvIdSpecial;
    private Boolean rvIsCheap;
    private Boolean rvIsFast;
    private Long createdDate;
    private String username;
    private Long foodtrucks;

    public static ReviewResDto fromEntity (Review review){
        return new ReviewResDto(
                review.getRvIsDelicious(),
                review.getRvIsCool(),
                review.getRvIsClean(),
                review.getRvIsKind(),
                review.getRvIsSpecial(),
                review.getRvIsCheap(),
                review.getRvIsFast(),
                review.getCreatedDate(),
                review.getUser().getName(),
                review.getFoodtrucks().getId()
        );
    }
}
