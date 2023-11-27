package com.lodi.gen.mybatisPlus;

import java.util.EnumSet;

/**
 * @author MaybeBin
 * @createDate 2023-11-19
 */
public enum FileEnum {

    AddRequest,

    PageRequest,

    UpdateRequest,

    VO,

    Convert;

    public static String getStringValue(String value) {
        for (FileEnum fileEnum : EnumSet.allOf(FileEnum.class)) {
            if (fileEnum.name().equals(value)) {
                return fileEnum.name();
            }
        }
        return null;
    }

    public static Boolean isPacketMangling(String value) {
        if (VO.toString().equals(value)) {
            return false;
        }
        for (FileEnum fileEnum : EnumSet.allOf(FileEnum.class)) {
            if (fileEnum.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

}
