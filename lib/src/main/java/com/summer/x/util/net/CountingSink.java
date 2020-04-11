package com.summer.x.util.net;

import com.summer.x.base.i.OnProgressI;
import com.summer.x.util.HandleUtil;

import java.io.IOException;

import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;

public class CountingSink extends ForwardingSink {

    private long bytesWritten = 0;

    OnProgressI onProgressI;

    long contentLength;

    public CountingSink(Sink delegate, long contentLength, OnProgressI onProgressI) {
        super(delegate);
        this.onProgressI = onProgressI;
        this.contentLength = contentLength;
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {
        super.write(source, byteCount);

        bytesWritten += byteCount;
        if(onProgressI!=null){
            HandleUtil.getInstance().post(new Runnable() {
                @Override
                public void run() {
                    onProgressI.onProgress("CountingSink", OnProgressI.DOING,bytesWritten*100/contentLength);
                }
            });
        }
    }
}