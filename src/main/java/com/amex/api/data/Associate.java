package com.amex.api.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity(name = "associates")
@Getter
@Setter
@NoArgsConstructor
public class Associate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty @Email
    private String email;

    @NotEmpty
    private String storeId;

    private String firstName;

    private String lastName;

    @NotEmpty
    private String cellphone;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant hireDate;

    public Associate(String email, String storeId, String firstName, String lastName, String cellphone) {
        this.email = email;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellphone = cellphone;
    }
}
