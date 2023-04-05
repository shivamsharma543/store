package com.event.store.resources;

import com.event.store.entity.EmployeeEntity;
import com.event.store.model.ApiResponse;
import com.event.store.model.SuccessApiResponse;
import com.event.store.service.EventStoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/get")
public class GetAttendance {

    @Autowired
    private final EventStoreService eventStoreService;

    @GetMapping("/attendance")
    public ResponseEntity<ApiResponse> getAttendanceForEmp(@RequestParam int empId) {
        EmployeeEntity employeeEntity = eventStoreService.getEmployeeAttendance(empId);
        return employeeEntity != null ? ResponseEntity.ok(new SuccessApiResponse(employeeEntity.getEmpId(), employeeEntity.getName(), employeeEntity.getPresentHours(), "attendance fetched successfully"))
                : new ResponseEntity<>(new ApiResponse("no user exist for empId" + empId), HttpStatus.OK);

    }
}
