package com.kanavi.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <b><code>MinioUploadDto</code></b>
 * <p>
 * Description
 * <p/>
 * <b>Creation Time:</b>2020-08-17-15:50
 *
 * @author tanghailan
 * @since LearnDemo 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinioUploadDto {
    private String url;
    private String name;
}
