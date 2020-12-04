package com.kanavi.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tanghailan
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinioUploadDto {
    private String url;
    private String name;
}
