package com.platform.marketplace.Marketplace.Platform.utility.scheduler;

import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor

public class OrganisationAccountScheduler {

    private final OrganisationService organisationService;
    @Scheduled(fixedRate = 86400000) //schedules it every day
    public void deleteOrganisationAccountsWithInactiveStatusMoreThanSixMonths(){
        LocalDateTime now = LocalDateTime.now();
        organisationService.deleteOrganisationAccountsThatAreInactiveMoreThanSixMonths(now);
    }
}
