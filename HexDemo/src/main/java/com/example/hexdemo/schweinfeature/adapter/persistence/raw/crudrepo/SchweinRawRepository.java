package com.example.hexdemo.schweinfeature.adapter.persistence.raw.crudrepo;

import com.example.hexdemo.schweinfeature.adapter.persistence.raw.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

public interface SchweinRawRepository extends CrudRepository<SchweinEntity, String> {
}
