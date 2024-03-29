package com.d205.foorrng.bigdata;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Setter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bigdata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bigdata_seq")
    private Long id;

    private String city; // 시

    private String distract; // 군, 구

    private String town; // 동
    
}
