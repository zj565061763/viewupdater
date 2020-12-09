# Gradle
[![](https://jitpack.io/v/zj565061763/viewupdater.svg)](https://jitpack.io/#zj565061763/viewupdater)

# 简单demo
```java
public class MainActivity extends AppCompatActivity
{
    public static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private final ViewUpdater mUpdater = new OnPreDrawUpdater();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 切换选中状态
                v.setSelected(!v.isSelected());
            }
        });

        // 设置状态变更回调
        mUpdater.setOnStateChangeCallback(new ViewUpdater.OnStateChangeCallback()
        {
            @Override
            public void onStateChanged(boolean started, ViewUpdater updater)
            {
                Log.i(TAG, "onStateChanged:" + started);
            }
        });

        // 设置监听哪个view
        mUpdater.setView(mButton);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // 开始监听
        mUpdater.start();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        // 停止监听
        mUpdater.stop();
    }
}
```
# 接口
```java
public interface ViewUpdater
{
    /**
     * 设置更新回调对象
     *
     * @param updatable
     */
    void setUpdatable(Updatable updatable);

    /**
     * 通知设置的回调对象{@link #setUpdatable(Updatable)}进行更新
     */
    void notifyUpdatable();

    /**
     * 返回设置的view
     *
     * @return
     */
    View getView();

    /**
     * 设置view
     *
     * @param view
     */
    void setView(View view);

    /**
     * 是否已经开始监听
     *
     * @return
     */
    boolean isStarted();

    /**
     * 开始监听
     *
     * @return true-成功开始
     */
    boolean start();

    /**
     * 停止监听
     */
    void stop();

    interface Updatable
    {
        /**
         * 更新回调
         */
        void update();
    }
}
```