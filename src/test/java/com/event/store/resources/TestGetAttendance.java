package com.event.store.resources;

import com.event.store.entity.EmployeeEntity;
import com.event.store.service.EventStoreService;
import com.event.store.service.TestEventStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class TestGetAttendance {

    @Mock
    private EventStoreService eventStoreService;

    @InjectMocks
    GetAttendance getAttendance;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(getAttendance).build();
    }

    @Test
    public void testGetAttendanceWithExistEmployeeId() throws Exception {
        EmployeeEntity employeeEntity = EmployeeEntity.builder().attendance("present").empId(147).name("JOHN").build();
        when(eventStoreService.getEmployeeAttendance(anyInt())).thenReturn(employeeEntity);
        MvcResult result  = this.mockMvc.perform(get("/get/attendance").param("empId","147").accept(MediaType.APPLICATION_JSON))
                                        .andExpect(status().isOk()).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    public void testGetAttendanceWithNotExistEmployeeId() throws Exception {
        when(eventStoreService.getEmployeeAttendance(anyInt())).thenReturn(null);
        MvcResult result = this.mockMvc.perform(get("/get/attendance").param("empId", "147").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertNotNull(result.getResponse());
    }

}
