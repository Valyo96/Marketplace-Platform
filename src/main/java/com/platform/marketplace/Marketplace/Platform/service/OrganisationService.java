package com.platform.marketplace.Marketplace.Platform.service;

import com.platform.marketplace.Marketplace.Platform.dto.OrganisationRegDTO;
import com.platform.marketplace.Marketplace.Platform.exceptions.AlreadyExistException;
import com.platform.marketplace.Marketplace.Platform.mapper.OrganisationRegDTOToOrganisation;
import com.platform.marketplace.Marketplace.Platform.model.Organisation;
import com.platform.marketplace.Marketplace.Platform.repository.OrganisationRepository;
import com.platform.marketplace.Marketplace.Platform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.platform.marketplace.Marketplace.Platform.consts.ExceptionMessages.emailAlreadyTaken;

@Service
@RequiredArgsConstructor
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    private final UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();

    private final OrganisationRegDTOToOrganisation mapper =new OrganisationRegDTOToOrganisation();

    public List<Organisation> getAllOrgs(){
        return organisationRepository.findAll();
    }

    public void registration(OrganisationRegDTO orgDto){
        Organisation org = mapper.apply(orgDto);
       if(getAllOrgs().stream().noneMatch(o-> o.getUser().getUsername().equals(orgDto.getEmail()))){
           String encodedPassword =passwordEncoder.encode(org.getUser().getPassword());
           org.getUser().setPassword(encodedPassword);
           userRepository.save(org.getUser());
           organisationRepository.save(org);
       } else {
           throw new AlreadyExistException(emailAlreadyTaken);
       }
    }
}
