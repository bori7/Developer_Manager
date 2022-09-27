package com.developer.DeveloperManager.service.implementation;

import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.repository.DepartmentRepo;
import com.developer.DeveloperManager.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service

@Slf4j
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private  DepartmentRepo departmentRepo;
    @Autowired
    private ModelMapper modelMapper;

    Response resp= new Response();
    //Department department= new Department();

    @Override
    public Response saveDepartment(DepartmentRequest departmentRequest) throws ParseException {
        try {
            if(departmentRequest.getDeptName()==null){
                resp.setResp_Code("84");
                resp.setResp_Msg("Department Name is Empty");
            }
           else if(departmentRepo.findByDeptName(departmentRequest.getDeptName()).isPresent()){
                resp.setResp_Code("82");
                resp.setResp_Msg("Name Already Exist");
            }
            else{
            departmentRequest.setDateCreated(LocalDateTime.now());
            departmentRequest.setDateModified(LocalDateTime.now());

            Department department= modelMapper.map(departmentRequest, Department.class);
            log.info(" Department "+ departmentRequest.getDeptName());
            departmentRepo.save(department);
            resp.setResp_Code("00");
            resp.setResp_Msg("Record Successfully added");
            resp.setData(department.getDept_Id());

        }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully added");
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response updateDepartmentName(DepartmentRequest departmentRequest) throws ParseException {
        try {
            Optional<Department> departmentOptional= departmentRepo.findById(departmentRequest.getDept_Id());
            ;
            if(departmentRequest.getDeptName()==null || departmentRequest.getDept_Id()==null  ){
                resp.setResp_Code("84");
                resp.setResp_Msg("Department Name or ID is Empty");
            }
            if(departmentOptional.isPresent()){
                departmentRequest.setDateModified(LocalDateTime.now());
                 Department department= modelMapper.map(departmentRequest, Department.class);
                log.info(" Department "+ departmentRequest.getDeptName());
                departmentRepo.save(department);
                resp.setResp_Code("00");
                resp.setResp_Msg("Record Successfully Modified");
                resp.setData(department.getDept_Id());

            }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully Modified");
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getAllDepartment() {
        try {
            List<Department> departmentList = departmentRepo.findAll(Sort.by(Sort.Direction.ASC, "deptName"));
            if (departmentList.isEmpty()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("The List is Empty");

            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(departmentList);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getById(Long dept_Id) {

        try{
            log.info("Department Id "+ dept_Id);
            Optional<Department> departmentOptional= departmentRepo.findById(dept_Id);
            if(departmentOptional.isPresent()){
                Department department= departmentOptional.get();
                resp.setData(department);
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");

            }
            else{
                resp.setResp_Code("84");
                resp.setResp_Msg("Department doesn't exist");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getByDeptName(String deptName) {
        try{
            Optional<Department> departmentOptional= departmentRepo.findByDeptName(deptName);
            if(departmentOptional.isPresent()){
                Department department= departmentOptional.get();
                resp.setData(department);
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");

            }
            else{
                resp.setResp_Code("84");
                resp.setResp_Msg("Department doesn't exist");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getByKeyWord(String name) {

        try {
            List<Department> departmentList = departmentRepo.findByDeptNameContaining(name);
            if (departmentList.isEmpty()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("No department contains the keyword");
            }
            else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(departmentList);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getDeveloperByDept(Long dept_Id) {
        try{
        log.info("Department Id "+ dept_Id);
        //Department department = new Department();
        Response response = getById(dept_Id);
        Department department = (Department) response.getData();
        log.info("Department Id "+ department.toString());
        List<Developer> devByDept = department.getDeveloper();
        if (devByDept.isEmpty()) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
        } else {
            resp.setResp_Code("00");
            resp.setResp_Msg("Success");
            log.info(devByDept.toString());
            resp.setData(devByDept);
        }
    } catch (Exception e) {
        resp.setResp_Code("00");
        resp.setResp_Msg("Success");
        throw new RuntimeException(e);

    }
        return resp;

}


}
