package com.example.backendspringcode.config;

import com.example.backendspringcode.model.FeatureTag;
import com.example.backendspringcode.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagSeeder {
    @Autowired
    private TagService tagService;

    /* We initialize an empty array of tags
    * We create a new instance of Feature Tag Entity and set its name and then add it to the array.
    * These tags are then bulk added with the service method */
    public List<FeatureTag> seedTags() {
        List<FeatureTag> tagsToAdd = new ArrayList<>();

        FeatureTag lowSensoryTag = new FeatureTag();
        lowSensoryTag.setTagName("low-sensory");
        tagsToAdd.add(lowSensoryTag);

        FeatureTag hearingLoopTag = new FeatureTag();
        hearingLoopTag.setTagName("hearing-loop");
        tagsToAdd.add(hearingLoopTag);

        FeatureTag brailleTactileTag = new FeatureTag();
        brailleTactileTag.setTagName("braille-tactile");
        tagsToAdd.add(brailleTactileTag);

        FeatureTag wheelchairAccessibleTag = new FeatureTag();
        wheelchairAccessibleTag.setTagName("wheelchair-accessible");
        tagsToAdd.add(wheelchairAccessibleTag);

        FeatureTag serviceAnimalTag = new FeatureTag();
        serviceAnimalTag.setTagName("service-animal");
        tagsToAdd.add(serviceAnimalTag);

        return tagService.addTags(tagsToAdd);
    }

}

