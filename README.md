![Screen1](/images/ic_launcher.png)

# Uberdriver: 利用BottomSheetBehavior实现Uber的2个界面

## 界面如下:

<img src="/images/enframe_2016-09-17-17-11-47.png" width="525" height="925" />

<img src="/images/enframe_2016-09-17-17-11-43.png" width="525" height="925" />

<img src="/images/enframe_2016-09-17-17-11-37.png" width="525" height="925" />

<img src="/images/enframe_2016-09-17-17-11-19.png" width="525" height="925" />


## BottomSheetBehavior使用简介

> Design Support Library提供了很多Material Design风格的控件,已经新的动画效果,比如在23.2.0+以后新加的
android.support.design.widget.BottomSheetBehavior可以轻松的在自己的应用中添加bottom sheet.

1. 在布局文件里面定义填充BottomSheetBehavior的view,改view的父布局应该是android.support.design.widget.CoordinatorLayout:

```

<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="me.chunsheng.uberdriver.MainActivity">

    <ImageView
        android:id="@+id/map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/black"
        android:contentDescription="@null"
        android:scaleType="fitStart"
        android:src="@drawable/uber_map_bg" />

    <RelativeLayout
        android:id="@+id/layout_bottom_sheet"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white"
        android:elevation="8dp"
        app:behavior_hideable="false"
        app:behavior_peekHeight="130dp"
        **app:layout_behavior="android.support.design.widget.BottomSheetBehavior">**

        <TextView
            android:id="@+id/text_view_sheet_title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="@dimen/activity_vertical_margin"
            android:text="点击叫车,上滑选车"
            android:textSize="14sp" />

    </RelativeLayout>


</android.support.design.widget.CoordinatorLayout>


```

2.然后代码中填充，已经使用BottomSheetBehavior：

```

private BottomSheetBehavior mBottomSheetBehavior;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

       ButterKnife.bind(this);

       mBottomSheetBehavior = BottomSheetBehavior.from(mLayoutBottomSheet);
       mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
           @Override
           public void onStateChanged(@NonNull View bottomSheet, int newState) {

               switch (newState) {
                   case BottomSheetBehavior.STATE_DRAGGING:
                       Log.e("Tag", "STATE_DRAGGING");
                       //用户上下拖拽BottomSheet时的状态
                       break;
                   case BottomSheetBehavior.STATE_SETTLING:
                       Log.e("Tag", "STATE_SETTLING");
                       //BottomSheet填充view释放时记录的最后状态
                       break;
                   case BottomSheetBehavior.STATE_EXPANDED:
                       Log.e("Tag", "STATE_EXPANDED");
                       //BottomSheet展开状态，会显示整个填充的view
                       break;
                   case BottomSheetBehavior.STATE_COLLAPSED:
                       Log.e("Tag", "STATE_COLLAPSED");
                       //关闭BottomSheet状态,然后显示设置的peekHeight的高度，改高度默认是0
                       break;
                  case
                  BottomSheetBehavior.STATE_HIDDEN:
                       Log.e("Tag", "Default");
                       //BottomSheet默认的状态，是显示或者隐藏，通过app:behavior_hideable来设置
                       break;

               }
           }

           @Override
           public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            //slideOffset滑动偏移距离
           }
       });

   }



```


3. 通过监听BottomSheet的各个状态，开始处理业务逻辑即可.


## BottomSheetBehavior注意点：

1. 作为填充BottomSheet的View，一定要声明一下，不然会报错：

```

Caused by: java.lang.IllegalArgumentException: The view is not associated with BottomSheetBehavior at android.support.design.widget.BottomSheetBehavior.from(BottomSheetBehavior.java:710)

```

声明两种方式：
* 通过布局文件声明：
  app:layout_behavior="android.support.design.widget.BottomSheetBehavior"

* 通过注解声明：通过设置给子 View 设置 CoordinatorLayout.Behavior 可以实现多种交互方式。通过 DefaultBehavior 注解可以给 View 设置默认的 Behavior.
**这里View指的是自定义View，也就是这一种方式只适用于自定义View**
