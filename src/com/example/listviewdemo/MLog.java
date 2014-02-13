package com.example.listviewdemo;

import android.os.Debug;
import android.os.Environment;
import android.util.Log;

public class MLog {
	private static String TAG = tag(MLog.class);
	
	public static final String TRACE = "Trace";

	public static final boolean DEBUG_MODE = true;

	private static final String DEFAULT_TRACE_PATH = Environment
			.getExternalStorageDirectory().getPath() + "/trace/";
	private static final String DEFAULT_TRACE_NAME = "trace.log";

	private static Boolean mLogEnabled;
	private static Boolean mTraceEnabled;

	private static long mLogTime = 0;
	
	private static String mCurrentTraceName;

	static {
		if (DEBUG_MODE) {
			enableLog();
			enableTrace();
		} else {
			disableLog();
			disableTrace();
		}
	}

	public static void d(String tag, String msg) {
		if (mLogEnabled) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Object... args) {
		if (mLogEnabled) {
			try {
				Log.d(tag, String.format(msg, args));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (mLogEnabled) {
			Log.d(tag, msg, tr);
		}
	}

	public static void disableLog() {
		mLogEnabled = false;
	}

	public static void disableTrace() {
		mTraceEnabled = false;
	}

	public static void e(String tag, String msg) {
		Log.e(tag, msg);
	}

	public static void e(String tag, String msg, Object... args) {
		try {
			Log.e(tag, String.format(msg, args));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		Log.e(tag, msg, tr);
	}

	public static void enableLog() {
		mLogEnabled = true;
	}

	public static void enableTrace() {
		mTraceEnabled = true;
	}

	public static void end(String msg) {
		if (mLogEnabled) {
			long cost = System.currentTimeMillis() - mLogTime;
			d(TAG, String.format("%d ms for %s", cost, msg));
		}
	}

	public static void i(String tag, String msg) {
		if (mLogEnabled) {
			Log.i(tag, msg);
		}
	}

	public static void i(String tag, String msg, Object... args) {
		if (mLogEnabled) {
			try {
				Log.i(tag, String.format(msg, args));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (mLogEnabled) {
			Log.i(tag, msg, tr);
		}
	}

	public static void start() {
		mLogTime = System.currentTimeMillis();
	}

	/**
	 * The trace file will be put under {@value #DEFAULT_TRACE_PATH} unless an
	 * absolute path is given.
	 * 
	 * @param traceName
	 *            Name for the trace log file to create. This could be a simple
	 *            name, or a absolute path of trace file name. If @param
	 *            traceName is empty, the trace file will be
	 *            DEFAULT_TRACE_PATH/dmtrace.trace
	 */
	public static void startTrace(String traceName) {
		if (mTraceEnabled) {
			mCurrentTraceName = traceName;

			if (mCurrentTraceName == "") {
				mCurrentTraceName = DEFAULT_TRACE_PATH + DEFAULT_TRACE_NAME;
			} else if (!mCurrentTraceName.startsWith("/")) {
				mCurrentTraceName = DEFAULT_TRACE_PATH + mCurrentTraceName;
			}

			Debug.startMethodTracing(mCurrentTraceName);
		}
	}

	public static void stopTrace() {
		if (mTraceEnabled) {
			Debug.stopMethodTracing();
			d(TRACE, "trace file is generated at /sdcard/%s", mCurrentTraceName);
		}
	}

	public static String tag(Class<?> clazz) {
		return clazz.getName();
	}

	public static void v(String tag, String msg) {
		if (mLogEnabled) {
			Log.v(tag, msg);
		}
	}

	public static void v(String tag, String msg, Object... args) {
		if (mLogEnabled) {
			try {
				Log.v(tag, String.format(msg, args));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (mLogEnabled) {
			Log.v(tag, msg, tr);
		}
	}

	public static void w(String tag, String msg) {
		if (mLogEnabled) {
			Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Object... args) {
		if (mLogEnabled) {
			try {
				Log.w(tag, String.format(msg, args));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (mLogEnabled) {
			Log.w(tag, msg, tr);
		}
	}
}
