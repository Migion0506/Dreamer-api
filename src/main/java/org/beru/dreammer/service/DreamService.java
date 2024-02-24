package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.DreamEntity;
import org.beru.dreammer.persistence.repository.DreamRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DreamService {
    private final DreamRepository dreamRepository;

    public DreamEntity save(DreamEntity dreamEntity) {
        try{
            return dreamRepository.save(dreamEntity);
        }catch(Exception e){
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
    public List<DreamEntity> findAll(){
        try {
            return dreamRepository.findAll();
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
    public DreamEntity findOne(String id){
        try {
            return dreamRepository.findById(id).orElseThrow(() -> new RestRequestEntityExceptionHandler("The id specified doesn't exists could been deleted or never existed"));
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
    public DreamEntity updateOne(String id, DreamEntity entity){
        try {
            entity.setId(id);
            return dreamRepository.save(entity);
        } catch (Exception e) {
            throw new RestRequestEntityExceptionHandler(e.getLocalizedMessage());
        }
    }
}
