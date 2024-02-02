package com.dlubera.grave.manager.service.domain.core.relative;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
class RelativeServiceImpl implements RelativeService{

    private final RelativeRepository relativeRepository;

    Relative saveNewRelative(Relative relative) {
        var entity = RelativeEntity.from(relative);
        //TODO temporary change to avoid nullable constraints from db end
        entity.setGraveId(1L);
        return Relative.from(relativeRepository.save(entity));
    }



    List<Relative> getAllRelatives() {
        return relativeRepository.findAll().stream()
                .map(Relative::from)
                .toList();
    }

    List<Relative> getAllFromGrave(Long graveId) {
        return relativeRepository.findAllByGraveId(graveId).stream()
                .map(Relative::from)
                .toList();
    }

    @Override
    public Relative getRelativeById(Long id) {
        return Relative.from(findByIdOrThrowException(id));
    }

    private RelativeEntity findByIdOrThrowException(Long id) {
        return relativeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Relative with id=%s not found".formatted(id)));
    }
}
