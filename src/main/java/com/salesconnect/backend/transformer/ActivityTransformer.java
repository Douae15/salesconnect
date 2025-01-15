package com.salesconnect.backend.transformer;

import com.salesconnect.backend.dto.ActivityDTO;
import com.salesconnect.backend.dto.ContactDTO;
import com.salesconnect.backend.dto.UserDTO;
import com.salesconnect.backend.entity.Activity;
import com.salesconnect.backend.entity.Contact;
import com.salesconnect.backend.entity.User;

public class ActivityTransformer extends Transformer<Activity, ActivityDTO>{
    @Override
    public Activity toEntity(ActivityDTO activityDTO) {
        if (activityDTO==null)
            return null;
        else{
            Transformer<User, UserDTO> userTransformer = new UserTransformer();
            Transformer<Contact, ContactDTO> contactTransformer = new ContactTransformer();
            Activity activity = new Activity();
            activity.setActivityId(activityDTO.getActivityId());
            activity.setDate(activityDTO.getDate());
            activity.setUser(userTransformer.toEntity(activityDTO.getUserDTO()));
            activity.setType(activityDTO.getType());
            activity.setSummary(activityDTO.getSummary());
            activity.setContact(contactTransformer.toEntity(activityDTO.getContactDTO()));
            return activity;
        }

    }

    @Override
    public ActivityDTO toDTO(Activity activity) {
        if (activity==null)
            return null;
        else{
            Transformer<User, UserDTO> userTransformer = new UserTransformer();
            Transformer<Contact, ContactDTO> contactTransformer = new ContactTransformer();
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setActivityId(activity.getActivityId());
            activityDTO.setDate(activity.getDate());
            activityDTO.setUserDTO(userTransformer.toDTO(activity.getUser()));
            activityDTO.setType(activity.getType());
            activityDTO.setSummary(activity.getSummary());
            activityDTO.setContactDTO(contactTransformer.toDTO(activity.getContact()));
            return activityDTO;
        }
    }
}
