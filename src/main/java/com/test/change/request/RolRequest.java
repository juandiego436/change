package com.test.change.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolRequest {
    @ApiModelProperty(position = 0,dataType = "String", required = true, example = "USER")
    private String rolNombre;
}
