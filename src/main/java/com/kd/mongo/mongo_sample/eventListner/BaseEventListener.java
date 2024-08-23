package com.kd.mongo.mongo_sample.eventListner;

import com.kd.mongo.mongo_sample.model.BaseModel;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BaseEventListener extends AbstractMongoEventListener<BaseModel> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseModel> event){
        super.onBeforeConvert(event);
        Date now = new Date();

        event.getSource().setCreatedAt(now);
        event.getSource().setUpdatedAt(now);
    }
}
