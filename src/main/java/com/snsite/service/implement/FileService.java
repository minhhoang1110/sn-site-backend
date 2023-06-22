package com.snsite.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;
import com.snsite.converter.FileConverter;
import com.snsite.dto.FileDto;
import com.snsite.entity.FileEntity;
import com.snsite.entity.UserEntity;
import com.snsite.repository.FileRepository;
import com.snsite.repository.UserRepository;
import com.snsite.service.IFileService;
import com.snsite.type.request.UploadFileRequest;

@Service
public class FileService implements IFileService {
	private final Cloudinary cloudinary = Singleton.getCloudinary();
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FileConverter fileConverter;
	@Autowired
	private UserRepository userRepository;

	@Override
	public FileDto uploadFile(UploadFileRequest uploadFileRequest) {
		try {
			Map uploadResult = cloudinary.uploader().upload(uploadFileRequest.getFile().getBytes(),
					ObjectUtils.asMap("use_filename", true, "unique_filename", false, "overwrite", true));
			String fileURL = uploadResult.get("url").toString();
			FileEntity fileEntity = new FileEntity();
			fileEntity.setFileType(FileDto.FileTypeToId.get(uploadFileRequest.getFileType()));
			fileEntity.setObjectType(FileDto.ObjectTypeToId.get(uploadFileRequest.getObjectType()));
			fileEntity.setUserId(uploadFileRequest.getUserId());
			fileEntity.setUrl(fileURL);
			fileEntity = fileRepository.save(fileEntity);
			return fileConverter.toDto(fileEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<FileDto> getListFile(Long userId) {
		Optional<UserEntity> user = userRepository.findById(userId);
		if (!user.isPresent())
			return null;
		List<FileEntity> fileEntities = fileRepository.findAllByUserId(userId);
		List<FileEntity> result = new ArrayList<>();
		for (FileEntity fileEntity : fileEntities) {
			if (fileEntity.getObjectType() == FileDto.ObjectTypeUserAvatar
					|| fileEntity.getObjectType() == FileDto.ObjectTypeUserCover
					|| fileEntity.getObjectType() == FileDto.ObjectTypePost) {
				result.add(fileEntity);
			}
		}
		if (result == null || result.size() == 0)
			return null;
		return fileConverter.toListDto(result);
	}

}
