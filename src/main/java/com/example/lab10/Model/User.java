package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role='JOB_SEEKER' or role='EMPLOYER'")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name is requirement")
    @Size(min =4, message = "Length must be more than 4 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$" ,message = "Must contain only characters (no numbers). ")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;
    @Email(message = "Must be a valid email format.")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;
    @Column(columnDefinition = "varchar(25) not null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?!.* ).{8,16}+$" ,message = "Minimum eight characters, at least one letter, one number and one special character")
    private String password;
    @Positive(message = "Enter valid number")
    @Min(value = 21,message = "Must be more than 21.")
    @Column(columnDefinition = "int not null check(age>=21)")
    private int age;
    @Pattern(regexp = "(JOB_SEEKER|EMPLOYER)+$",message = "Must be either \"JOB_SEEKER\" or \"EMPLOYER\" only. ")
    @NotNull(message = "role Cannot be null")
    @Column(name = "role",columnDefinition = "varchar(25) not null ")
    private String role;


}
