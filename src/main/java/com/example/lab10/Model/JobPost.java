package com.example.lab10.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Check(constraints = "salary>0")
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "title can not be null")
    @Column(columnDefinition = "varchar(55) not null")
    private String title;
    @NotNull(message = "Cannot be null.")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;
    @Column(columnDefinition = "varchar(55) not null")
    private String location;
    @Positive(message = "Must be a non-negative number. ")
    @NotNull(message = "Cannot be null.")
    @Column(name = "salary", columnDefinition = "int not null ")
    private int salary;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "datetime default(CURRENT_TIMESTAMP)")
    private LocalDate postingDate;
}
