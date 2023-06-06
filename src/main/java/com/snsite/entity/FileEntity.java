package com.snsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "files")
public class FileEntity extends BaseEntity {
	@Column
	@NotNull(message = "ObjectId must not be empty")
	private Long objectId;
	@Column
	@NotNull(message = "FileType must not be empty")
	private Integer fileType;
	@Column
	@NotNull(message = "ObjectType must not be empty")
	private Integer objectType;
	@Column
	@NotBlank(message = "Url must not be empty")
	private String url;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
