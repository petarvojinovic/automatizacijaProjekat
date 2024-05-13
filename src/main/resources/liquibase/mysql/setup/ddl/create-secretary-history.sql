CREATE TABLE tbl_secretary_history(
	department_id BIGINT UNSIGNED NOT NULL,
	member_id BIGINT UNSIGNED NOT NULL,
	start_date DATE,
	end_date DATE,
	PRIMARY KEY (department_id, member_id),
	CONSTRAINT depart_secretary_fk FOREIGN KEY (department_id) REFERENCES tbl_department(id),
	CONSTRAINT member_secretary_fk FOREIGN KEY (member_id) REFERENCES tbl_member(id)
);