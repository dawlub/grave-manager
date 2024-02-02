package com.dlubera.grave.manager.service.visit.planner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController()
@RequestMapping(value = "v1/visit")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000", methods = RequestMethod.POST)
class VisitController {


}
