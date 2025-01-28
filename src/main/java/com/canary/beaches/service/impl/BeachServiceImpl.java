package com.canary.beaches.service.impl;

import com.canary.beaches.model.Beach;
import com.canary.beaches.repository.BeachRepository;
import com.canary.beaches.service.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BeachServiceImpl implements BeachService {

    private final BeachRepository beachRepository;

    @Autowired
    public BeachServiceImpl(BeachRepository beachRepository) {
        this.beachRepository = beachRepository;
    }

    @Override
    public List<Beach> findAll() {
        return beachRepository.findAll();
    }

    @Override
    public Optional<Beach> findById(String id) {
        return beachRepository.findById(id);
    }

    @Override
    public Beach save(Beach beach) {
        return beachRepository.save(beach);
    }

    @Override
    public void deleteById(String id) {
        beachRepository.deleteById(id);
    }
}