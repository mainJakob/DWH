package at.htlstp.dwh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "dim_time")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @NotNull
    private Integer date_day;
    @NotNull
    private Integer date_month;
    @NotNull
    private Integer date_year;
    @NotNull
    private LocalDate date_time;


}
