# Gradle
[![](https://jitpack.io/v/zj565061763/viewupdater.svg)](https://jitpack.io/#zj565061763/viewupdater)

# 简单demo
```java
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private final ViewUpdater mUpdater = new OnPreDrawUpdater() {
        @Override
        protected void onStateChanged(boolean started) {
            super.onStateChanged(started);
            Log.i(TAG, "onStateChanged started:" + started);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换选中状态
                v.setSelected(!v.isSelected());
            }
        });

        // 设置更新回调对象
        mUpdater.setUpdatable(new ViewUpdater.Updatable() {
            @Override
            public void update() {
                // 获得选中状态
                final boolean isSelected = mButton.isSelected();
                mButton.setTextColor(isSelected ? Color.RED : Color.BLACK);
                Log.i(TAG, "update isSelected:" + isSelected);
            }
        });

        // 设置监听哪个view
        mUpdater.setView(mButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 开始监听
        mUpdater.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停止监听
        mUpdater.stop();
    }
}
```
# 接口
```java
public interface ViewUpdater {
    /**
     * 设置更新对象
     */
    void setUpdatable(@Nullable Updatable updatable);

    /**
     * 通知更新对象{@link #setUpdatable(Updatable)}
     */
    void notifyUpdatable();

    /**
     * 返回设置的view
     */
    @Nullable
    View getView();

    /**
     * 设置view
     */
    void setView(@Nullable View view);

    /**
     * 是否已经开始监听
     *
     * @return true-已经开始
     */
    boolean isStarted();

    /**
     * 开始监听
     *
     * @return true-已经开始
     */
    boolean start();

    /**
     * 停止监听
     */
    void stop();

    interface Updatable {
        /**
         * 更新回调
         */
        void update();
    }
}
```