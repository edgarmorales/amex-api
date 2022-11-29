package com.amex.api.service;

import com.amex.api.data.Associate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class AssociateServiceTests {

    @Autowired
    private AssociateService associateService;

    @Test
    @DisplayName("Create an Associate")
    public void test_a_givenAssociateService_whenAssociateIsCreated_thenFetchAndMatchFields() {
        Long associateId = associateService.saveAssociate(
                new Associate(
                        "test-a-associate@test.com",
                        "12345",
                        "Tester",
                        "McTester",
                        "123-456-7890"))
                .getId();

        Associate associate = associateService.fetchAssociateById(associateId).get();

        Assertions.assertThat(associate.getEmail()).isEqualTo("test-a-associate@test.com");
        Assertions.assertThat(associate.getStoreId()).isEqualTo("12345");
        Assertions.assertThat(associate.getFirstName()).isEqualTo("Tester");
        Assertions.assertThat(associate.getLastName()).isEqualTo("McTester");
        Assertions.assertThat(associate.getCellphone()).isEqualTo("123-456-7890");
    }

    @Test
    @DisplayName("Update an Associate")
    public void test_b_givenAssociateService_whenAssociateIsUpdated_thenFetchAndMatchFields() {
        Long associateId = associateService.saveAssociate(
                new Associate(
                        "test-b-associate@test.com",
                        "12345",
                        "TesterB",
                        "McTester",
                        "123-456-7890"))
                .getId();

        Associate associate = associateService.fetchAssociateById(associateId).get();
        associate.setEmail(associate.getEmail() + "-UPDATED");
        associate.setFirstName(associate.getFirstName() + "-UPDATED");
        associate.setLastName(associate.getLastName() + "-UPDATED");
        associate.setCellphone(associate.getCellphone() + "-UPDATED");

        associateService.updateAssociate(associate, associateId);

        Associate updatedAssociate = associateService.fetchAssociateById(associateId).get();
        Assertions.assertThat(updatedAssociate.getFirstName()).isEqualTo(associate.getFirstName());
        Assertions.assertThat(updatedAssociate.getEmail()).isEqualTo(associate.getEmail());
        Assertions.assertThat(updatedAssociate.getLastName()).isEqualTo(associate.getLastName());
        Assertions.assertThat(updatedAssociate.getCellphone()).isEqualTo(associate.getCellphone());
    }

    @Test
    @DisplayName("Delete an Associate")
    public void test_c_givenAssociateService_whenAssociateIsDeleted_thenConfirmAssociateNotExists() {
        Long associateId = associateService.saveAssociate(
                new Associate(
                        "test-c-associate@test.com",
                        "12345",
                        "TesterC",
                        "McTester",
                        "123-456-7890"))
                .getId();

        associateService.deleteAssociateById(associateId);

        Optional<Associate> possibleAssociate = associateService.fetchAssociateById(associateId);
        Assertions.assertThat(possibleAssociate.isPresent()).isFalse();
    }
}
