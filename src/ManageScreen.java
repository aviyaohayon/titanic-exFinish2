import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManageScreen extends JPanel {
    private JComboBox<String> survivedComboBox;
    private List<Passenger> passengerList;
    private JTextField minRange;
    private int minIdInt;
    private JTextField maxRange;
    private int maxIdInt;
    private JTextField name;
    private JComboBox<String> sexComboBox;
    private JTextField sibSp;
    private JTextField parch;
    private JTextField ticket;
    private JTextField minFare;
    private JTextField maxFare;
    private JTextField cabin;
    private JComboBox<String> embarkedComboBox;
    private JButton submit;
    List<Passenger> sortList;
    private JButton statistic;
    private int submitTimes = Constants.DEFAULT;
    private ImageIcon titanic;



    public ManageScreen(int x, int y, int width, int height) {
        this.passengerList = createPassengerList();
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        if (file.exists()) {
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
            JLabel survivedLabel = new JLabel("Passenger Class: ");
            survivedLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel);
            this.survivedComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.survivedComboBox.setBounds(survivedLabel.getX() + survivedLabel.getWidth() + 1, survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.survivedComboBox);
            JLabel rangeNumLabel = new JLabel("Passenger Range Number: ");
            rangeNumLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y + Constants.SPACE_Y, Constants.LABEL_WIDTH + 80, Constants.LABEL_HEIGHT);
            this.add(rangeNumLabel);
            JLabel minRange = new JLabel("Min: ");
            minRange.setBounds(x + Constants.MARGIN_FROM_LEFT, rangeNumLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(minRange);
            this.minRange = new JTextField();
            this.minRange.setBounds(minRange.getWidth(), minRange.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.minRange);
            JLabel maxRange = new JLabel("Max: ");
            maxRange.setBounds(Constants.RANGE_NUMBER_X, rangeNumLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(maxRange);
            this.maxRange = new JTextField();
            this.maxRange.setBounds(maxRange.getWidth() + maxRange.getX(), maxRange.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.maxRange);
            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, minRange.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(nameLabel);
            this.name = new JTextField();
            this.name.setBounds(nameLabel.getX() + nameLabel.getWidth(), nameLabel.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(this.name);
            JLabel sexLabel = new JLabel("Sex:");
            sexLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, nameLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(sexLabel);
            this.sexComboBox = new JComboBox<>(Constants.SEX_OPTION);
            this.sexComboBox.setBounds(sexLabel.getX() + sexLabel.getWidth(), sexLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.sexComboBox);
            JLabel sibSpLabel = new JLabel("Amount Of Siblings/ Partners: ");
            sibSpLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, sexComboBox.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH * 2, Constants.LABEL_HEIGHT);
            this.add(sibSpLabel);
            this.sibSp = new JTextField();
            this.sibSp.setBounds(sibSpLabel.getX() + sibSpLabel.getWidth(), sibSpLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.sibSp);
            JLabel parChLabel = new JLabel("Amount Of Children/ Parents: ");
            parChLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, sibSpLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH * 2, Constants.LABEL_HEIGHT);
            this.add(parChLabel);
            this.parch = new JTextField();
            this.parch.setBounds(parChLabel.getX() + parChLabel.getWidth(), parChLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.parch);
            JLabel ticketLabel = new JLabel("One Number From Ticket: ");
            ticketLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, parch.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH * 2, Constants.LABEL_HEIGHT);
            this.add(ticketLabel);
            this.ticket = new JTextField();
            this.ticket.setBounds(ticketLabel.getX() + ticketLabel.getWidth(), ticketLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.ticket);
            JLabel rangeFareLabel = new JLabel("Passenger Range Fare: ");
            rangeFareLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, ticketLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH + 80, Constants.LABEL_HEIGHT);
            this.add(rangeFareLabel);
            JLabel minFare = new JLabel("Min: ");
            minFare.setBounds(x + Constants.MARGIN_FROM_LEFT, rangeFareLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(minFare);
            this.minFare = new JTextField();
            this.minFare.setBounds(minFare.getWidth(), minFare.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.minFare);
            JLabel maxFare = new JLabel("Max: ");
            maxFare.setBounds(x + Constants.RANGE_NUMBER_X, rangeFareLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(maxFare);
            this.maxFare = new JTextField();
            this.maxFare.setBounds(maxFare.getWidth() + maxFare.getX(), maxFare.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.maxFare);
            JLabel cabinLabel = new JLabel("One Number From Cabin: ");
            cabinLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, maxFare.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH * 2, Constants.LABEL_HEIGHT);
            this.add(cabinLabel);
            this.cabin = new JTextField();
            this.cabin.setBounds(cabinLabel.getWidth() + cabinLabel.getX(), cabinLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.cabin);
            JLabel embarkedLabel = new JLabel("Passenger Embarked: ");
            embarkedLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, cabinLabel.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH * 2, Constants.LABEL_HEIGHT);
            this.add(embarkedLabel);
            this.embarkedComboBox = new JComboBox<>(Constants.EMBARKED_OPTION);
            this.embarkedComboBox.setBounds(embarkedLabel.getX() + embarkedLabel.getWidth(), embarkedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.embarkedComboBox);



            this.submit = new JButton("Submit");
            this.submit.setBounds(x + Constants.LABEL_HEIGHT, Constants.WINDOW_HEIGHT - Constants.BUTTON_HEIGHT, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            add(submit);
            JLabel resultSort =new JLabel("");
            resultSort.setBounds(x + Constants.MARGIN_FROM_LEFT, submit.getY() + Constants.SPACE_Y, Constants.LABEL_WIDTH * 4, Constants.LABEL_HEIGHT);
            this.add(resultSort);
            this.repaint();
            this.submit.addActionListener((e) -> {
                this.sortList = this.passengerList;
                this.submitTimes++;
                String selectedPClass = (String) survivedComboBox.getSelectedItem();
                this.sortByPClass(selectedPClass);
                String selectRangeMin = this.minRange.getText();
                String selectRangeMax = this.maxRange.getText();
                this.sortByRangeId(selectRangeMin, selectRangeMax);
                String selectName = this.name.getText();
                this.sortByName(selectName);
                String selectSex = (String) this.sexComboBox.getSelectedItem();
                this.sortBySex(selectSex);
                String selectSibSp = this.sibSp.getText();
                this.sortBySibSp(selectSibSp);
                String selectParch = this.parch.getText();
                this.sortByParch(selectParch);
                String selectTicket = this.ticket.getText();
                this.sortByTicket(selectTicket);
                String selectMinFare = this.minFare.getText();
                String selectMaxFare = this.maxFare.getText();
                this.sortByFare(selectMinFare, selectMaxFare);
                String selectCabin = this.cabin.getText();
                this.sortByCabin(selectCabin);
                String selectEmbarked = (String) this.embarkedComboBox.getSelectedItem();
                this.sortByEmbarked(selectEmbarked);
                this.creatSortFile(this.submitTimes);

                resultSort.setText ("Total Rows: " + this.sortList.size() + "(" + this.survivedAmount(this.sortList) + "survived, " + this.notSurvivedAmount() + " did not) ");
            });

            this.statistic = new JButton("Creat Statistic File:");
            this.statistic.setBounds(submit.getX() + submit.getWidth() * 2, Constants.WINDOW_HEIGHT - Constants.BUTTON_HEIGHT, Constants.LABEL_WIDTH + 50, Constants.LABEL_HEIGHT);
            add(statistic);

            this.statistic.addActionListener((e) -> {
                this.creatStatisticFile();
            });
        }
    }

    public List<Passenger> createPassengerList() {
        List<Passenger> passengers = new ArrayList<>();
        String csvFile = Constants.PATH_TO_DATA_FILE;
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                Passenger passenger = new Passenger(line);
                passengers.add(passenger);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passengers;

    }


    public void sortByPClass(String userChoose) {
        this.sortList = this.sortList.stream().filter(passenger -> (passenger.typeClass(userChoose))).collect(Collectors.toList());
    }

    public void sortByRangeId(String min, String max) {
        try {
            this.minIdInt = Integer.parseInt(min);
        } catch (Exception e) {
            this.minIdInt = Constants.MIN_PASSENGER_ID;
        }
        try {
            this.maxIdInt = Integer.parseInt(max);
        } catch (Exception e) {
            this.maxIdInt = Constants.MAX_PASSENGER_ID;
        }
        this.sortList = this.sortList.stream().filter(passenger -> (passenger.rangeId(this.minIdInt, this.maxIdInt))).collect(Collectors.toList());
    }

    public void sortByName(String name) {
        this.sortList = this.sortList.stream().filter(passenger -> (passenger.isContainInName(name))).collect(Collectors.toList());
    }

    public void sortBySex(String sex) {
        this.sortList = this.sortList.stream().filter(passenger -> (passenger.sexType(sex))).collect(Collectors.toList());
    }

    public void sortBySibSp(String sibSp) {
        if (!sibSp.equals("")) {
            try {
                int sibSpInt = Integer.parseInt(sibSp);
                this.sortList = this.sortList.stream().filter(passenger -> (passenger.amountSibSp(sibSpInt))).collect(Collectors.toList());
            } catch (Exception e) {

            }
        }
    }

    public void sortByParch(String parch) {
        if (parch != null) {
            try {
                int sibParchInt = Integer.parseInt(parch);
                this.sortList = this.sortList.stream().filter(passenger -> (passenger.amountParCh(sibParchInt))).collect(Collectors.toList());
            } catch (Exception e) {

            }
        }
    }

    public void sortByTicket(String ticket) {
        try {
            if (!ticket.equals("")) {
                this.sortList = this.sortList.stream().filter(passenger -> (passenger.ticketNum(ticket))).collect(Collectors.toList());
            }
        } catch (Exception e) {

        }
    }

    public void sortByFare(String min, String max) {

        this.sortList = this.sortList.stream().filter(passenger -> (passenger.fareRange(min, max))).collect(Collectors.toList());
    }

    public void sortByCabin(String cabin) {

        try {
            if (!cabin.equals("")) {
                this.sortList = this.sortList.stream().filter(passenger -> (passenger.cabinNumber(cabin))).collect(Collectors.toList());
            }
        } catch (Exception e) {

        }
    }

    public void sortByEmbarked(String userChoose) {
        this.sortList = this.sortList.stream().filter(passenger -> (passenger.typeEmbarked(userChoose))).collect(Collectors.toList());
    }

    public int survivedAmount(List <Passenger> list) {
        int survived = 0;
        for (Passenger passenger : list) {
            if (passenger.isSurvived())
                survived++;
        }
        return survived;
    }

    public int notSurvivedAmount() {
        int notSurvived = 0;
        for (Passenger passenger : this.sortList) {
            if (!passenger.isSurvived())
                notSurvived++;
        }
        return notSurvived;
    }

    public void creatStatisticFile(){
        try {
            FileWriter statisticFile = new FileWriter("src/"+ "Statistics.txt");
            statisticFile.write(this.percentSummary());
            statisticFile.close();
        }catch (Exception e){

        }
    }


    public double pClassPercent(String chooseUser ){
        List <Passenger> pClassList =this.passengerList.stream().filter(passenger -> (passenger.typeClass(chooseUser))).collect(Collectors.toList());
        double listSize=  pClassList.size();
        double survivedAmount= this.survivedAmount(pClassList);
        return((survivedAmount/listSize )*100);
    }

    public String percentSummary(){

        String data= "survived percent from:"+"\n"
                +"percentPClass:" +"\n"
                + "first:" +this.pClassPercent("1")+ "\n"
                + "second:"+this.pClassPercent("2")+"\n"
                + "third:"+ this.pClassPercent("3")+"\n"
                + "sex:" + "\n"
                + "male:" +this.sexPercent("male")+"\n"
                +"female:" + this.sexPercent("female")+"\n"
                + "age:"  +"\n"
                +"between: 0-10:"+ this.agePercent(0,10)+"\n"
                +"between: 11-20:"+ this.agePercent(11,20) +"\n"
                +"between: 21-30:"+ this.agePercent(21,30)+"\n"
                +"between: 31-40:"+ this.agePercent(31,40)+"\n"
                +"between: 41-50:"+ this.agePercent(41,50)+"\n"
                +"between: 51+:"+ this.agePercent(51,this.maxAge())+"\n"
                +"exist family:" +this.existFamilyPercent()+"\n"
                +"not exist family:" +this.notExistFamilyPercent()+"\n"
                +"fare range:"+ "\n"
                +"between 0-10:"+ this.farRangePercent("0","10")+"\n"
                +"between 11-30:"+ this.farRangePercent("11","30")+"\n"
                +"between: 31+:"+ this.farRangePercent("31",this.maxFare()+"")+"\n"
                +"embarked:"+ "\n"
                +"S:"+this.embarkedPercent("S")+"\n"
                +"Q:"+this.embarkedPercent("Q")+"\n"
                +"C:"+this.embarkedPercent("C")+"\n";
        return data;
    }

    public double sexPercent(String chooseUser ){
        List <Passenger> sexList =this.passengerList.stream().filter(passenger -> (passenger.sexType(chooseUser))).collect(Collectors.toList());
        double listSize=  sexList.size();
        double survivedAmount= this.survivedAmount(sexList);
        return((survivedAmount/listSize )*100);
    }
    public double agePercent(int min , int max){
    List <Passenger> pClassList =this.passengerList.stream().filter(passenger -> (passenger.ageRange(min,max))).collect(Collectors.toList());
    double listSize=  pClassList.size();
    double survivedAmount= this.survivedAmount(pClassList);
    return((survivedAmount/listSize )*100);
}
    public int maxAge (){
        int max=0;
        for (Passenger passenger : passengerList){
            if(passenger.getAge()>max)
                max=passenger.getAge();
        }
        return max;
    }
    public double existFamilyPercent(){
        List <Passenger> familyList =this.passengerList.stream().filter(passenger -> (passenger.existFamily())).collect(Collectors.toList());
        double listSize=  familyList.size();
        double survivedAmount= this.survivedAmount(familyList);
        return((survivedAmount/listSize )*100);
    }
    public double notExistFamilyPercent(){
        List <Passenger> notFamilyList =this.passengerList.stream().filter(passenger -> (passenger.notExistFamily())).collect(Collectors.toList());
        double listSize=  notFamilyList.size();
        double survivedAmount= this.survivedAmount(notFamilyList);
        return((survivedAmount/listSize )*100);
    }
    public double farRangePercent(String min , String max){
        List <Passenger> fareList =this.passengerList.stream().filter(passenger -> (passenger.fareRange(min,max))).collect(Collectors.toList());
        double listSize=  fareList.size();
        double survivedAmount= this.survivedAmount(fareList);
        return((survivedAmount/listSize )*100);
    }
    public int maxFare (){
        int max=0;
        for (Passenger passenger : passengerList){
            if(passenger.getFare()>max)
                max=passenger.getFare();
        }
        return max;
    }
    public double embarkedPercent(String embarked){
        List <Passenger> embarkedList =this.passengerList.stream().filter(passenger -> (passenger.typeEmbarked(embarked))).collect(Collectors.toList());
        double listSize=  embarkedList.size();
        double survivedAmount= this.survivedAmount(embarkedList);
        return((survivedAmount/listSize )*100);
    }
    public void creatSortFile(int num){
        try {
            FileWriter sortFile = new FileWriter("src/" +num+ ".csv");
            sortFile.write(this.sortFileData());
            sortFile.close();
        }catch (Exception e){

        }
    }
    public String sortFileData (){
        String data="";
    for (Passenger passenger: this.sortList){
        data+=passenger.toString()+"\n";

    }
    return data;
    }
    public void paintBackground(Graphics graphics){
        this.titanic = new ImageIcon("src/data/טיטניק סופי.jpeg");
        titanic.paintIcon(this,graphics,0,0);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        this.paintBackground(graphics);

    }
}




