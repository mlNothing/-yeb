package com.example.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mlNothing
 * @date 2021/12/8 19:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResBean {
    @ApiModelProperty(value = "总条数")
    private Long total;
    @ApiModelProperty(value = "返回的数据")
    private List<?> data;
}
