package com.cse110.apk404.myCalendar.eventListHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PostData {

    class ByteData {
        byte[] data;
        String header;
        String name;

        ByteData(String name, String contentType, byte[] data) {
            this.name = name;
            this.data = data;
            try {
                header = "--" + BOUNDARY + CRLF + "Content-Disposition: form-data; name=\"file\"; filename=\"" + URLEncoder.encode(name, encoding) + "\";" + CRLF +
                        "Content-Type: " + contentType + CRLF + CRLF;
            } catch(UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        public int getSize() {
            return header.length() + data.length + CRLF.length();
        }


        public void write(OutputStream out) throws IOException {
            out.write(header.getBytes());
            out.write(data);
            out.write(CRLF.getBytes());
        }
    }

    private static final String TAG = PostData.class.getSimpleName();
    static final String BOUNDARY = "3C3F786D6C2076657273696F6E2E302220656E636F64696E673D662D38223F3E0A3C6D616E6966";
    static final String CRLF = "\r\n";
    private final String encoding;
    private StringBuilder sb;
    private String trailer;
    private List<ByteData> dataList = new ArrayList<ByteData>();


    public PostData() {
        this("UTF-8");
    }

    public PostData(String encoding) {
        this.encoding = encoding;
        sb = new StringBuilder();
        trailer = "--" + BOUNDARY + "--" + CRLF;
    }

    public String getContentType() {
        return "multipart/form-data; boundary=" + BOUNDARY;
    }

    public void addValue(String name, int value) {
        addValue(name, Integer.toString(value));
    }

    public void addValue(String name, String value) {
        sb.append("--" + BOUNDARY + CRLF);
        sb.append("Content-Disposition: form-data; name=\"");
        try {
            sb.append(URLEncoder.encode(name, encoding));
            sb.append('"');
            sb.append(CRLF + CRLF);
            sb.append(value);
            sb.append(CRLF);
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void addData(String name, String contentType, byte[] data) {
        dataList.add(new ByteData(name, contentType, data));
    }


    public long getLength() {
        long length = sb.toString().getBytes().length;
        length += trailer.length();
        for(ByteData byteData : dataList)
            length += byteData.getSize();
        return length;
    }

    public String toString() {
        return sb.toString();
    }

    public void write(OutputStream out) throws IOException {
        out.write(sb.toString().getBytes());
        for(ByteData byteData : dataList)
            byteData.write(out);
        out.write(trailer.getBytes());
    }
}