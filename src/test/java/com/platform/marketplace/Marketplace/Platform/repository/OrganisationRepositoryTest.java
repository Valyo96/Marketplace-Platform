package com.platform.marketplace.Marketplace.Platform.repository;

import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrganisationRepositoryTest {
    @Autowired
    private OrganisationRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void itShouldCheckifFindOrganisationsByUserId() {
        //given

        //when
        //then
    }

    @Test
    void findOrganisationByUserId() {
    }

    @Test
    void findOrganisationsByRegistrationDateDesc() {
    }

    @Test
    void findOrganisationsByRegistrationDateAsc() {
    }
}