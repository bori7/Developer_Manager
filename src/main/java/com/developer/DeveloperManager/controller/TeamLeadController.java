package com.developer.DeveloperManager.controller;


import com.developer.DeveloperManager.dto.TeamLeadRequest;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.service.implementation.TeamLeadServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/teamLead")
public class TeamLeadController {
  @Autowired
    private final TeamLeadServiceImpl teamLeadService;


    @PostMapping("/add")
    public ResponseEntity<Response> saveTeamLead(@RequestBody TeamLeadRequest teamLeadRequest) throws ParseException {
        return  ResponseEntity.ok().body(teamLeadService.saveTeamLead(teamLeadRequest ));

    }
    @PostMapping("/update")
    public ResponseEntity<Response> updateTeamLead(@RequestBody TeamLeadRequest teamLeadRequest) throws ParseException {
        return  ResponseEntity.ok().body(teamLeadService.updateTeamLead(teamLeadRequest ));
    }
    @GetMapping("/getAllTeamLead")
    public ResponseEntity<Response> getAllTeamLead() throws ParseException {
        return  ResponseEntity.ok().body(teamLeadService.getAllTeamLead());
    }
    @GetMapping("/getTeamLeadById/{Id}")
    public ResponseEntity<Response> getTeamLeadById(@PathVariable(name="Id") Long lead_Id) throws ParseException {
        return  ResponseEntity.ok().body(teamLeadService.getTeamLeadById(lead_Id));
    }
    @GetMapping("/getTeamLeadByDept/{Id}")
    public ResponseEntity<Response> getTeamLeadByDept(@PathVariable(name="Id") Long dept_Id) throws ParseException {
        return  ResponseEntity.ok().body(teamLeadService.getTeamLeadByDeptId(dept_Id));
    }
}
