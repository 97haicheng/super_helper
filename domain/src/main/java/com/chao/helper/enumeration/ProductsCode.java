package com.chao.helper.enumeration;

/**
 * Created by think on 2016/11/11.
 *
 * 各个供应商枚举类
 */
public class ProductsCode {

    public static void main(String[] args) {
        System.out.println( QGHXENUM.QGHXTEST104.getName());
    }

    //辽宁联通融合产品
    public enum LNLTRHENUM{

        //2G/3G/4G融合漫游流量产品
        M50(1, "m50"),//50M3G后付费漫游流量包
        M100(2, "m100"),//100M3G后付费漫游流量包
        M200(3, "m200"),//200M3G后付费漫游流量包
        M300(4, "m300"),//300M3G后付费漫游流量包
        M500(5, "m500"),//500M3G后付费漫游流量包

        //2G/3G/4G融合本地流量产品
        G50(5, "g50"),//50M省内2G/3G/4G流量包
        G100(5, "g100"),//100M省内2G/3G/4G流量包
        G200(5, "g200"),//200M省内2G/3G/4G流量包
        G300(5, "g300"),//300M省内2G/3G/4G流量包
        G400(5, "g400"),//400M省内2G/3G/4G流量包
        G500(5, "g500"),//500M省内2G/3G/4G流量包

        //2G/3G融合漫游流量产品
        BM20(5, "bm20"),//20M全国2G/3G流量包
        BM50(5, "bm50"),//50M全国2G/3G流量包
        BM100(5, "bm100"),//100M全国2G/3G流量包
        BM200(5, "bm200"),//200M全国2G/3G流量包
        BM300(5, "bm300"),//300M全国2G/3G流量包
        BM500(5, "bm500"),//500M全国2G/3G流量包

        //日包
        R1024(5, "r1024");//日包



        // 成员变量
        private int index;
        private String name;

        //构造方法
        private LNLTRHENUM(int index, String name) {
            this.index = index;
            this.name = name;

        }

        // 普通方法
        public static String getName(int index) {
            for (LNLTRHENUM o : LNLTRHENUM.values()) {
                if (o.getIndex() == index) {
                    return o.name;
                }
            }
            return null;
        }

        // get set 方法
        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //全国后向产品
    public enum QGHXENUM{

        QGHXTEST104(1, "104"),//联通宽带全国50M
        QGHXTEST108(2, "108"),//惠付移动30M
        QGHXTEST105(3, "105"),//辽宁联通漫游50M
        QGHXTEST109(4, "109"),//联通时科50M
        QGHXTEST110(5, "110"),//维客移动30M
        QGHXTEST111(5, "111"),//维客联通50M
        QGHXTEST112(6, "112");//社会化企业合作流量直充(电信150M)

        // 成员变量
        private int index;
        private String name;

        //构造方法
        private QGHXENUM(int index, String name) {
            this.index = index;
            this.name = name;

        }

        // 普通方法
        public static String getName(int index) {
            for (QGHXENUM o : QGHXENUM.values()) {
                if (o.getIndex() == index) {
                    return o.name;
                }
            }
            return null;
        }

        // get set 方法
        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
