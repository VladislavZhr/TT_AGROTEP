package com.market.fishmarket.infostructure.adapter.in.validator;

import com.market.fishmarket.infostructure.adapter.in.dto.FishDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FishDtoValidator implements Validator {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final List<String> ALLOWED_CONTENT_TYPES = List.of("image/jpeg", "image/png");
    private static final int MAX_FILES = 5;

    @Override
    public boolean supports(Class<?> clazz) {
        return FishDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FishDTO fishDto = (FishDTO) target;

        List<MultipartFile> files = fishDto.getImageFiles();

        if (files == null || files.isEmpty()) {
            errors.rejectValue("imageFiles", "", "Потрібно завантажити хоча б одну фотографію.");
            return;
        }

        if (files.size() > MAX_FILES) {
            errors.rejectValue("imageFiles", "", "Не можна завантажити більше " + MAX_FILES + " фотографій.");
            return;
        }

        int index = 1;
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                errors.rejectValue("imageFiles", "", "Файл №" + index + " порожній.");
                index++;
                continue;
            }

            if (file.getSize() > MAX_FILE_SIZE) {
                errors.rejectValue("imageFiles", "", "Файл №" + index + " перевищує 5MB.");
                index++;
                continue;
            }

            if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
                errors.rejectValue("imageFiles", "", "Файл №" + index + " має недопустимий тип. Лише JPEG або PNG.");
                index++;
                continue;
            }

            try (InputStream inputStream = file.getInputStream()) {
                if (!isValidImage(inputStream)) {
                    errors.rejectValue("imageFiles", "", "Файл №" + index + " має неправильний формат або небезпечний вміст.");
                }
            } catch (IOException e) {
                errors.rejectValue("imageFiles", "", "Файл №" + index + ": помилка читання.");
            }

            index++;
        }
    }

    private boolean isValidImage(InputStream inputStream) {
        try {
            byte[] header = new byte[8];
            int bytesRead = inputStream.read(header);
            if (bytesRead < 3) return false;

            // JPEG
            if (header[0] == (byte) 0xFF && header[1] == (byte) 0xD8 && header[2] == (byte) 0xFF) return true;

            // PNG
            return header.length >= 8 &&
                    header[0] == (byte) 0x89 && header[1] == (byte) 0x50 &&
                    header[2] == (byte) 0x4E && header[3] == (byte) 0x47 &&
                    header[4] == (byte) 0x0D && header[5] == (byte) 0x0A &&
                    header[6] == (byte) 0x1A && header[7] == (byte) 0x0A;

        } catch (IOException e) {
            return false;
        }
    }
}

