package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.*;
import com.salesconnect.backend.entity.*;

public class ContactTransformer extends Transformer<Contact, ContactDTO>{
    @Override
    public Contact toEntity(ContactDTO contactDTO) {
        if (contactDTO==null)
            return null;
        else{
            Transformer<Company, CompanyDTO> companyTransformer = new CompanyTransformer();
            Transformer<Activity, ActivityDTO> activityTransformer = new ActivityTransformer();
            Transformer<Opportunity, OpportunityDTO> opportunityTransformer = new OpportunityTransformer();
            Contact contact = new Contact();
            contact.setContactId(contactDTO.getContactId());
            contact.setName(contactDTO.getName());
            contact.setEmail(contactDTO.getEmail());
            contact.setAddress(contactDTO.getAddress());
            contact.setPhone(contactDTO.getPhone());
            contact.setCompany(companyTransformer.toEntity(contactDTO.getCompanyDTO()));
            contact.setActivities(activityTransformer.toEntityList(contactDTO.getActivitiesDTO()));
            contact.setOpportunities(opportunityTransformer.toEntityList(contactDTO.getOpportunitiesDTO()));

            return contact;
        }
    }

    @Override
    public ContactDTO toDTO(Contact contact) {
        if (contact==null)
            return null;
        else{
            Transformer<Company, CompanyDTO> companyTransformer = new CompanyTransformer();
            Transformer<Activity, ActivityDTO> activityTransformer = new ActivityTransformer();
            Transformer<Opportunity, OpportunityDTO> opportunityTransformer = new OpportunityTransformer();
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setContactId(contact.getContactId());
            contactDTO.setName(contact.getName());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setAddress(contact.getAddress());
            contactDTO.setPhone(contact.getPhone());
            contactDTO.setCompanyDTO(companyTransformer.toDTO(contact.getCompany()));
            contactDTO.setActivitiesDTO(activityTransformer.toDTOList(contact.getActivities()));
            contactDTO.setOpportunitiesDTO(opportunityTransformer.toDTOList(contact.getOpportunities()));

            return contactDTO;
        }
    }
}
