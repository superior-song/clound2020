package com.atguigu.springcloud.entity.ucm;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/12/30 0030.
 */
@Data
public class Menu {
  String path;
  String name;
  Meta meta;
  List<MenuTmp> children;
}
