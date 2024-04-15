package at.htlstp.dwh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "dim_product")
public class Product {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Float price;
    @NotNull
    private String category;


}
