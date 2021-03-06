package com.fangzuo.assist.cloud.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Adapter.StripAdapter;
import com.fangzuo.assist.cloud.Dao.Client;
import com.fangzuo.assist.cloud.Dao.Org;
import com.fangzuo.assist.cloud.Dao.Storage;
import com.fangzuo.assist.cloud.Dao.Suppliers;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDB2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDBDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.DbBox.FragmentDBMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox.FragmentPKDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox.FragmentPYingDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PDBox.FragmentPYingMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDh2Main;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.GbManagerBox.FragmentGbGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.GbManagerBox.FragmentGbInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOInDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentOOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPISDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPISMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrGetDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisDhMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentPrisMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentSaleOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.FragmentSaleOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentCgOrder2WgrkDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentCgOrder2WgrkMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentDbApply2DBDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentDbApply2DBMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox.FragmentTbGetMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox.FragmentPrisTBDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.TbManagerBox.FragmentPrisTBMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwInDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwInMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.Fragment3HwOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentYbOutDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.OtherInOutBox.FragmentYbOutMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentBackMsg2SaleBackDetail;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentBackMsg2SaleBackMain;
import com.fangzuo.assist.cloud.Fragment.TabForActivity.PushDownFragment.FragmentSaleOutDetailForPD;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.Lg;
import com.fangzuo.assist.cloud.Utils.ShareUtil;
import com.fangzuo.assist.cloud.databinding.ActivityPagerForBinding;
import com.fangzuo.assist.cloud.zxing.ScanManager;
import com.fangzuo.greendao.gen.DaoSession;
import com.fangzuo.greendao.gen.T_DetailDao;
import com.fangzuo.greendao.gen.T_mainDao;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PagerForActivity extends BaseActivity {
    ActivityPagerForBinding binding;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private int activity;
    private Org orgIn;
    private Org orgOut;
    private Org huozhuIn;
    private Org huozhuOut;
    private Storage storage;
    private Client client;
    private Suppliers suppliers;
    private String DBType;//调拨类型(货主类型)
    private String DBDirection;//调拨方向
    private String note;
    private String FOrderNo;//业务单号
    private String ManStore;//仓管员
    private String ManSale;//销售员
    private String ManBuyer;//销售员
    private String ManGet;//领料人
    private String DepartMent;//生产车间
    private String DepartMentBuy;//采购部门
    private String date;//日期
    private String printNum;//日期
    private boolean hasLock=false;//判断表头是否被锁住
    private boolean isStorage=false;//是否带出默认仓库
    StripAdapter stripAdapter;
    @Override
    protected boolean isScan() {
        return true;
    }

    public String getPrintNum() {
        return printNum;
    }

    public void setPrintNum(String printNum) {
        this.printNum = printNum;
    }

    @Override
    protected void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager_for);
        Intent in = getIntent();
        Bundle b = in.getExtras();
//        String searchString = b.getString("search", "");
        activity = b.getInt("activity");
        Lg.e("获得数据：",""+activity);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
        titles.add("基本信息");
        titles.add("明细信息");
        setFragment(activity);

    }

    private void setFragment(int activity){
        switch (activity){
            case Config.ProductInStoreActivity:
                binding.topActivity.tvTitle.setText("简单生产入库");
                fragments.add(new FragmentPrisMain());
                fragments.add(new FragmentPrisDetail());
                break;
            case Config.ProductGetActivity:
                binding.topActivity.tvTitle.setText("简单生产领料");
                fragments.add(new FragmentPrGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.DBActivity:
                binding.topActivity.tvTitle.setText("组织内调拨单");
                fragments.add(new FragmentDBMain());
                fragments.add(new FragmentDBDetail());
                break;
            case Config.DB2Activity:
                binding.topActivity.tvTitle.setText("跨组织调拨单");
                fragments.add(new FragmentDB2Main());
                fragments.add(new FragmentDBDetail());
                break;
            case Config.SaleOutActivity:
                binding.topActivity.tvTitle.setText("销售出库单");
                fragments.add(new FragmentSaleOutMain());
                fragments.add(new FragmentSaleOutDetail());
                break;
            case Config.PurchaseInStoreActivity:
                binding.topActivity.tvTitle.setText("采购入库单");
                fragments.add(new FragmentPISMain());
                fragments.add(new FragmentPISDetail());
                break;
            case Config.PdSaleOrder2SaleOutActivity:
                binding.topActivity.tvTitle.setText("销售订单下推销售出库");
                fragments.add(new FragmentSaleOutMain());
                fragments.add(new FragmentSaleOutDetailForPD());
                break;
            case Config.PdSaleOrder2SaleOut2Activity:
                binding.topActivity.tvTitle.setText("VMI销售订单下推销售出库单");
                fragments.add(new FragmentSaleOutMain());
                fragments.add(new FragmentSaleOutDetailForPD());
                break;
            case Config.PdBackMsg2SaleBackActivity:
                binding.topActivity.tvTitle.setText("退货通知单下推销售退货单");
                fragments.add(new FragmentBackMsg2SaleBackMain());
                fragments.add(new FragmentBackMsg2SaleBackDetail());
                break;
            case Config.TbGetActivity:
                binding.topActivity.tvTitle.setText("挑板领料1");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.TbGet2Activity:
                binding.topActivity.tvTitle.setText("挑板领料2");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.TbGet3Activity:
                binding.topActivity.tvTitle.setText("挑板领料3");
                fragments.add(new FragmentTbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.TbInActivity:
                binding.topActivity.tvTitle.setText("挑板入库1");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.TbIn2Activity:
                binding.topActivity.tvTitle.setText("挑板入库2");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;
            case Config.TbIn3Activity:
                binding.topActivity.tvTitle.setText("挑板入库3");
                fragments.add(new FragmentPrisTBMain());
                fragments.add(new FragmentPrisTBDetail());
                break;

            case Config.GbGetActivity:
                binding.topActivity.tvTitle.setText("改板领料");
                fragments.add(new FragmentGbGetMain());
                fragments.add(new FragmentPrGetDetail());
                break;
            case Config.GbInActivity:
                binding.topActivity.tvTitle.setText("改板入库");
                fragments.add(new FragmentGbInMain());
                fragments.add(new FragmentPrisDetail());
                break;
            case Config.DhInActivity:
                binding.topActivity.tvTitle.setText("到货入库1");
                fragments.add(new FragmentPrisDhMain());
                fragments.add(new FragmentPrisDetail());
                break;
            case Config.DhIn2Activity:
                binding.topActivity.tvTitle.setText("到货入库2");
                fragments.add(new FragmentPrisDh2Main());
                fragments.add(new FragmentPrisDetail());
                break;
//            case Config.SimpleInActivity:
//                binding.topActivity.tvTitle.setText("简单生产入库");
//                fragments.add(new FragmentPrisSimpleInMain());
//                fragments.add(new FragmentPrisDetail());
//                break;
            case Config.YbOutActivity:
                binding.topActivity.tvTitle.setText("样板出库");
                fragments.add(new FragmentYbOutMain());
                fragments.add(new FragmentYbOutDetail());
                break;
            case Config.HwOut3Activity:
                binding.topActivity.tvTitle.setText("第三方货物出库");
                fragments.add(new Fragment3HwOutMain());
                fragments.add(new Fragment3HwOutDetail());
                break;
            case Config.HwIn3Activity:
                binding.topActivity.tvTitle.setText("第三方货物入库");
                fragments.add(new Fragment3HwInMain());
                fragments.add(new Fragment3HwInDetail());
                break;
            case Config.PdCgOrder2WgrkActivity:
                binding.topActivity.tvTitle.setText("采购订单下推外购入库");
                fragments.add(new FragmentCgOrder2WgrkMain());
                fragments.add(new FragmentCgOrder2WgrkDetail());
                break;
            case Config.PYingActivity:
                binding.topActivity.tvTitle.setText("盘盈入库");
                fragments.add(new FragmentPYingMain());
                fragments.add(new FragmentPYingDetail());
                break;
            case Config.PkuiActivity:
                binding.topActivity.tvTitle.setText("盘亏入库");
                fragments.add(new FragmentPYingMain());
                fragments.add(new FragmentPKDetail());
                break;
            case Config.PkuiVMIActivity:
                binding.topActivity.tvTitle.setText("VMI盘亏入库");
                fragments.add(new FragmentPYingMain());
                fragments.add(new FragmentPKDetail());
                break;
            case Config.PdDbApply2DBActivity:
                binding.topActivity.tvTitle.setText("调拨申请单下推直接调拨单");
                fragments.add(new FragmentDbApply2DBMain());
                fragments.add(new FragmentDbApply2DBDetail());
                break;
            case Config.PdDbApply2DB4VMIActivity:
                binding.topActivity.tvTitle.setText("VMI调拨申请单下推直接调拨单");
                fragments.add(new FragmentDbApply2DBMain());
                fragments.add(new FragmentDbApply2DBDetail());
                break;





//            case Config.DgInActivity:
//                binding.topActivity.tvTitle.setText("到柜入库");
//                fragments.add(new FragmentPrisDGMain());
//                fragments.add(new FragmentPrisDetail());
//                break;
//            case Config.DcInActivity:
//                binding.topActivity.tvTitle.setText("代存入库");
//                fragments.add(new FragmentGbInMain());
//                fragments.add(new FragmentPrisDetail());
//                break;
            case Config.OtherInStoreActivity:
                binding.topActivity.tvTitle.setText("其他入库");
                fragments.add(new FragmentOInMain());
                fragments.add(new FragmentOInDetail());
                break;
            case Config.OtherOutStoreActivity:
                binding.topActivity.tvTitle.setText("其他出库");
                fragments.add(new FragmentOOutMain());
                fragments.add(new FragmentOOutDetail());
                break;
        }
        //设置pager
        if (null==stripAdapter){
            stripAdapter = new StripAdapter(getSupportFragmentManager(), fragments, titles);
            binding.viewpager.setAdapter(stripAdapter);
            binding.tabstrip.setShouldExpand(true);
            binding.tabstrip.setViewPager(binding.viewpager);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tabstrip.setDividerColor(getColor(R.color.cpb_blue));
                binding.tabstrip.setIndicatorColor(getColor(R.color.cpb_blue));
            }else{
                binding.tabstrip.setDividerColor(Color.BLUE);
                binding.tabstrip.setIndicatorColor(Color.BLUE);
            }
            binding.tabstrip.setUnderlineHeight(3);
            binding.tabstrip.setTextSize(45);
            binding.tabstrip.setIndicatorHeight(6);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void OnReceive(String code) {

    }


    public boolean isHasLock() {
        return hasLock;
    }

    public void setHasLock(boolean hasLock) {
        this.hasLock = hasLock;
    }
    public int getActivity(){
        return activity;
    }

    public void setOrgIn(Org orgIn) {
        this.orgIn = orgIn;
    }

    public void setOrgOut(Org orgOut) {
        this.orgOut = orgOut;
    }

    public void setHuozhuIn(Org huozhuIn) {
        this.huozhuIn = huozhuIn;
    }

    public void setHuozhuOut(Org huozhuOut) {
        this.huozhuOut = huozhuOut;
    }

    public void setStorage(Storage s) {
        this.storage = s;
    }

    public Storage getStorage() {
        return storage!=null?storage:new Storage("","","","","","");
    }

    public void setClient(Client s) {
        this.client = s;
    }
    public void setSuppliers(Suppliers s) {
        this.suppliers = s;
    }

    public Client getClient() {
        return client!=null?client:new Client("","","","");
    }
    public Suppliers getSuppliers() {
        return suppliers!=null?suppliers:new Suppliers("","","","","","","","","","","","","");
    }

    public void setDBType(String DBType) {
        this.DBType = DBType;
    }

    public void setDBDirection(String DBDirection) {
        this.DBDirection = DBDirection;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFOrderNo() {
        return FOrderNo;
    }

    public void setFOrderNo(String fOrderNo) {
        this.FOrderNo = fOrderNo;
    }

    public void setManStore(String manStore) {
        ManStore = manStore;
    }

    public void setManSale(String manSale) {
        ManSale = manSale;
    }
    public void setManBuyer(String manSale) {
        ManBuyer = manSale;
    }
    public void setManGet(String manGet) {
        ManGet = manGet;
    }

    public void setDepartMent(String departMent) {
        DepartMent = departMent;
    }
    public void setDepartMentBuy(String departMent) {
        DepartMentBuy = departMent;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setStorage(boolean storage) {
        isStorage = storage;
    }

    public String getDBType() {
        return DBType==null?"":DBType;
    }
    public boolean isStorage() {
        return isStorage;
    }
    public String getDBDirection() {
        return DBDirection==null?"":DBDirection;
    }

    public String getNote() {
        return note==null?"":note;
    }

    public String getManStore() {
        return ManStore==null?"":ManStore;
    }

    public String getManSale() {
        return ManSale==null?"":ManSale;
    }
    public String getManBuyer() {
        return ManBuyer==null?"":ManBuyer;
    }
    public String getManGet() {
        return ManGet==null?"":ManGet;
    }

    public String getDepartMent() {
        return DepartMent==null?"":DepartMent;
    }
    public String getDepartMentBuy() {
        return DepartMentBuy==null?"":DepartMentBuy;
    }

    public String getDate() {
        return date==null?"":date;
    }

    public CheckBox getIsHebing() {
        return binding.topActivity.ishebing;
    }

    public CheckBox getIsAuto() {
        return binding.topActivity.isAutoAdd;
    }

    public Org getOrgIn() {
        return orgIn==null?new Org("","","",""):orgIn;
    }
    public String getOrgIn(int type) {
        if (type==0){
            return orgIn==null?"":orgIn.FNumber;
        }else{
            return orgIn==null?"":orgIn.FOrgID;
        }
    }

    public Org getOrgOut() {
        return orgOut==null?new Org("","","",""):orgOut;
    }
    public String getOrgOut(int type) {
        if (type==0){
            return orgOut==null?"":orgOut.FNumber;
        }else{
            return orgOut==null?"":orgOut.FOrgID;
        }
    }

    public Org getHuozhuIn() {
        return huozhuIn;
    }
    public String getHuozhuIn(int type) {
        if (type==0){
            return huozhuIn==null?"":huozhuIn.FNumber;
        }else{
            return huozhuIn==null?"":huozhuIn.FOrgID;
        }
    }

    public Org getHuozhuOut() {
        return huozhuOut;
    }
    public String getHuozhuOut(int type) {
        if (type==0){
            return huozhuOut==null?"":huozhuOut.FNumber;
        }else{
            return huozhuOut==null?"":huozhuOut.FOrgID;
        }
    }

    public T_mainDao getT_mainDao() {
        return t_mainDao;
    }

    public T_DetailDao getT_detailDao() {
        return t_detailDao;
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public Gson getGson() {
        return gson;
    }
    public ShareUtil getShare() {
        return share;
    }
    public ScanManager getScanManager() {
        return mCaptureManager;
    }
    public void setScanManager(ScanManager scanManager) {
        mCaptureManager = scanManager;
    }
    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public static void start(Context context, int activity){
        Intent intent = new Intent(context,PagerForActivity.class);
        intent.putExtra("activity",activity);
        context.startActivity(intent);
    }
    public static void start(Context context, int activity, ArrayList<String> fid){
        Intent intent = new Intent(context,PagerForActivity.class);
        intent.putExtra("activity",activity);
        intent.putStringArrayListExtra("fid", fid);
        context.startActivity(intent);
    }
    /**
     * 权限处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (null!=mCaptureManager)mCaptureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
