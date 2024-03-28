package com.d205.foorrng.festival;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Getter
@Validated
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "festival_seq")
    private Long id;
    private String country;
    private String period;
    private String festivalName;
    private String location;
    private String organization;
    private String agency;
    private String contact_number;

}
