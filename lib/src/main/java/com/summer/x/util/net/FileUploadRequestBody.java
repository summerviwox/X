package com.summer.x.util.net;

import com.blankj.utilcode.util.LogUtils;
import com.summer.x.base.i.OnProgressI;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

public class FileUploadRequestBody extends RequestBody {

    RequestBody requestBody;

    OnProgressI onProgressI;

    public FileUploadRequestBody(MediaType contentType, final File file, OnProgressI onProgressI){
        this.requestBody = RequestBody.create(contentType,file);
        try {
            LogUtils.e(requestBody.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.onProgressI = onProgressI;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        CountingSink countingSink = new CountingSink(sink,requestBody.contentLength(),onProgressI);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        //写入
        requestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }
}
