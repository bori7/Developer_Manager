package com.developer.DeveloperManager.service.implementation;

import com.developer.DeveloperManager.dto.DepartmentRequest;
import com.developer.DeveloperManager.model.Department;
import com.developer.DeveloperManager.model.Developer;
import com.developer.DeveloperManager.model.Response;
import com.developer.DeveloperManager.repository.DepartmentRepo;
import com.developer.DeveloperManager.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final ModelMapper modelMapper;
    Response resp= new Response();
    @Override
    public Response saveDepartment(DepartmentRequest departmentRequest) throws ParseException {
        try {
            departmentRequest.setDateCreated(LocalDateTime.now());
            Department department= modelMapper.map(departmentRequest, Department.class);
            departmentRepo.save(department);
            resp.setResp_Code("00");
            resp.setResp_Msg("Record Successfully added");
            resp.setData(department.getDept_Id());
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Record not successfully added");
            throw new RuntimeException(e);
        }
        return resp;
    }

    @Override
    public Response getAllDepartment() {
        try {
            List<Department> departmentList = new ArrayList<>();
            departmentList = departmentRepo.findAll();

            if (departmentList.isEmpty()) {
                resp.setResp_Code("82");
                resp.setResp_Msg("List is Empty");

            } else {
                resp.setResp_Code("00");
                resp.setResp_Msg("Success");
                resp.setData(departmentList);

            }
            return resp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

   /* @Override
    public Response getDeveloperById(String dept_Id) {
        try{
        List<Developer> developers= departmentRepo.findByDeveloperByDeptId(dept_Id);

        if(developers.isEmpty()){
            resp.setResp_Code("82");
            resp.setResp_Msg("Array Empty");

        }
        else{
            resp.setResp_Code("00");
            resp.setResp_Msg("Success");
            resp.setData(developers);
        }
        } catch (Exception e) {
            resp.setResp_Code("99");
            resp.setResp_Msg("Failed");
            throw new RuntimeException(e);
        }
        return resp;
    }*/
}
