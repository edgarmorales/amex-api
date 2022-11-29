package com.amex.api.service;

import com.amex.api.data.Associate;

import java.util.List;
import java.util.Optional;

public interface AssociateService {

    Associate saveAssociate(Associate associate);

    Associate updateAssociate(Associate associate, Long id);

    Optional<Associate> fetchAssociateById(Long associateId);

    List<Associate> fetchAssociates();

    void deleteAssociateById(Long associateId);
}