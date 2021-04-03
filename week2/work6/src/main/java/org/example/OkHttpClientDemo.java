package org.example;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpClientDemo {

  static OkHttpClient client = new OkHttpClient();

  public static void main(String[] args) {
    String url = "http://localhost:8801";
    try {
      Request request = new Request.Builder()
          .url(url)
          .get()
          .build();
      try (Response response = client.newCall(request).execute()) {
        System.out.println(response.body().string());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
