package com.snsite.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.snsite.dto.FileDto;
import com.snsite.entity.FileEntity;

@Component
public class FileConverter {
	public FileEntity toEntity(FileDto fileDto) {
		FileEntity fileEntity = new FileEntity();
		fileEntity.setUserId(fileDto.getUserId());
		fileEntity.setFileType(FileDto.FileTypeToId.get(fileDto.getFileType()));
		fileEntity.setObjectType(FileDto.ObjectTypeToId.get(fileDto.getObjectType()));
		fileEntity.setUrl(fileDto.getUrl());
		return fileEntity;
	}

	public FileDto toDto(FileEntity fileEntity) {
		FileDto fileDto = new FileDto();
		fileDto.setId(fileEntity.getId());
		fileDto.setCreatedAt(fileEntity.getCreatedAt());
		fileDto.setCreatedBy(fileEntity.getCreatedBy());
		fileDto.setUpdatedAt(fileEntity.getUpdatedAt());
		fileDto.setUpdatedBy(fileEntity.getUpdatedBy());
		fileDto.setIsActive(fileEntity.getIsActive());
		fileDto.setUserId(fileEntity.getUserId());
		fileDto.setFileType(FileDto.FileTypeToString.get(fileEntity.getFileType()));
		fileDto.setObjectType(FileDto.ObjectTypeToString.get(fileEntity.getObjectType()));
		fileDto.setUrl(fileEntity.getUrl());
		return fileDto;
	}

	public List<FileDto> toListDto(List<FileEntity> fileEntities) {
		if (fileEntities.size() == 0)
			return null;
		List<FileDto> notificationDtos = new ArrayList<>();
		for (FileEntity fileEntity : fileEntities) {
			notificationDtos.add(this.toDto(fileEntity));
		}
		return notificationDtos;
	}
}
