package com.d205.foorrng.festival;

import java.util.List;

public interface FestivalRepository {
    List<Festival> findFutureFestivals();
}
