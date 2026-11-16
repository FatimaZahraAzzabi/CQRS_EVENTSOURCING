package net.fz.demoescqrsaxon.events;


import lombok.AllArgsConstructor;
import lombok.Getter;
import net.fz.demoescqrsaxon.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountActivatedEvent {
    private String accountId;
    private AccountStatus status;
}
