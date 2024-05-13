CREATE TABLE tbl_academic_title_history(
	member_id BIGINT UNSIGNED NOT NULL,
	academic_title_id BIGINT UNSIGNED NOT NULL,
	start_date DATE,
	end_date DATE,
	scientific_field_id BIGINT UNSIGNED,
	PRIMARY KEY (member_id, academic_title_id),
	CONSTRAINT memberr_fk FOREIGN KEY (member_id) REFERENCES tbl_member(id),
	CONSTRAINT academic_titlee_fk FOREIGN KEY (academic_title_id) REFERENCES tbl_academic_title(id),
	CONSTRAINT scientific_fieldd_fk FOREIGN KEY (scientific_field_id) REFERENCES tbl_scientific_field(id)
);