package net.fz.demoescqrsaxon.query.entites;

import jakarta.persistence.*;
import lombok.*;
import net.fz.demoescqrsaxon.enums.OperationType;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        private Instant date;

    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    private String currency;

    @ManyToOne
    private Account account;
}