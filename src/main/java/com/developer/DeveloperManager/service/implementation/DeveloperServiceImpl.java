package com.developer.DeveloperManager.service.implementation;

import com.developer.DeveloperManager.dto.DeveloperRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.repository.DepartmentRepo;
import com.developer.DeveloperManager.repository.DeveloperRepo;
import com.developer.DeveloperManager.service.DepartmentService;
import com.developer.DeveloperManager.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepo developerRepo;

    private final DepartmentServiceImpl departmentService;

    private final DepartmentRepo departmentRepo;

    private final ModelMapper modelMapper;


    Response resp = new Response();

    @Override
    public Response saveDeveloper(DeveloperRequest developerRequest) throws ParseException {
        Response resp = new Response();

        try {
            Department department = departmentRepo.findById(developerRequest.getDepartment().getDept_Id()).orElseThrow(() -> new RuntimeException("Department not found"));
            Optional<Developer> developerEmail = developerRepo.findByEmail(developerRequest.getEmail());
            Optional<Developer> developerPhone = developerRepo.findByPhoneNo(developerRequest.getPhoneNo());
            if(developerEmail.isPresent() || developerPhone.isPresent()){
                resp.setResp_Code("82");
                resp.setResp_Msg("Record already Exist");
            }
            else {
                developerRequest.setDateCreated(LocalDateTime.now());
                Developer developer;
                developer = modelMapper.map(developerRequest, Developer.class);
                developer.setDepartment(department);
                developerRepo.save(developer);
                resp.setResp_Code("00");
                resp.setResp_Msg("Record Successfully added");
                resp.setData(developer.getDev_Id());
            }

        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully added");
            throw new RuntimeException(e);
        }
        return resp;

    }

    @Override
    public Response updateDeveloperDetails(DeveloperRequest developerRequest) {
        try {
            Optional<Developer> developerOptional = developerRepo.findById(developerRequest.getDev_Id());
            if (developerOptional.isPresent()) {
                developerRequest.setDateModified(LocalDateTime.now());
                Developer developer = modelMapper.map(developerRequest, Developer.class);
                //log.info(" Department "+ developerRequest.getDeptName());
                developerRepo.save(developer);
                resp.setResp_Code("00");
                resp.setResp_Msg("Record Successfully Modified");
                resp.setData(developer.getDev_Id());
            }
            else{
                resp.setResp_Code("82");
                resp.setResp_Msg("Record does not exist");
            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully Modified");
            throw new RuntimeException(e);
        }
        return resp;
    }


    @Override
    public Response getAllDevelopers() {
        try {
            List<Developer> developerList = developerRepo.findAll();
            if (developerList.isEmpty()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("List Empty");

            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(developerList);

            }

        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;

    }

    @Override
    public Response getByGender(String gender) {
        try{
           Optional<List<Developer>> devByGender= developerRepo.findByGender(gender);
            log.info("Department List "+ gender);
            log.info("devByGender "+ devByGender);
            if(!(devByGender.isPresent())){
                resp.setResp_Code("84");
                resp.setResp_Msg("No developer with this gender");
            }
            else{
                List<Developer> developerList= devByGender.get();
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(developerList);
            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);

        }
        return resp;
    }

    @Override
    public Response getByDepartment(Long dept_Id) {
        try {

            Department department = new Department();
            Response response = departmentService.getById(dept_Id);
            department = (Department) response.getData();
            List<Developer> devByDept = department.getDeveloper();
            if (devByDept.isEmpty()) {
                resp.setResp_Code("84");
                resp.setResp_Msg("No developer attached to this department");
            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(devByDept);
            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);

        }
        return resp;

    }

    @Override
    public Response getDeveloperById(Long dev_Id) {
        try {

            Optional<Developer> developer = developerRepo.findById(dev_Id);
            if (developer.isPresent()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("Developer doesn't exist");
            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(developer.get());
            }

        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;

    }



}
