package com.example.easyenglish;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.DBManager.DBTools;
import com.example.MyAdapter.ExampleAdapter;
import com.example.UserHleper.NEMT;
import com.example.UserHleper.Values;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

public class Worditempage extends Activity implements OnClickListener {

	ExampleAdapter adapter;
	private TextView wordphono;
	private TextView wordkey;
	private TextView pagenum;
	private List<NEMT> worddetail;
	private List<NEMT> unitwords;
	private TextView wordtrans;
	private TextView wordexample;
	private Button btnwordpre;
	private Button btnwordnext;
	private Button btnwordplay;
	private Button btnspeek;
	private Button btnback;
	private Button setting;

	private SeekBar seekbarspeed;
	private SeekBar seekbarvolume;
	private String voicer = "xiaoyan";
	private String wid;
	private String tablename;
	private String sum;
	private String p;
	private int pos;
	private boolean isplay = false;
	private boolean playnext = false;
	private boolean pause = false;
	private AlertDialog d;
	private View dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.worddetial);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.detialtitlebar);

		Typeface myface = Typeface.createFromAsset(getAssets(),
				"font/SEGOEUI.TTF");
		findViews();
		wordphono.setTypeface(myface);
		final String[] str = getIntent().getStringArrayExtra("word");
		tablename = str[0];
		wid = str[1];
		sum = str[2];
		p = str[3];

		pos = Integer.parseInt(p);
		worddetail = getStrings(tablename, wid);
		unitwords = getNPString(tablename);
		setText(worddetail);
		btnback.setOnClickListener(this);
		btnwordplay.setOnClickListener(this);
		btnwordnext.setOnClickListener(this);
		btnwordpre.setOnClickListener(this);
		setting.setOnClickListener(this);
		btnspeek.setOnClickListener(this);

	}

	// 点击事件
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnwordplay:
				if (!isplay) { // 初始值isplay=false，若此前状态未播放，变换按钮
					isplay = true;
					playnext = true;
					pause = false;
					if (!pause) { // pause初始值为false 判断是否未暂停，若非暂停状态,开始播放
						playWords();
					} else { // 若是暂停状态，继续播放
						SpeechSynthesizer.getSynthesizer().resumeSpeaking();
					}
					btnwordplay.setBackgroundResource(R.mipmap.btn_wordpause);
				} else {
					playnext = false;
					isplay = false;
					pause = true;
					SpeechSynthesizer.getSynthesizer().pauseSpeaking();
					btnwordplay.setBackgroundResource(R.mipmap.btn_wordplay);
				}
				break;
			case R.id.btnwordpre:
				isplay=false;
				playnext=false;
				if (pos > 1) {
					p = pos - 1 + "";
					pos = Integer.parseInt(p);
					resetText(unitwords, pos - 1, p);
					btnwordplay.setBackgroundResource(R.mipmap.btn_wordplay);
				}
				break;
			case R.id.btnwordnext:
				isplay=false;
				playnext=false;
				nextPlay();
				btnwordplay.setBackgroundResource(R.mipmap.btn_wordplay);
				break;
			case R.id.detialback:
				playnext = false;
				isplay=false;
				if (SpeechSynthesizer.getSynthesizer() != null) {
					SpeechSynthesizer.getSynthesizer().destroy();
				}
				Worditempage.this.finish();
				break;
			case R.id.setting:
				if (d == null) {
					d = new AlertDialog.Builder(this)
							.setTitle("设置")
							.setView(dialog)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface arg0,
															int arg1) {
											Values.SPEED = seekbarspeed
													.getProgress() + "";
											Values.VOLUME = seekbarvolume
													.getProgress() + "";
										}
									}).show();
//				Window window=d.getWindow();
//				window.setContentView(dialog);
				}
				d.show();
				break;
			case R.id.btnspeek:
				playnext=false;
				playWords();
				break;
			default:
				break;
		}
	}

	// 添加控件
	private void findViews() {
		wordkey = (TextView) findViewById(R.id.wordkey);
		pagenum = (TextView) findViewById(R.id.pagenum);
		wordphono = (TextView) findViewById(R.id.wordphono);
		wordtrans = (TextView) findViewById(R.id.wordtrans);
		wordexample = (TextView) findViewById(R.id.wordexample);
		btnwordplay = (Button) findViewById(R.id.btnwordplay);
		btnwordnext = (Button) findViewById(R.id.btnwordnext);
		btnwordpre = (Button) findViewById(R.id.btnwordpre);
		btnspeek = (Button) findViewById(R.id.btnspeek);
		findViewById(R.id.btnspeek);
		findViewById(R.id.imgexample);
		findViewById(R.id.imgtran);

		View view = getWindow().getDecorView();
		btnback = (Button) view.findViewById(R.id.detialback);
		setting = (Button) view.findViewById(R.id.setting);

		LayoutInflater layoutinflater = LayoutInflater.from(this);
		dialog = layoutinflater.inflate(R.layout.items, null);

		dialog.findViewById(R.id.seekbartext);
		dialog.findViewById(R.id.seekbartext1);
		seekbarspeed = (SeekBar) dialog.findViewById(R.id.speedseek);
		seekbarvolume = (SeekBar) dialog.findViewById(R.id.volumeseek);
	}

	// 重新设置控件文本，返回键右边
	private void resetText(List<NEMT> worddetail, int datap, String p) {
		wordkey.setText(worddetail.get(datap).getWord_key());
		wordphono.setText(worddetail.get(datap).getWord_phono());
		wordtrans.setText(worddetail.get(datap).getWord_trans());
		pagenum.setText(p + "/" + sum);
		String example = worddetail.get(datap).getWord_example();

		final Pattern highlight = Pattern.compile(worddetail.get(datap)
				.getWord_key());

		SpannableString s = new SpannableString(example);
		Matcher m = highlight.matcher(s.toString());
		while (m.find()) {
			s.setSpan(new ForegroundColorSpan(Color.BLUE), m.start(), m.end(),
					Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			wordexample.setText(s);
		}
	}

	// 设置控件文本，返回键右边
	private void setText(List<NEMT> worddetail) {
		wordkey.setText(worddetail.get(0).getWord_key());
		wordphono.setText(worddetail.get(0).getWord_phono());
		wordtrans.setText(worddetail.get(0).getWord_trans());
		pagenum.setText(p + "/" + sum);
		String example = worddetail.get(0).getWord_example();

		final Pattern highlight = Pattern.compile(worddetail.get(0)
				.getWord_key());
		SpannableString s = new SpannableString(example);
		Matcher m = highlight.matcher(s.toString());
		while (m.find()) {
			// s.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), m.start(),
			// m.end(),
			// Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			s.setSpan(new ForegroundColorSpan(Color.BLUE), m.start(), m.end(),
					Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			wordexample.setText(s);
		}
	}

	// 播放
	private void playWords() {
		// unitwords=getNPString(tablename);
		speech(unitwords.get(pos - 1).getWord_key());
	}

	private void nextPlay() {
		if (pos < unitwords.size() && pos >= 1) {
			p = pos + 1 + "";
			pos = Integer.parseInt(p);
		}
		resetText(unitwords, pos - 1, p);
	}

	private List<NEMT> getStrings(String tablename, String wid) {
		DBTools dbt = new DBTools();
		worddetail = dbt.getNMET(Worditempage.this, tablename, wid);
		return worddetail;
	}

	private List<NEMT> getNPString(String tablename) {
		DBTools dbt = new DBTools();
		unitwords = dbt.getWordPreorNext(this, tablename, Values.POSTION);
		return unitwords;
	}

	private InitListener mInitListener = new InitListener() {

		@Override
		public void onInit(int arg0) {
		}
	};

	public void speech(String str) {
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=54ba0f59");
		SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(this,
				mInitListener);
		mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);// 设置发音人
		mTts.setParameter(SpeechConstant.SPEED, Values.SPEED);// 设置语速
		mTts.setParameter(SpeechConstant.VOLUME, Values.VOLUME);// 设置音量，范围0~100
		mTts.startSpeaking(str, mSynListener);// 3.开始合成

	}

	Handler handler = new Handler();;
	// 合成监听器
	private SynthesizerListener mSynListener = new SynthesizerListener() {
		//
		public void onCompleted(SpeechError error) {
			if (pos >= unitwords.size()) {
				playnext=false;
				isplay = false;
				btnwordplay.setBackgroundResource(R.mipmap.btn_wordplay);
				SpeechSynthesizer.getSynthesizer().destroy();
			}
			if (playnext) {
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						if(isplay){
							nextPlay();
							playWords();}
					}
				}, 3000);//三秒后切换下一个单词
			}
		}

		// 缓冲进度回调.percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
		public void onBufferProgress(int percent, int beginPos, int endPos,
									 String info) {
		}

		// 开始播放
		public void onSpeakBegin() {
//			btnwordplay.setBackgroundResource(R.mipmap.btn_wordpause);
		}

		// 暂停播放
		public void onSpeakPaused() {
		}

		// 播放进度回调
		// percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
		public void onSpeakProgress(int percent, int beginPos, int endPos) {
//			 mPercentForPlaying = percent;

		}

		// 恢复播放回调接口
		public void onSpeakResumed() {
			btnwordplay.setBackgroundResource(R.mipmap.btn_wordpause);
		}

		// 会话事件回调接口
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
		}
	};

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		playnext = false;
		isplay=false;
		if (SpeechSynthesizer.getSynthesizer() != null) {
			SpeechSynthesizer.getSynthesizer().destroy();
		}
	}
}
