package net.fz.demoescqrsaxon.query.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import net.fz.demoescqrsaxon.enums.AccountStatus;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    private String id;

    private double balance;

        private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private String currency;
}