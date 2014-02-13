package com.example.listviewdemo.data;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.listviewdemo.MLog;
import com.example.listviewdemo.schema.GoogleImageSearchResponse;
import com.google.gson.Gson;

public class API {
	public static String TAG = MLog.tag(API.class);

	public static String SEARCH_URL = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=";

	public static GoogleImageSearchResponse searchImage(String keyword)
			throws ClientProtocolException, IOException {
		String url = SEARCH_URL + keyword;

		// using native way to send request, caller should use RxJava to
		// call this method asynchronously
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String body = EntityUtils.toString(entity);

		Gson gson = new Gson();
		GoogleImageSearchResponse pool = gson.fromJson(body,
				GoogleImageSearchResponse.class);

		return pool;
	}

	public static Observable<GoogleImageSearchResponse> getImageSearchFetcher(
			final String url) {
		return Observable.create(
				new Observable.OnSubscribeFunc<GoogleImageSearchResponse>() {
					@Override
					public Subscription onSubscribe(
							Observer<? super GoogleImageSearchResponse> observer) {
						try {
							for (int i = 0; i < 3; i++) {
								String pageUrl = String.format(
										"%s&rsz=8&start=%d", url, 8 * i);
								observer.onNext(API.searchImage(pageUrl));
							}
							observer.onCompleted();
						} catch (Exception e) {
							observer.onError(e);
						}

						return Subscriptions.empty();
					}
				}).subscribeOn(Schedulers.io());
	}

	public static Bitmap getImage(String url) throws ClientProtocolException,
			IOException {
		MLog.d(TAG, "getting " + url);
		// using native way to send request, caller should use RxJava to
		// call this method asynchronously
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		byte[] content = EntityUtils.toByteArray(entity);
		return BitmapFactory.decodeByteArray(content, 0, content.length);
	}

	public static Observable<ListItemImage> getImageFetcher(
			final List<String> urls) {
		return Observable.create(
				new Observable.OnSubscribeFunc<ListItemImage>() {
					@Override
					public Subscription onSubscribe(
							Observer<? super ListItemImage> observer) {
						try {
							for (String url : urls) {
								ListItemImage itemImage = new ListItemImage();
								itemImage.url = url;
								itemImage.bitmap = API.getImage(url);
								observer.onNext(itemImage);
							}
							observer.onCompleted();
						} catch (Exception e) {
							observer.onError(e);
						}

						return Subscriptions.empty();
					}
				}).subscribeOn(Schedulers.io());
	}
}
