package edu.self.presenter;

import java.util.ArrayList;
import java.util.List;
import edu.self.Model.CalculatorModel;
import edu.self.Model.IModel;
import edu.self.view.IView;

public class CalculatorPresenter implements IPresenter {
    IView iView ;
    IModel iModel;

    public CalculatorPresenter(IView iView) {
        this.iView = iView;
        iModel  = new CalculatorModel(this);
    }

    @Override
    public void handleButtonClick(String result) {
        switch (result) {
            case "=":
                computeResult(iModel.getData());
                break;
            default:
                iModel.update(iModel.getData() +result);
                iView.updateResult(iModel.getData());
        }
    }

    private void computeResult(String result) {
        List<String> numbers = new ArrayList<>();
        String operand1 = "";
        formOperatorsAndOperand(result, numbers, operand1);
        if (numbers.size() > 2) {
            calculateResult(numbers);
        }else{
            iView.updateResult("Invalid Input");
            iModel.update("");
        }
    }

    private void calculateResult(List<String> numbers) {
        int result;
        if (numbers.get(0).contains("+-/*")) {
            iView.updateResult("Invalid Input");
            iModel.update("");
            return;
        }
        try {
            result = Integer.parseInt(numbers.get(0));
            int index = 1;
            while (numbers.size() > index) {
                switch (numbers.get(index)) {
                    case "+":
                        result = result + Integer.parseInt(numbers.get(index + 1));
                        break;
                    case "-":
                        result = result - Integer.parseInt(numbers.get(index + 1));
                        break;
                    case "/":
                        result = result / Integer.parseInt(numbers.get(index + 1));
                        break;
                    case "*":
                        result = result * Integer.parseInt(numbers.get(index + 1));
                        break;
                    default:
                        iView.updateResult("Invalid Expression");
                }
                index += 2;
            }
        }catch (ArithmeticException  e){
            e.printStackTrace();
            iView.updateResult(e.getMessage());
            return;
        }catch (Exception e){
            e.printStackTrace();
            iView.updateResult("Invalid Input");
            return;
        }finally {
            iModel.update("");
        }
        iView.updateResult(""+result);
    }

    private void formOperatorsAndOperand(String result, List<String> numbers, String operand1) {
        for (int i = 0; i < result.length(); i++) {
            switch (result.charAt(i)) {
                case '+':
                case '-':
                case '/':
                case '*':
                    if (!operand1.equals("")) {
                        numbers.add(numbers.size(), operand1);
                        operand1 = "";
                    }
                    numbers.add(numbers.size(), "" + result.charAt(i));
                    break;
                default:
                    operand1 = operand1 + result.charAt(i);
                    break;
            }
        }
        if (!operand1.equals("")) {
            numbers.add(numbers.size(), operand1);
        }
    }
}