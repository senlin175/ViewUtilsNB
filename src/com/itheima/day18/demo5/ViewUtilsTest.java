package com.itheima.day18.demo5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class ViewUtilsTest {
	
	public static void inject(Activity avtivity) {
		bindFiled(avtivity);
		bindButten(avtivity);
	}
	private static void bindButten(final Activity avtivity2) {
		Class<? extends Activity> clazz = avtivity2.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (final Method method : methods) {
			Onclick onclick = method.getAnnotation(Onclick.class);
			if (onclick!=null) {
				int[] rstIds = onclick.value();
				for (int i : rstIds) {
					method.setAccessible(true);
					View view = avtivity2.findViewById(i);
					view.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							method.setAccessible(true);
							try {
								method.invoke(avtivity2, v);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		}
		
	}
	private static void bindFiled(Activity avtivity2){
		Class<? extends Activity> clazz = avtivity2.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			ViewInject viewInject = field.getAnnotation(ViewInject.class);
			if (viewInject!=null) {
				int resId = viewInject.value();
				View view = avtivity2.findViewById(resId);
				field.setAccessible(true);
				try {
					field.set(avtivity2, view);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	
}
