package com.developer.DeveloperManager.service.implementation;

import com.developer.DeveloperManager.dto.TeamLeadRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.model.TeamLead;
import com.developer.DeveloperManager.repository.DepartmentRepo;
import com.developer.DeveloperManager.repository.DeveloperRepo;
import com.developer.DeveloperManager.repository.TeamLeadRepo;
import com.developer.DeveloperManager.service.DepartmentService;
import com.developer.DeveloperManager.service.TeamLeadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamLeadServiceImpl implements TeamLeadService {

    Response resp = new Response();
    private final ModelMapper modelMapper;
    private final TeamLeadRepo teamLeadRepo;
    private final DeveloperRepo developerRepo;
    private final DepartmentRepo departmentRepo;
    private final DepartmentService departmentService;

    @Override
    public Response saveTeamLead(TeamLeadRequest teamLeadRequest) {
        Department department = departmentRepo.findById(teamLeadRequest.getDepartment().getDept_Id()).orElseThrow(() -> new RuntimeException("Department not found"));
        Developer developer = developerRepo.findById(teamLeadRequest.getDeveloper().getDev_Id()).orElseThrow(() -> new RuntimeException("Developer doesn't exist"));

        try {
            teamLeadRequest.setDateCreated(LocalDateTime.now());
            teamLeadRequest.setDepartment(department);
            teamLeadRequest.setDeveloper(developer);
            TeamLead teamLead = modelMapper.map(teamLeadRequest, TeamLead.class);
            teamLeadRepo.save(teamLead);
            resp.setResp_Code("00");
            resp.setResp_Msg("Record Successfully added");
            resp.setData(teamLead.getLead_Id());


        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully added");
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response updateTeamLead(TeamLeadRequest teamLeadRequest) {
        try {
            Optional<TeamLead> teamLeadOptional = teamLeadRepo.findById(teamLeadRequest.getLead_Id());
            if (teamLeadOptional.isPresent()) {
                teamLeadRequest.setDateModified(LocalDateTime.now());
                TeamLead teamLead = modelMapper.map(teamLeadRequest, TeamLead.class);
                //log.info(" Department "+ developerRequest.getDeptName());
                teamLeadRepo.save(teamLead);
                resp.setResp_Code("00");
                resp.setResp_Msg("Record Successfully Modified");
                resp.setData(teamLead.getLead_Id());
            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully Modified");
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getAllTeamLead() {
        try {
            List<TeamLead> teamLeadList = teamLeadRepo.findAll();
            if (teamLeadList.isEmpty()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("List Empty");

            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(teamLeadList);

            }

        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;
    }



    @Override
    public Response getTeamLeadByDeptId(Long dept_Id) {
        try {
            Department department = new Department();
            Response response = departmentService.getById(dept_Id);
            department = (Department) response.getData();
            TeamLead teamLead = department.getTeamLead();
            if (teamLead==null) {
                resp.setResp_Code("84");
                resp.setResp_Msg("No teamLead found for this department");
            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(teamLead.getDeveloper());
            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);

        }
        return resp;

    }

    @Override
    public Response getTeamLeadById(Long lead_Id) {
        try {

           Optional<TeamLead> teamLead = teamLeadRepo.findById(lead_Id);
            if (teamLead.isPresent()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("TeamLead doesn't exist");
            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(teamLead.get());
            }

        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;
    }


}

