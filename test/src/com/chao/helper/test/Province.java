package com.chao.helper.test;

/**
 * Created by think on 2017/1/5.
 */
public enum Province {
    ALL(0, "全国"),

    BEI_JING(1, "北京"),
    SHANG_HAI(2, "上海"),
    YUN_NAN(3, "云南"),
    NEI_MENG_GU(4, "内蒙古"),
    JI_LIN(5, "吉林"),
    SI_CHUAN(6, "四川"),
    TIAN_JIN(7, "天津"),
    NING_XIA(8, "宁夏"),
    AN_HUI(9, "安徽"),
    SHAN_DONG(10, "山东"),
    SHAN_XI(11, "山西"),
    GUANG_DONG(12, "广东"),
    GUANG_XI(13, "广西"),
    XIN_JIANG(14, "新疆"),
    JIANG_SU(15, "江苏"),
    JIANG_XI(16, "江西"),
    HE_BEI(17, "河北"),
    HE_NAN(18, "河南"),
    ZHE_JIANG(19, "浙江"),
    HAI_NAN(20, "海南"),
    HU_BEI(21, "湖北"),
    HU_NAN(22, "湖南"),
    GAN_SU(23, "甘肃"),
    FU_JIAN(24, "福建"),
    XI_ZANG(25, "西藏"),
    GUI_ZHOU(26, "贵州"),
    LIAO_NING(27, "辽宁"),
    CHONG_QING(28, "重庆"),
    SHAN3_XI(29, "陕西"),
    QING_HAI(30, "青海"),
    HEI_LONG_JIANG(31, "黑龙江");

    private int provideCode;
    private String code;

    Province(int provideCode, String code) {
        this.provideCode = provideCode;
        this.code = code;
    }

    public int getProvideCode() {
        return provideCode;
    }

    public String getCode() {
        return code;
    }

    public static String getName(int provideCode) {
        for (Province c : Province.values()) {
            if (c.getProvideCode() == provideCode) {
                return c.code;
            }
        }
        return null;
    }

    public static Integer getCode(String name) {

        for (Province c : Province.values()) {
            if (c.getCode()==name) {
                return c.getProvideCode();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Province.getCode("辽宁"));
    }
}
