package com.fangzuo.assist.cloud.Utils;


import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.SettingList;
import com.fangzuo.assist.cloud.R;

import java.util.ArrayList;

/**
 * Created by NB on 2017/7/28.
 */

public class GetSettingList {
    public static ArrayList<SettingList> getList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList(App.getContext().getResources().getString(R.string.down_set), R.mipmap.download));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.Wifi_set),R.mipmap.wifi));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.voice_set),R.mipmap.sound));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.updata_app),R.mipmap.getnewversion));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.server_set),R.mipmap.tomcat));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.print_set),R.mipmap.test));
        items.add(new SettingList(App.getContext().getResources().getString(R.string.net_set),R.mipmap.test));
        return items;
    }
    public static ArrayList<SettingList> getPurchaseList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList("销售出库单",R.mipmap.sellinout));
        items.add(new SettingList("到货入库1",R.mipmap.sellinout));
        items.add(new SettingList("到货入库2",R.mipmap.sellinout));
        items.add(new SettingList("采购入库",R.mipmap.printmain));//实际是：采购订单下推外购入库
        items.add(new SettingList("其他出入库",R.mipmap.chuku));
        items.add(new SettingList("调拨单",R.mipmap.diaobo));
        items.add(new SettingList("挑板业务1",R.mipmap.sellinout));
        items.add(new SettingList("挑板业务2",R.mipmap.sellinout));
        items.add(new SettingList("挑板业务3",R.mipmap.sellinout));
        items.add(new SettingList("改板业务",R.mipmap.sellinout));
        items.add(new SettingList("盘盈入库",R.mipmap.sellinout));
        items.add(new SettingList("盘亏入库",R.mipmap.sellinout));
        items.add(new SettingList("简单生产领料",R.mipmap.chuku));
        items.add(new SettingList("简单产品入库",R.mipmap.purchaseinstorage));
        items.add(new SettingList("扫一扫",R.mipmap.scan));
        items.add(new SettingList("期初物料补打",R.mipmap.printmain));
        items.add(new SettingList("标签补打",R.mipmap.printmain));

//        items.add(new SettingList("简单生产领料",R.mipmap.chuku));
//        items.add(new SettingList("简单产品入库",R.mipmap.purchaseinstorage));
//        items.add(new SettingList("代存业务",R.mipmap.sellinout));
//        items.add(new SettingList("库存查询",R.mipmap.sellinout));
//        items.add(new SettingList("采购入库",R.mipmap.purchaseorder));
//        items.add(new SettingList("其他入库",R.mipmap.ruku));
//        items.add(new SettingList("其他出库",R.mipmap.chuku));
//        items.add(new SettingList("单据下推",R.mipmap.sellout));
//        items.add(new SettingList("盘点",R.mipmap.pandian));
        //-------------
//        items.add(new SettingList("采购订单",R.mipmap.purchaseorder));
//        items.add(new SettingList("销售订单",R.mipmap.saleorder));
//        items.add(new SettingList("盘点",R.mipmap.pandian));
//        for (int i=0; i<items.size();i++){
//            Log.e("test","定位ary:"+ary[i]);
//            Log.e("test","定位items:"+items.get(i).tag);
            //根据ary的值，遍历list符合的item并添加
//            for (int j=0;j<ary.length;j++){
//                if (TextUtils.equals(items.get(i).tag,ary[j])){
//                    Log.e("test","222加入："+items.get(i).toString());
//                    backItems.add(items.get(i));
//                }
//            }
//        }
        return items;
    }

    public static ArrayList<SettingList> getSaleList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList("简单生产领料",R.mipmap.chuku));
        items.add(new SettingList("简单产品入库",R.mipmap.purchaseinstorage));
        items.add(new SettingList("扫一扫",R.mipmap.scan));
//        items.add(new SettingList("挑板入库",R.mipmap.saleorder));
//        items.add(new SettingList("到柜入库",R.mipmap.sellinout));
//        items.add(new SettingList("简单生产入库",R.mipmap.sellout));
//        items.add(new SettingList("生产领料",R.mipmap.chuku));
        return items;
    }
    public static ArrayList<SettingList> getStorageList() {
        ArrayList<SettingList> items = new ArrayList<>();
//        items.add(new SettingList("盘点",R.mipmap.pandian));
//        items.add(new SettingList("调拨",R.mipmap.diaobo));
//        items.add(new SettingList("其他入库",R.mipmap.ruku));
//        items.add(new SettingList("其他出库",R.mipmap.chuku));
        return items;
    }

    public static ArrayList<SettingList> GetPushDownList() {
        ArrayList<SettingList> items = new ArrayList<>();
        items.add(new SettingList("采购订单下推外购入库单",R.mipmap.pandian));
        items.add(new SettingList("销售订单下推销售出库单",R.mipmap.pandian));
        items.add(new SettingList("销售订单下推销售退货单",R.mipmap.diaobo));
        items.add(new SettingList("销售出库单下推销售退货单",R.mipmap.ruku));
        items.add(new SettingList("发货通知单下推销售出库单",R.mipmap.chuku));
        items.add(new SettingList("退货通知单下推销售退货单",R.mipmap.pandian));
        items.add(new SettingList("调拨申请单下推分布式调入单",R.mipmap.diaobo));
        items.add(new SettingList("调拨申请单下推分布式调出单",R.mipmap.ruku));

//        items.add(new SettingList("生产任务单下推生产领料",R.mipmap.pandian));
//        items.add(new SettingList("采购订单下推收料通知单",R.mipmap.pandian));
//        items.add(new SettingList("销售订单下推发料通知单",R.mipmap.pandian));
//        items.add(new SettingList("生产任务单下推生产汇报单",R.mipmap.pandian));
//        items.add(new SettingList("汇报单下推产品入库",R.mipmap.pandian));
//        items.add(new SettingList("销售出库单验货",R.mipmap.pandian));
//        items.add(new SettingList("发货通知生成调拨单",R.mipmap.pandian));
//        items.add(new SettingList("产品入库验货",R.mipmap.pandian));
        return items;
    }
}
