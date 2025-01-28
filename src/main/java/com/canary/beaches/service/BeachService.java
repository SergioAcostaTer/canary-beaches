package com.canary.beaches.service;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.model.Beach;
import java.util.List;
import java.util.Optional;

public interface BeachService {
    List<Beach> findAll();
    Optional<BeachDto> findById(Long id);
    Beach save(Beach beach);
    void deleteById(String id);
}