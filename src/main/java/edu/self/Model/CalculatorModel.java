package edu.self.Model;

import edu.self.presenter.IPresenter;

public class CalculatorModel implements IModel {
    //in this example presenter reference is not required but if required to
    // call presenter methods use this reference
    private IPresenter iPresenter;
    private String result;

    public CalculatorModel(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
        result = "";
    }

    @Override
    public void update(String text) {
        this.result = text;
    }

    @Override
    public String getData(){
        return result;
    }
}