package com.dlubera.grave.manager.service.visit.planner;


import com.dlubera.grave.manager.service.domain.core.relative.RelativeService;
import com.dlubera.grave.manager.service.domain.core.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVisitService {

    private final UserVisitRepository userVisitRepository;
    private final RelativeService relativeService;
    private final UserService userService;

    UserVisitDto createNewRelativeVisit(UserVisitRequest request) {
        var userName = userService.getUser(request.getAuthentication().getName());
        var userVisitId = new UserVisitId(userName.getId(), request.getRelativeId());
        var userVisitEntity = new UserVisitEntity(userVisitId, request.getLocalDate(), null);
        var savedVisit = userVisitRepository.save(userVisitEntity);
        return createUserVisitResponse(savedVisit);
    }

    private UserVisitDto createUserVisitResponse(UserVisitEntity visitEntity) {
        var relative = relativeService.getRelativeById(visitEntity.getUserVisitId().getDeadId());
        var visitDate = visitEntity.getVisitDate();
        return new UserVisitDto(relative, visitDate);
    }

    public List<UserVisitDto> getAllUserVisits(Authentication authentication) {
        var userName = userService.getUser(authentication.getName());
        var allUserVisits = userVisitRepository.findAllByUserVisitIdUserId(userName.getId());
        return allUserVisits.stream()
                .map(x -> new UserVisitDto(relativeService.getRelativeById(x.getUserVisitId().getDeadId()), x.getVisitDate()))
                .toList();
    }
}
