package com.lodi.common.model.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网站基本信息
 *
 * @author MaybeBin
 * @createDate 2024-03-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebsiteBasic {

    private Long articleCount;
    private Long tagCount;
    private Long categoryCount;
    private Long commentCount;

}
