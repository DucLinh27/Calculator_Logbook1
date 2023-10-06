package easy.tuto.easycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultCalculaor, solutionCalculator;
    MaterialButton buttonCalulatorC, buttonCalulatorBrackOpen, buttonCalulatorBrackClose;
    MaterialButton buttonCalulatorDivide, buttonCalulatorMultiply, buttonCalulatorPlus, buttonCalulatorMinus, buttonCalulatorEquals;
    MaterialButton buttonCalulator0, buttonCalulator1, buttonCalulator2, buttonCalulator3, buttonCalulator4, buttonCalulator5, buttonCalulator6, buttonCalulator7, buttonCalulator8, buttonCalulator9;
    MaterialButton buttonCalulatorAC, buttonCalulatorDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       resultCalculaor = findViewById(R.id.result_calculator);
       solutionCalculator = findViewById(R.id.solution_calculator);

       assignCalculatorId(buttonCalulatorC,R.id.buttonCal_c);
       assignCalculatorId(buttonCalulatorBrackOpen,R.id.buttonCal_open_bracket);
       assignCalculatorId(buttonCalulatorBrackClose,R.id.buttonCal_close_bracket);
       assignCalculatorId(buttonCalulatorDivide,R.id.buttonCal_divide);
       assignCalculatorId(buttonCalulatorMultiply,R.id.buttonCal_multiply);
       assignCalculatorId(buttonCalulatorPlus,R.id.buttonCal_plus);
       assignCalculatorId(buttonCalulatorMinus,R.id.buttonCal_minus);
       assignCalculatorId(buttonCalulatorEquals,R.id.buttonCal_equals);
       assignCalculatorId(buttonCalulator0,R.id.buttonCal_0);
       assignCalculatorId(buttonCalulator1,R.id.buttonCal_1);
       assignCalculatorId(buttonCalulator2,R.id.buttonCal_2);
       assignCalculatorId(buttonCalulator3,R.id.buttonCal_3);
       assignCalculatorId(buttonCalulator4,R.id.buttonCal_4);
       assignCalculatorId(buttonCalulator5,R.id.buttonCal_5);
       assignCalculatorId(buttonCalulator6,R.id.buttonCal_6);
       assignCalculatorId(buttonCalulator7,R.id.buttonCal_7);
       assignCalculatorId(buttonCalulator8,R.id.buttonCal_8);
       assignCalculatorId(buttonCalulator9,R.id.buttonCal_9);
       assignCalculatorId(buttonCalulatorAC,R.id.buttonCal_ac);
       assignCalculatorId(buttonCalulatorDot,R.id.buttonCal_dot);
    }
    void assignCalculatorId(MaterialButton btnCal, int id){
        btnCal = findViewById(id);
        btnCal.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton buttonCal =(MaterialButton) view;
        String buttonCalText = buttonCal.getText().toString();
        String dataToCalculator = solutionCalculator.getText().toString();

        if(buttonCalText.equals("AC")){
            solutionCalculator.setText("");
            resultCalculaor.setText("0");
        } else if(buttonCalText.equals("=")){
            String resultCalFinal = getResult(dataToCalculator);
            if (!resultCalFinal.equals("Error")) {
                resultCalculaor.setText(resultCalFinal);
            } else {
                resultCalculaor.setText("Error");
            }
        } else if(buttonCalText.equals("C")){
            if (!dataToCalculator.isEmpty()) {
                dataToCalculator = dataToCalculator.substring(0, dataToCalculator.length() - 1);
                solutionCalculator.setText(dataToCalculator);
            }
        } else {
            dataToCalculator = dataToCalculator + buttonCalText;
            solutionCalculator.setText(dataToCalculator);
        }
    }
        String getResult(String data){
        try{
            // Khởi tạo một Context của JavaScript
            Context context  = Context.enter();
            // Tắt tối ưu hóa (optimization) để đảm bảo tính chính xác của kết quả
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            // Khởi tạo đối tượng Scriptable để thực hiện đánh giá biểu thức
            String finalResultCal =  context.evaluateString(scriptable,data,"Javascript",
                    1,null).toString();

            // Nếu kết quả kết thúc bằng ".0," loại bỏ phần thập phân
            if(finalResultCal.endsWith(".0")){
                finalResultCal = finalResultCal.replace(".0","");
            }
            return finalResultCal;
        }catch (Exception e){
            return "Error";
        }
    }
}