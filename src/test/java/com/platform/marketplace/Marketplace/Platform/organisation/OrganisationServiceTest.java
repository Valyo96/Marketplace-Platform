package com.platform.marketplace.Marketplace.Platform.organisation;

import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.service.organisation.OrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrganisationServiceTest {
    @Mock
    private OrganisationRepository organisationRepository;
    private OrganisationService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
