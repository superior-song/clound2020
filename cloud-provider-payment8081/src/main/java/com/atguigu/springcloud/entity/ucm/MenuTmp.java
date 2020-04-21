package com.atguigu.springcloud.entity.ucm;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/12/30 0030.
 */
@Data
public class MenuTmp {
    String path;
    String name;
    Meta meta;
    List<Map<String ,Object>> children;
}
