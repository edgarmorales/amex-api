package com.amex.api.service;

import com.amex.api.data.Associate;
import com.amex.api.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociateServiceImpl implements AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Override
    public Associate saveAssociate(Associate associate) {
        return associateRepository.save(associate);
    }

    @Override
    public Associate updateAssociate(Associate otherAssociate, Long id) {
        Associate associate = associateRepository.findById(id).get();
        if (!otherAssociate.getFirstName().isEmpty()) {
            associate.setFirstName(otherAssociate.getFirstName());
        }
        if (!otherAssociate.getLastName().isEmpty()) {
            associate.setLastName(otherAssociate.getLastName());
        }
        if (!otherAssociate.getStoreId().isEmpty()) {
            associate.setStoreId(otherAssociate.getStoreId());
        }
        if (!otherAssociate.getEmail().isEmpty()) {
            associate.setEmail(otherAssociate.getEmail());
        }
        if (!otherAssociate.getCellphone().isEmpty()) {
            associate.setCellphone(otherAssociate.getCellphone());
        }
        return associateRepository.save(associate);
    }

    @Override
    public Optional<Associate> fetchAssociateById(Long associateId) {
        return associateRepository.findById(associateId);
    }

    @Override
    public List<Associate> fetchAssociates() {
        return (List<Associate>) associateRepository.findAll();
    }

    @Override
    public void deleteAssociateById(Long associateId) {
        associateRepository.deleteById(associateId);
    }
}
