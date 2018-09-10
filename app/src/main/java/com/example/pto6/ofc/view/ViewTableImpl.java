package com.example.pto6.ofc.view;

public class ViewTableImpl implements ViewTable {
    @Override
    public Class<?> get(String key) {
        switch (key) {
            case "debit":
                return DebitEditView.class;
            case "credit":
                return CreditEditView.class;
            case "graphs":
                return GraphsActivity.class;
            case "main":
            default:
                return OfcListActivity.class;
        }
    }
}
