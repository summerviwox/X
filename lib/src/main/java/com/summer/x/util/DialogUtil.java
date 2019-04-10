package com.summer.x.util;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;



public class DialogUtil{

	AlertDialog alertDialog;

	//显示自定义弹框
	public void showDialog(Context context, View view, View.OnClickListener listener,int... id){
		Builder builder = new Builder(context);
		alertDialog = builder.create();
		alertDialog.setCancelable(true);
		alertDialog.show();
		alertDialog.getWindow().setContentView(view);
		alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		for(int i=0;i<id.length;i++){
			view.findViewById(id[i]).setOnClickListener(listener);
		}
	}


	//显示自定义弹框
	public void dismissDialog(){
		 if(alertDialog!=null){
			 alertDialog.dismiss();
			 alertDialog=null;
		 }
	}
}
