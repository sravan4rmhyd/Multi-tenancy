package org.examples.repository;

import org.examples.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Query(value="select from public.fn_CopySchemaData(:source,:dest)",nativeQuery=true)
    Void createSchema(@Param("source") String sourceSchema,@Param("dest") String targetSchema);
}
