# FragmentDemo
#### Fragment页面切换管理

### 1、Activity加载或切换 Fragment页面，有两种方式add()、replace()，最后都调用doAddOp()方法，仅做了标记

```
1、 add(@IdRes int containerViewId, @NonNull Fragment fragment)
2、 replace(@IdRes int containerViewId, @NonNull Fragment fragment)

void doAddOp(int containerViewId, Fragment fragment, @Nullable String tag, int opcmd)
```

### 2、BaseActivity 继承自 FragmentHold，FragmentHold提供Fragment集合管理，
### 并提供三个回退Fragment的方法

```
1、弹出当前Fragment页面
public void pop();

2、弹出到指定压栈页面（注意：如果Class没有，将退回栈顶）
public void popTo(Class clazz);

3、弹出到栈顶
public void popToTop();
```

### 3、BaseFragment 在 onAttach(context) 方法中获取承载的 BaseActivity，用于调用BaseActivity定义的管理Fragment的方法

```
@Override
public void onAttach(@NonNull Context context) {
	super.onAttach(context);
	//requireActivity() 返回的是宿主activity
	requireActivity().getLifecycle().addObserver(new LifecycleEventObserver() {
		@Override
		public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
			if (event.getTargetState() == Lifecycle.State.CREATED){
				//在这里任你飞翔
				Log.i(">>>boylab>>>", "onStateChanged: 什么时候执行2");
				mActivity = (BaseActivity) requireActivity();
				initData();

				requireActivity().getLifecycle().removeObserver(this);  //这里是删除观察者
			}
		}
	});
}
```

### 4、Fragment页面切换回调数据

```
1、调用addForResult()、replaceForResult()两个方法，需要requestKey, FragmentResultListener两个参数，FragmentResultListener监听回调参数

public void addForResult(BaseFragment fragment, String requestKey, FragmentResultListener listener) {
	mActivity.addForResult(fragment, requestKey, listener);
}

public void replaceForResult(BaseFragment fragment, String requestKey, FragmentResultListener listener) {
	mActivity.replaceForResult(fragment, requestKey, listener);
}

2、返回之前，调用setFragmentResult()方法，通过requestKey回传参数

public void setFragmentResult(@NonNull String requestKey, @NonNull Bundle result)

3、特性：Fragment页面切换，可以做到跨多个页面，进行参数回调，且以回到页面前最后一次调用setFragmentResult()方法为准
```

### 5、Fragment页面跳转到Activity页面回调数据，(次方法同样适用Activity之间跳转回调)

```
1、在Fragment页面创建 ActivityResultLauncher 对象，
private ActivityResultLauncher activityLauncher = registerForActivityResult(new ResultContract(), new ActivityResultCallback<ResultContract.ResultBean>(){

	@Override
	public void onActivityResult(ResultContract.ResultBean result) {
		//Log.i(">>>boylab>>>", "onActivityResult: result = "+result);
		if (result != null){
			text_Activity.setText(result.toString());
		}
	}
});

2、通过 ActivityResultLauncher 启动新Activity页面
Intent intent = new Intent(getContext(), SecondActivity.class);
activityLauncher.launch(intent);

3、特性：次方式只能进行两个页面之间数据回调，不能进行跨多页面回调

```

|  类型   | 方法  | 方法  |
|--------------------------------------------|---------------------------------------------------|---------------------------------------------|
| abstract <br />ActivityResultContract<I, ?>| getContract()									 | Get the {@link ActivityResultContract} <br/>that was used to create this launcher. |
| abstract void								 | unregister()									 	 | Unregisters this launcher, <br/>releasing the underlying result callback, <br/>and any references captured within it. |
| abstract void								 | launch(I input,<br/>ActivityOptionsCompat options)| Executes an {@link ActivityResultContract}. |
| void										 | launch(I input)									 | Executes an {@link ActivityResultContract}. |


类型	方法	描述
Get the {@link ActivityResultContract} that was used to create this launcher.
Unregisters this launcher, releasing the underlying result callback, and any references captured within it.
Executes an {@link ActivityResultContract}.
Executes an {@link ActivityResultContract}.


### 6、注意：在使用ActivityResultLauncher跳转页面并回调时，如果点击按键返回，回调的数据会出现空指针，所以需要做判空处理。


### 7、拓展：原始Fragment跳转Activity 页面数据回调思路，

```
1、Activity 复写onActivityResult(),然后Activity分发并调用 Fragment中 onActivityResult()方法，
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	getmFragment().onActivityResult(requestCode, resultCode, data);
}

2、Fragment中的 onActivityResult() 用来处理Activity数据回调

@Override
public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
}

3、在Fragment页面中， 通过承载Fragment的 Activity调用 startActivityForResult() 进行跳转

4、Fragment中的 startActivityForResult()、onActivityResult()都弃用了

```