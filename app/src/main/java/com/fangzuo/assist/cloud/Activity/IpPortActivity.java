package com.fangzuo.assist.cloud.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzuo.assist.cloud.ABase.BaseActivity;
import com.fangzuo.assist.cloud.Activity.Crash.App;
import com.fangzuo.assist.cloud.Beans.UseTimeBean;
import com.fangzuo.assist.cloud.R;
import com.fangzuo.assist.cloud.Utils.BasicShareUtil;
import com.fangzuo.assist.cloud.Utils.Config;
import com.fangzuo.assist.cloud.Utils.Info;
import com.fangzuo.assist.cloud.Utils.Toast;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.fangzuo.assist.cloud.Utils.CommonUtil.dealTime;


public class IpPortActivity extends BaseActivity {


    @BindView(R.id.ed_ip)
    EditText edIp;
    @BindView(R.id.ed_port)
    EditText edPort;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.sp_pda)
    Spinner spPda;
    @BindView(R.id.btn_back)
    RelativeLayout btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_url)
    EditText edUrl;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.ed_printnum)
    EditText edPrintnum;
    private BasicShareUtil share;
    ArrayAdapter<String> adapter;
    private String string;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ip_port);
        mContext = this;
        ButterKnife.bind(this);
        tvTitle.setText(getResources().getString(R.string.server_set));
        share = BasicShareUtil.getInstance(mContext);
        if (!share.getIP().equals("")) {
            edIp.setText(share.getIP());
            edPort.setText(share.getPort());
            edUrl.setText(Hawk.get(Config.Cloud_Url, "http://47.106.179.214/K3Cloud/"));
        } else {
            if (Info.DATABASESETTING.equals("K3DBConfigerRY")) {
                edUrl.setText(Hawk.get(Config.Cloud_Url, "http://47.106.179.214/K3Cloud/"));
                edIp.setText("47.106.179.214");
                edPort.setText("8080");
            } else {
                edUrl.setText("http://192.168.0.201/K3Cloud/");
                edIp.setText("192.168.0.136");
                edPort.setText("8082");
            }
        }
        edPrintnum.setText(Hawk.get(Config.PrintNum,"2"));
    }

    @Override
    protected void initData() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Config.PDA_Type);
        spPda.setAdapter(adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spPda.setSelection(App.PDA_Choose);
            }
        }, 100);

        if (null != Hawk.get(Config.SaveTime, null)) {
            UseTimeBean bean = Hawk.get(Config.SaveTime);
            tvEndtime.setText("有效期：" + dealTime(bean.endTime));
        } else {
            tvEndtime.setText("获取时间失效");
        }
    }

    @Override
    protected void initListener() {
        spPda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                string = adapter.getItem(i);
//                private String[] arr={"G02A设备","8000设备","5000设备"手机端};
                if ("G02A设备".equals(string)) {
                    Hawk.put(Config.PDA, 1);
                    Toast.showText(mContext, "选择了G02A设备" + App.PDA_Choose);
                } else if ("8000设备".equals(string)) {
                    Hawk.put(Config.PDA, 2);
                    Toast.showText(mContext, "选择了8000设备" + App.PDA_Choose);
                } else if ("5000设备".equals(string)) {
                    Hawk.put(Config.PDA, 3);
                    Toast.showText(mContext, "选择了5000设备" + App.PDA_Choose);
                } else if ("手机端".equals(string)) {
                    Hawk.put(Config.PDA, 4);
                    Toast.showText(mContext, "选择了手机端" + App.PDA_Choose);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void OnReceive(String code) {
        Toast.showText(mContext, code);
    }


    @Override
    protected void onDestroy() {
//        App.PDA_Choose = Hawk.get(Config.PDA, 2);
        if (!edUrl.getText().toString().equals("")) {
            Hawk.put(Config.Cloud_Url, edUrl.getText().toString());
        }
        if (!edPrintnum.getText().toString().equals("")) {
            Hawk.put(Config.PrintNum, edPrintnum.getText().toString());
        }
        super.onDestroy();
    }

    @OnClick({R.id.btn_save, R.id.btn_back, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.btn_save:
                if (!edPort.getText().toString().equals("") && !edIp.getText().toString().equals("")) {
                    share.setIP(edIp.getText().toString());
                    share.setPort(edPort.getText().toString());
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
