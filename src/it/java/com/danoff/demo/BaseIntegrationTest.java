package com.danoff.demo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("itest")
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insertData.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/deleteTableData.sql")
})
public abstract class BaseIntegrationTest {

}
