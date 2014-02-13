package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.observables.AndroidObservable;

import com.example.listviewdemo.data.API;
import com.example.listviewdemo.data.ListItemImage;
import com.example.listviewdemo.schema.GoogleImageSearchResponse;
import com.example.listviewdemo.schema.Result;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchResultAdapter extends ArrayAdapter<Result> {
	private final Activity mContext;
	private List<Result> mData = new ArrayList<Result>();

	private Subscription mAdapterDataSubscription;

	private Subscription mListItemImageSubscription;

	// TODO: replace with FIFO Cache for better memory performance here
	private Map<String, Bitmap> mImages = new HashMap<String, Bitmap>();

	static class ViewHolder {
		public TextView title;
		public TextView content;
		public ImageView image;
	}

	public SearchResultAdapter(Activity context) {
		super(context, R.layout.listitem_searchresult);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	public void doSearch(String keyword) {
		mData.clear();
		mImages.clear();

		Observable<GoogleImageSearchResponse> mImageSerachObservable = API
				.getImageSearchFetcher(API.SEARCH_URL);
		mAdapterDataSubscription = AndroidObservable.fromActivity(mContext,
				mImageSerachObservable).subscribe(mAdapterDataObserver);

		// mImageSerachObservable.subscribe();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			view = inflater.inflate(R.layout.listitem_searchresult, null);
			ViewHolder viewHolder = new ViewHolder();
			viewHolder.title = (TextView) view
					.findViewById(R.id.search_result_title);
			viewHolder.content = (TextView) view
					.findViewById(R.id.search_result_content);
			viewHolder.image = (ImageView) view
					.findViewById(R.id.search_result_image);
			view.setTag(viewHolder);
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		Result r = mData.get(position);
		holder.title.setText(r.getTitleNoFormatting());
		holder.content.setText(r.getContentNoFormatting());

		String url = mData.get(position).getUrl();
		if (mImages.get(url) != null)
			holder.image.setImageBitmap(mImages.get(url));

		return view;
	}

	private Observer<GoogleImageSearchResponse> mAdapterDataObserver = new Observer<GoogleImageSearchResponse>() {
		@Override
		public void onCompleted() {
			mAdapterDataSubscription.unsubscribe();

			// initialize and launch image download process
			List<String> urls = new ArrayList<String>(mData.size());
			for (Result r : mData) {
				String url = r.getUrl();
				if (!mImages.containsKey(url)) {
					urls.add(url);
					mImages.put(url, null);
				}
			}

			Observable<ListItemImage> mImageFetcherObservable = API
					.getImageFetcher(urls);
			mListItemImageSubscription = AndroidObservable.fromActivity(
					mContext, mImageFetcherObservable).subscribe(
					mListItemImageObserver);
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
		}

		@Override
		public void onNext(GoogleImageSearchResponse response) {
			List<Result> results = response.getResponseData().getResults();
			mData.addAll(results);
			notifyDataSetChanged();
		}
	};

	private Observer<ListItemImage> mListItemImageObserver = new Observer<ListItemImage>() {
		@Override
		public void onCompleted() {
			// mListItemImageSubscription.unsubscribe();
		}

		@Override
		public void onError(Throwable e) {
			e.printStackTrace();
		}

		@Override
		public void onNext(ListItemImage itemImage) {
			mImages.put(itemImage.url, itemImage.bitmap);
			notifyDataSetChanged();
		}
	};

}
