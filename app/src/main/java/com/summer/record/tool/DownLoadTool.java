package com.summer.record.tool;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.DownloadListener;

import com.blankj.utilcode.util.FileUtils;
import com.summer.record.data.net.Net;
import com.summer.x.base.i.OnProgressI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownLoadTool {

    private static final String TAG = "DownloadUtil";
    //视频下载相关
    private Call<ResponseBody> mCall;
    private File mFile;
    private Thread mThread;

    public DownLoadTool() {
    }

    public void downloadFile(String url,String path, final OnProgressI progressI) {
        progressI.onProgress("downloadFile",OnProgressI.PREPARE,url);
        String name = url;
        mFile = new File(path);
        if(!mFile.getParentFile().exists()){
            mFile.getParentFile().mkdirs();
        }
        mCall = Net.getInstance().download(url);
        mCall.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... strings) {
                        long currentLength = 0;
                        OutputStream os = null;
                        if(response.body()==null){
                            return null;
                        }
                        InputStream is = response.body().byteStream(); //获取下载输入流
                        long totalLength = response.body().contentLength();
                        try {
                            os = new FileOutputStream(mFile); //输出流
                            int len;
                            byte[] buff = new byte[1024];
                            while ((len = is.read(buff)) != -1) {
                                os.write(buff, 0, len);
                                currentLength += len;
                                publishProgress((100 * currentLength / totalLength)+"");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        } finally {
                            if (os != null) {
                                try {
                                    os.close(); //关闭输出流
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (is != null) {
                                try {
                                    is.close(); //关闭输入流
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return mFile.getPath();
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        progressI.onProgress("downloadFile",s==null?OnProgressI.ERROR:OnProgressI.SUCCESS,s);
                        progressI.onProgress("downloadFile",OnProgressI.END,url);
                    }

                    @Override
                    protected void onProgressUpdate(String... values) {
                        super.onProgressUpdate(values);
                        progressI.onProgress("downloadFile",OnProgressI.DOING,values[0]);
                    }
                }.execute();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressI.onProgress("downloadFile",OnProgressI.ERROR,url);
                progressI.onProgress("downloadFile",OnProgressI.END,url);
            }
        });
    }
}
