insert into tbl_member(first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
values ("John", "Johnson",
(select (id) from tbl_academic_title where name="academic-title-2"),
(select (id) from tbl_education_title where name="education-title-3"),
(select (id) from tbl_scientific_field where name="scientific-field-4"),
(select (id) from tbl_department where name="department-5"));

insert into tbl_member(first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
values ("Robert", "Robertson",
(select (id) from tbl_academic_title where name="academic-title-1"),
(select (id) from tbl_education_title where name="education-title-3"),
(select (id) from tbl_scientific_field where name="scientific-field-3"),
(select (id) from tbl_department where name="department-4"));

insert into tbl_member(first_name, last_name, academic_title_id, education_title_id, scientific_field_id, department_id)
values ("Will", "Wilson",
(select (id) from tbl_academic_title where name="academic-title-3"),
(select (id) from tbl_education_title where name="education-title-5"),
(select (id) from tbl_scientific_field where name="scientific-field-4"),
(select (id) from tbl_department where name="department-6"));