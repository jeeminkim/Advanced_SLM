package com.example.kang.limine;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import static android.nfc.NfcAdapter.getDefaultAdapter;

public class Parking extends URLConnect {
    String myJSON;
    JSONArray peoples = null;

    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    String nfcid, id, myResult;
    TextView tagDesc;
    private int check = 0;
    String Cname,Cid;
    TextView textView;


    private static final String TAG_RESULTS="result";
    private static final String TAG_ID="id";
    private static final String TAG_INH="inh";
    private static final String TAG_INM="inm";
    private static final String TAG_INS="ins";
    private static final String TAG_OUTH="outh";
    private static final String TAG_OUTM="outm";
    private static final String TAG_OUTS="outs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.enableDefaults();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        textView = (TextView)findViewById(R.id.textViewa);
        tagDesc = (TextView) findViewById(R.id.tagDesc);
        nfcAdapter = getDefaultAdapter(this);

        Cursor c = sql.rawQuery("select name,id From " + TABLENAME, null);
        int count = c.getCount();
        for (int i = 0; i < count; i++) {
            c.moveToNext();
            Cname = c.getString(0);
            Cid = c.getString(1);
        }

        if(nfcAdapter == null)
        {
            Toast.makeText(this, "NFC를 지원하지 않는 단말기입니다.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        id = getDeviceSerialNumber();


    }
    private static String getDeviceSerialNumber() {

        try {
            return (String) Build.class.getField("SERIAL").get(null);
        } catch (Exception ignored) {
            return null;
        }
    }
    @Override
    protected void onPause() {
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
        }
    }
    private void AddData(String id) {

        class addData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPreExecute();
                //loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String id= (String) params[0];


                    //String link = urljoin;
                    String link = urlnfcin;
                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("doInBackground message : " + e.getMessage());
                }
            }
        }
        addData task = new addData();
        task.execute(id);
    } //nfc in
    private void AddData3(String id) {

        class addData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPreExecute();
                //loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String id= (String) params[0];


                    //String link = urljoin;
                    String link = urlnfcouttime;
                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("doInBackground message : " + e.getMessage());
                }
            }
        }
        addData task = new addData();
        task.execute(id);
    } //nfc outtime
    private void AddData2(String id) {

        class addData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPreExecute();
                //loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String id= (String) params[0];


                    //String link = urljoin;
                    String link = urlnfcintime;
                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("doInBackground message : " + e.getMessage());
                }
            }
        }
        addData task = new addData();
        task.execute(id);
    } //nfc intime
    private void AddData1(String id) {

        class addData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
                //loading = ProgressDialog.show(Paint.Join.this, "잠시만 기다려 주세요", null, true, true) ;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPreExecute();
                //loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                try {
                    String id= (String) params[0];


                    //String link = urljoin;
                    String link = urlnfcout;
                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;


                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("doInBackground message : " + e.getMessage());
                }
            }
        }
        addData task = new addData();
        task.execute(id);
    } //nfc out
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if (tag != null) {
            byte[] tagId = tag.getId();
            nfcid = toHexString(tagId);
            try {
                if (nfcid.equals("042CCF5AD44880") && check == 0 ) {  // 출구태그후 입구태그시 ( 처음 출입시 )( 정상 )

                    tagDesc.setText(Cname + " 고객님 입장되었습니다~!");
                    HttpThread thread = new HttpThread();
                    thread.start();

                    AddData(Cid);
                    AddData2(Cid);
                    getData(urlnfcintime);


                    Toast.makeText(Parking.this, "환영합니다 "+Cname+"고객님!! 입장하셨습니다!!", Toast.LENGTH_SHORT).show();

                    /*Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();*/
                    check = 1 ;
                }

                else if(nfcid.equals("047ECB5AD44881") && check == 1 ) {  // 입구태그후 출구태그시 ( 퇴장시 )( 정상 )
                    tagDesc.setText(Cname + " 고객님 퇴장되었습니다~!");
                    HttpThread thread = new HttpThread();
                    thread.start();
                    AddData1(Cid);
                    AddData3(Cid);
                    getData1(urlnfcouttime);
                    Toast.makeText(Parking.this, "감사합니다  "+Cname +"고객님!! 퇴장하셨습니다!!", Toast.LENGTH_SHORT).show();

                    /*Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);   //이하 3줄 -
                    startActivity(intent1);
                    finish();*/
                    check = 0;
                }

                else if(nfcid.equals("042CCF5AD44880") && check == 1 ) {  // 입구태그 후 입구에 다시 태그할 경우 ( 비정상 )
                    tagDesc.setText("죄송합니다. 잘못된 지역입니다. 출구로 가주시기 바랍니다.");
                    HttpThread thread = new HttpThread();
                    thread.start();

                    Toast.makeText(Parking.this, "죄송합니다. 잘못된 지역입니다. 출구로 가주시기 바랍니다.", Toast.LENGTH_SHORT).show();

                    /*Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();*/
                }

                else if(nfcid.equals("047ECB5AD44881") && check == 0 ) {  // 출구태그 후 출구에 다시 태그할 경우 ( 비정상 )
                    tagDesc.setText("죄송합니다. 잘못된 지역입니다. 입구로 가주시기 바랍니다.");
                    HttpThread thread = new HttpThread();
                    thread.start();

                    Toast.makeText(Parking.this, "죄송합니다. 잘못된 지역입니다. 입구로 가주시기 바랍니다.", Toast.LENGTH_SHORT).show();

                    /*Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();*/
                }

            }

            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class HttpThread extends Thread{
        public void run(){
            mHandler.sendEmptyMessage(0);
        }
    }
    public void senddata(String pin){

    }
    Handler mHandler = new Handler() {

        // 핸들러가 호출되면 이쪽 함수가 실행됩니다.

        public void handleMessage(Message msg)
        {

            if(msg.what == 0)
            {
                // 여기다가 코딩
                StringBuffer buffer = new StringBuffer();
                try{
                    URL url = new URL("rkdtjdwn.iptime.org/nfc/nfc.php");
                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("POST");                                          // 전송 방식은 POST
                    http.setDoInput(true);                                                        // 서버에서 읽기 모드 지정
                    http.setDoOutput(true);                                                       // 서버로 쓰기 모드 지정
                    http.setDefaultUseCaches(false);
                    http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                    buffer.append("id").append("=").append(id).append("&");
                    buffer.append("nfcid").append("=").append(nfcid);

                    OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                    PrintWriter writer = new PrintWriter(outStream);
                    writer.write(buffer.toString());
                    writer.flush();
                    InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "utf-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuilder builder = new StringBuilder();


                    myResult = builder.toString();                       // 전송결과를 전역 변수에 저장

                    Toast.makeText(Parking.this, "수업 등록 완료", Toast.LENGTH_LONG).show();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    };

    public static final String CHARS = "0123456789ABCDEF";

    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; ++i) {
            sb.append(CHARS.charAt((data[i] >> 4) & 0x0F))
                    .append(CHARS.charAt(data[i] & 0x0F));
        }
        return sb.toString();
    }
    public void getData1(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList1();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
    protected void showList1(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++) {

                JSONObject c = peoples.getJSONObject(i);

                String id = c.getString(TAG_ID);
                String outh = c.getString(TAG_OUTH);
                String outm = c.getString(TAG_OUTM);
                String outs = c.getString(TAG_OUTS);
                textView.append("입장시간 : "+outh +" 시"+outm+" 분" +outs+ "초");
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }

    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++) {

                JSONObject c = peoples.getJSONObject(i);

                String id = c.getString(TAG_ID);
                String inh = c.getString(TAG_INH);
                String inm = c.getString(TAG_INM);
                String ins = c.getString(TAG_INS);

                textView.append("입장시간 : "+inh +" 시"+inm+" 분" +ins+ "초");
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}