public class Passenger {
    private int passengerId;
    private int survived;
    private String pClass;
    private String name;
    private String sex;
    private int age;
    private int sibSp;
    private int parch;
    private String ticket;
    private int  fare;
    private int fareMaxInt;
    private int fareMinInt;
    private String cabin;
    private String embarked;

    public Passenger(String passenger) {
        try {
            String[] passengerData = passenger.split(Constants.CSV_SPLIT_BY);
            this.passengerId = Integer.parseInt(passengerData[0]);
            this.survived = Integer.parseInt(passengerData[1]);
            this.pClass = (passengerData[2]);
            this.name = getFormattedName(passengerData[4])+" "+passengerData[3];
            this.sex = passengerData[5];
            this.age = Integer.parseInt(passengerData[6]);
            this.sibSp = Integer.parseInt(passengerData[7]);
            this.parch = Integer.parseInt(passengerData[8]);
            this.ticket = passengerData[9];
            this.fare = Integer.parseInt(passengerData[10].substring(0,passengerData[10].indexOf(".")));
            this.cabin = (passengerData[11]);
            this.embarked = passengerData[12];
        }catch (Exception e){

        }
    }
    public static String getFormattedName(String firstName){
        return firstName.substring(firstName.indexOf(".")+2);
    }

    @Override
    public String toString() {
        return
                "passengerId=" + passengerId +
                        ", survived=" + survived +
                        ", pClass=" + pClass +
                        ", name=" + name  +
                        ", sex=" + sex  +
                        ", age=" + age +
                        ", sibSp=" + sibSp +
                        ", parch=" + parch +
                        ", ticket=" + ticket  +
                        ", fare=" + fare  +
                        ", cabin=" + cabin  +
                        ", embarked=" + embarked  +
                        '}';
    }

    public boolean isSurvived(){
        return (this.survived == 1);
    }

    public boolean typeClass(String type){
        boolean result = false;
        if (type.equals("All")){
            return true;
        }else if (this.pClass!=null){
            result= this.pClass.equals(type.substring(0,1));
        }
        return result;


    }

    public boolean rangeId(int min, int max){
        return (this.passengerId >= min && this.passengerId <= max);
    }

    public boolean isContainInName(String name) {
        boolean result = false;
        if (this.name != null && (this.name.contains(name)))
             result =true;
        return result;
    }

    public boolean sexType(String type){
        boolean result = false;
        if (this.sex!=null) {
            if (type.equals("All"))
                result = true;
            else if (this.sex.equals(type))
                result = true;
        }
        return result;
    }
    public boolean ageRange (int min ,int max ){
        return (this.age>= min&& this.age<=max);
    }

    public int getAge() {
        return age;
    }

    public boolean amountSibSp(int num){
        return (this.sibSp == num);
    }

    public boolean amountParCh(int num){
        return (this.parch == num);
    }
    public  boolean existFamily(){
        return (this.parch+this.sibSp!=0);
    }
    public  boolean notExistFamily(){
        return (this.parch+this.sibSp==0);
    }
    public boolean ticketNum(String num){
        boolean isContain = false;
        try {
            char numChar = num.charAt(0);
            if (num.length() == 1 && Character.isDigit(numChar) && this.ticket.contains(num)){
                isContain = true;
            }
        }catch (Exception e){

        }
            return isContain;
        }
    public boolean fareRange(String  min, String  max) {
        try {
            this.fareMinInt= Integer.parseInt(min);
        }catch (Exception e){
            this.fareMinInt  = Constants.DEFAULT;
        }
        try {
            this.fareMaxInt= Integer.parseInt(max);
        }catch (Exception e){
            this.fareMaxInt = Constants.DEFAULT;
        }
        if (this.fareMaxInt != 0) {
            return ( this.fare >= this.fareMinInt&& this.fare <= this.fareMaxInt);
        }else
            return (this.fare >= this.fareMinInt);
    }

    public int getFare() {
        return this.fare;
    }

    public boolean cabinNumber (String num){
            boolean isContain = false;
            try {
                char numChar = num.charAt(0);
                if (num.length() == 1 && Character.isDigit(numChar) && this.cabin.contains(num)){
                    isContain = true;
                }
            }catch (Exception e){

            }
            return isContain;
        }

    public boolean typeEmbarked(String type){
        boolean result = false;
        if (type.equals("All")){
            return true;
        }else if (this.embarked!=null){
            result= this.embarked.equals(type);
        }
        return result;

    }
}