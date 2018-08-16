package com.example.pto6.ofc.model;

public enum TypePeriod {
    DAY {
        @Override
        public String getName() {
            return "day";
        }
    }, MONTH {
        @Override
        public String getName() {
            return "month";
        }
    }, WEEK {
        @Override
        public String getName() {
            return "week";
        }
    }, YEAR {
        @Override
        public String getName() {
            return "year";
        }
    };

    public abstract String getName();
}
