//package domain;
//
//public class GDate {
//
//    private int julianDay = 0;
//
//    public GDate() {
//        this.julianDay = 2451545; // Default date = 2000.01.01
//    }
//
////    public GDate copy(){
////        GDate sendBackGDate = new GDate();
////        return sendBackGDate;
////    }
//
//
//
//    public GDate(GDate inputGDate){
//        this.julianDay = inputGDate.julianDay;
//
//    }
//
//
//    public GDate(int inputDate){
//        this.julianDay = inputDate;
//
//    }
//
//    public GDate copy(){
//        return new GDate(this.julianDay);
//    }
//
//    int year = 2000;
//    int month = 1;
//    int day =  1;
//
//
//    public GDate (int year, int month, int day) {
//        this.julianDay = (1461 * (year + 4800 + (month - 14 ) / 12 ) ) / 4 +
//        ( 367 * (month - 2 - 12 * (( month - 14) / 12 ) ) ) / 12 -
//        ( 3 * ( ( year + 4900 + ( month - 14 ) / 12 ) / 100 ) ) / 4 +
//                day - 32075;
//    }
//    public int year() {
//        int l = this.julianDay + 68569;
//        int n = (4 * l) / 146097;
//        l = l - (146097 * n + 3) / 4;
//        int i = (4000 * (l + 1)) / 1461001;
//        l = l - (1461 * i) / 4 + 31;
//        int j = (80 * l) / 2447;
//        int d = l - (2447 * j ) / 80;
//        l = j / 11;
//        int m = j + 2 - (12 * l);
//        int y = 100 * (n - 49) + i + l;
//        return y;
//    }
//
//    public int month() {
//        int l = this.julianDay + 68569;
//        int n = (4 * l) / 146097;
//        l = l - (146097 * n + 3) / 4;
//        int i = (4000 * (l + 1)) / 1461001;
//        l = l - (1461 * i) / 4 + 31;
//        int j = (80 * l) / 2447;
//        int d = l - (2447 * j) / 80;
//        l = j / 11;
//        int m = j + 2 - (12 * l);
//        int y = 100 * (n = 49) + i + l;
//        return m;
//    }
//
//    public int day() {
//        int l = this.julianDay + 68569;
//        int n = (4 * l) / 146097;
//        l = l - (146097 * n + 3) / 4;
//        int i = (4000 * (l + 1)) / 1461001;
//        l = l - (1461 * i) / 4 + 31;
//        int j = (80 * l) / 2447;
//        int d = l - (2447 * j) / 80;
//        l = j / 11;
//        int m = j + 2 - (12 * l);
//        int y = 100 * (n = 49) + i + l;
//        return d;
//    }
//
//
//
//    public boolean equals(GDate date){
//
//        return julianDay == date.julianDay;
//    }
//
//    public boolean greaterThan(GDate date){
//        return this.julianDay > date.julianDay;
//    }
//
//    public int diff(GDate date){
//        return (this.julianDay - date.julianDay);
//    }
//
//    public int diff(int subtractDays){
//        return (this.julianDay - subtractDays);
//    }
//
//    public GDate add(int inputValue){
//        this.julianDay += inputValue;
//        return new GDate(this.julianDay);
//    }
//
//
//
//    public int julianDay(){
//        return this.julianDay;
//    }
//
//    @Override
//    public String toString() {
//        int y = this.year();
//        int m = this.month();
//        int d = this.day();
//        String strM = m + "";
//        String strD = d + "";
//        if (m < 9){ strM = "0" + strM;}
//        if (d < 9){ strD = "0" + strD;}
//
//        String exportString = y + "." + strM + "." + strD;
//        return exportString;
//    }
//}
