package legalFreedom.java.view.main;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import legalFreedom.R;
import legalFreedom.java.view.base.BaseActivity;
import legalFreedom.java.view.home.HomeFragment;

public class MainActivity extends BaseActivity {
    @BindView(R.id.container_view_group)
    FrameLayout containerViewGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = HomeFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewGroup.getId(), homeFragment, homeFragment.getClass().getSimpleName())
                .commit();

    }

}
