package edu.self.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.self.R;
import edu.self.presenter.CalculatorPresenter;
import edu.self.presenter.IPresenter;

public class MainActivity extends AppCompatActivity implements IView{

    private static final String TAG = MainActivity.class.getName();
    @BindView(R.id.one)
    Button one;
    @BindView(R.id.two)
    Button two;
    @BindView(R.id.three)
    Button three;
    @BindView(R.id.four)
    Button four;
    @BindView(R.id.five)
    Button five;
    @BindView(R.id.six)
    Button six;
    @BindView(R.id.seven)
    Button seven;
    @BindView(R.id.eight)
    Button eight;
    @BindView(R.id.nine)
    Button nine;
    @BindView(R.id.zero)
    Button zero;
    @BindView(R.id.mul)
    Button mul;
    @BindView(R.id.sub)
    Button sub;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.div)
    Button div;
    @BindView(R.id.equal)
    Button equal;
    @BindView(R.id.resultView)
    TextView resultEquals;
    IPresenter iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iPresenter = new CalculatorPresenter(this);
    }

    @OnClick({ R.id.one, R.id.two, R.id.three,R.id.four,R.id.five,R.id.six,R.id.seven ,
            R.id.eight ,R.id.nine ,R.id.zero ,R.id.add ,R.id.sub ,R.id.div ,R.id.mul ,R.id.equal })
    public void buttonClick(View view) {
        Button button = (Button) view;
        String text = (String) button.getText();
        iPresenter.handleButtonClick(text);
        Log.d(TAG, "buttonClick: text =" +text);
    }

    public void updateResult(String result){
        resultEquals.setText(result);
    }
}