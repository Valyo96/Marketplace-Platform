package com.platform.marketplace.Marketplace.Platform.service.image;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import com.platform.marketplace.Marketplace.Platform.model.Event;
import com.platform.marketplace.Marketplace.Platform.utility.exceptions.ImageConvertorException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.IMAGE_CONVERTER_FAILED_EXCEPTION_MESSAGE;

@Service
public class ImageConvertor {
    public byte[] convertMultipartToByteArray(EventDTO eventDTO) {
        MultipartFile file = eventDTO.getImagePath();
        try {
           return file.getBytes();
        } catch (IOException e) {
            throw new ImageConvertorException(IMAGE_CONVERTER_FAILED_EXCEPTION_MESSAGE);
        }
    }

    public String convertByteToString(byte[] imageBytes){
        return Base64.getEncoder().encodeToString(imageBytes);
    }

}
