package org.beru.dreammer.persistence.audit;

import org.beru.dreammer.persistence.entity.UserEntity;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class AuditUserListener {
    private UserEntity user;

    @PostLoad
    public void postLoad(UserEntity entity){
        this.user = entity;
    }

    @PostPersist
    public void onPostPersist(UserEntity entity){
        System.out.println("Post persist");
        System.out.println("New value: " + entity);
    }

    @PostUpdate
    public void onPostUpdate(UserEntity entity){
        System.out.println("Post update");
        System.out.println("Old value: " + this.user);
        System.out.println("New value: " + entity);
    }
    @PreRemove
    public void onPreDelete(UserEntity entity){
        System.out.println("Pre remove");
        System.out.println("Old value: " + entity);
    }
    
}
