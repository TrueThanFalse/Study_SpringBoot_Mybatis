package com.example.mybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileVO {

	/*
	 *  CREATE TABLE file(
		uuid VARCHAR(256),
		save_dir VARCHAR(256) NOT NULL,
		file_name VARCHAR(256) NOT NULL,
		file_type int(1) DEFAULT 0,
		bno BIGINT NOT NULL,
		file_size BIGINT NOT NULL,
		reg_at DATETIME DEFAULT NOW(),
		PRIMARY KEY(uuid)
		);
	 */
	
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private long bno;
	private long fileSize;
	private String regAt;
}
